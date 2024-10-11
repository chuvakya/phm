package org.zs.phm3.service.releability;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.failure.FailureService;
import org.zs.phm3.models.calculation.CalculationAttribute;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.repository.calculation.CalculationAttrRepository;
import org.zs.phm3.repository.calculation.CalculationAttrTypeRepository;
import org.zs.phm3.repository.unit.UnitAttributeRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Transactional
@Service
public class ReliabilityServiceImpl {


    @Value(value = "${reliability.url-probability}")
    private String urlProbability;

    @Autowired
    private CalculationAttrTypeRepository calculationAttrTypeRepository;

    @Autowired
    private CalculationAttrRepository calculationAttrRepository;

    @Autowired
    private UnitAttributeRepository unitAttributeRepository;

    @Autowired
    private FailureService failureService;

//    @Autowired
//    private UnitService unitService;

    @Scheduled(fixedRateString  = "${reliability.schedule-fixed-rate}")
    private void start() throws IOException, ParseException {
        writeProbability(getProbabilityJson(createJsonReliability(getAllUnitAttributes())));
    }


    private ArrayList<ReliabilityCalcEntity> getAllUnitAttributes(){
        Iterable<UnitAttribute> listAllUnitAttributeLambda = unitAttributeRepository.getAllById_AttributeKey("MTBF");
        Iterable<UnitAttribute> listAllUnitAttributeCommissioningTime =
                unitAttributeRepository.getAllById_AttributeKey("commissioning_time");
        Map<String, ReliabilityCalcEntity> reliabilityHashMap = new HashMap<>();
        Set<Map.Entry<String, ReliabilityCalcEntity>> set = reliabilityHashMap.entrySet();
        ArrayList<ReliabilityCalcEntity> reliabilityCalcEntitiesList = new ArrayList<>();

        for (UnitAttribute unitAttributeLambda: listAllUnitAttributeLambda) {
            reliabilityHashMap.put(unitAttributeLambda.getId().getUnitId(),
                    new ReliabilityCalcEntity(unitAttributeLambda.getId().getUnitId(), unitAttributeLambda.getDoubleValue()));
        }

        for (UnitAttribute unitAttributeCommissioningTime: listAllUnitAttributeCommissioningTime) {
            reliabilityHashMap.get(unitAttributeCommissioningTime.getId().getUnitId()).setCommissioningTime(
                    unitAttributeCommissioningTime.getLongValue());
        }

        for (Map.Entry<String, ReliabilityCalcEntity> me : set) {
            reliabilityCalcEntitiesList.add(me.getValue());
        }

        return reliabilityCalcEntitiesList;
    }

    private JSONArray getProbabilityJson(String json) throws IOException, ParseException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlProbability);
        StringEntity entity = new StringEntity(json);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        String resp = EntityUtils.toString(response.getEntity());
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(resp);

        return jsonArray;
    }

    private String createJsonReliability(ArrayList<ReliabilityCalcEntity> reliabilityCalcEntities){
        JSONArray jsonArray = new JSONArray();
        for (ReliabilityCalcEntity reliabilityCalcEntity: reliabilityCalcEntities) {
            double now = System.currentTimeMillis();
            double commissioningTime = reliabilityCalcEntity.getCommissioningTime();
            if(reliabilityCalcEntity.getCommissioningTime() == null) continue;
            JSONObject obj = new JSONObject();
            obj.put("id", reliabilityCalcEntity.getUnitId());
            obj.put("time", (now - commissioningTime)/3600000);
            obj.put("lambda", (1/reliabilityCalcEntity.getLambda()));
            jsonArray.add(obj);
        }
        return jsonArray.toJSONString();
    }

    private void writeProbability(JSONArray jsonArray){
        for (Object obj: jsonArray) {
            JSONObject jsonObj = (JSONObject) obj;

            CalculationAttribute calcAttr = calculationAttrRepository.getByAttrType_NameAndNameAndUnitId(
                    "Reliability Service","Probability",  PhmUtil.toShortUUID(jsonObj.get("id").toString()));

            if (calcAttr == null) {
                calcAttr = new CalculationAttribute(
                        PhmUtil.toShortUUID(jsonObj.get("id").toString()), "Probability",
                        (Double) jsonObj.get("prob_fault"), null, System.currentTimeMillis(),
                        calculationAttrTypeRepository.getByName("Reliability Service"));
            } else {
                calcAttr.setTime(System.currentTimeMillis());
                calcAttr.setValue((Double) jsonObj.get("prob_fault"));
            }

            calculationAttrRepository.save(calcAttr);
//            FailureEntity failureEntity = failureService.findByUnitId(PhmUtil.toShortUUID(jsonObj.get("id").toString()));
        }
    }

}
