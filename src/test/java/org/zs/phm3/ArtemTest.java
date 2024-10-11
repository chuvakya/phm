//package org.zs.phm3;
//
//import org.json.JSONException;
//import org.json.XML;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.zs.phm3.failure.FailureEntity;
//import org.zs.phm3.failure.FailureRepository;
//import org.zs.phm3.ftamodel.FtaModelRepository;
//import org.zs.phm3.ftamodel.result.FtaEventResult;
//import org.zs.phm3.models.unit.UnitAttributeKey;
//import org.zs.phm3.repository.unit.UnitAttributeRepository;
//
//import java.util.ArrayList;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ArtemTest {
//
//    @Autowired
//    private FtaModelRepository ftaModelRepository;
//
//    @Autowired
//    private FailureRepository failureRepository;
//
//    @Autowired
//    private UnitAttributeRepository unitAttributeRepository;
//
//    @Test
//    public void test(){
//        for (FtaEventResult ftaEventRes: getEventList(10L)) {
//            System.out.println(ftaEventRes);
//        }
//    }
//
//    private ArrayList<FtaEventResult> getEventList(Long projectId){
//        ArrayList<FtaEventResult> ftaEventResultArrayList = new ArrayList<>();
//        String xmlBody = ftaModelRepository.findByProjectId(projectId).getBody();
//        org.json.JSONObject json;
//        try {
//            json = XML.toJSONObject(xmlBody, true);
//            JSONParser parser = new JSONParser();
//            JSONObject simpJson = (JSONObject) parser.parse(String.valueOf(json));
//            JSONObject opsaMef = (JSONObject) simpJson.get("opsa-mef");
//            JSONObject modelData = (JSONObject) opsaMef.get("model-data");
//
//            if(modelData.get("define-basic-event") instanceof JSONArray){
//                JSONArray basicEvents = (JSONArray) modelData.get("define-basic-event");
//                ftaEventResultArrayList.addAll(getEventResultList(basicEvents));
//            } else if(modelData.get("define-basic-event") instanceof JSONObject){
//                JSONObject basicEvent = (JSONObject) modelData.get("define-basic-event");
//                ftaEventResultArrayList.addAll(getEventResultList(basicEvent));
//            }
//            if(modelData.get("define-house-event") instanceof JSONArray){
//                JSONArray houseEvents = (JSONArray) modelData.get("define-house-event");
//                ftaEventResultArrayList.addAll(getEventResultList(houseEvents));
//            } else if(modelData.get("define-house-event") instanceof JSONObject){
//                JSONObject houseEvent = (JSONObject) modelData.get("define-house-event");
//                ftaEventResultArrayList.addAll(getEventResultList(houseEvent));
//            }
//
//        } catch (JSONException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        return ftaEventResultArrayList;
//    }
//
//    private ArrayList<FtaEventResult> getEventResultList(JSONObject obj){
//        ArrayList<FtaEventResult> ftaEventResults = new ArrayList<>();
//        ftaEventResults.add(makeFtaEventResult(obj));
//        return ftaEventResults;
//    }
//
//    private ArrayList<FtaEventResult> getEventResultList(JSONArray arr){
//        ArrayList<FtaEventResult> ftaEventResults = new ArrayList<>();
//        for (int i = 0; i < arr.size(); i++){
//            ftaEventResults.add(makeFtaEventResult((JSONObject) arr.get(i)));
//        }
//        return ftaEventResults;
//    }
//
//    private FtaEventResult makeFtaEventResult(JSONObject obj){
//        FailureEntity failureEntity = failureRepository.findById(Long.valueOf(obj.get("label").toString())).get();
//        Double mtbf = unitAttributeRepository.findById(new UnitAttributeKey(failureEntity.getUnitId(), "MTBF"))
//                .get().getDoubleValue();
//        JSONObject attributes = (JSONObject) obj.get("attributes");
//        JSONObject constant = (JSONObject) obj.get("constant");
//        String eventType = null;
//        String probability = null;
//
//        if((attributes == null) && (constant == null)){
//            eventType = "basic";
//            JSONObject floatVal = (JSONObject) obj.get("float");
//            probability = floatVal.get("value").toString();
//        } else if((attributes == null) && (constant != null)){
//            eventType = "house";
//            JSONObject floatVal = (JSONObject) obj.get("constant");
//            probability = floatVal.get("value").toString();
//        } else if(attributes != null){
//            JSONObject attribute = (JSONObject) attributes.get("attribute");
//            JSONObject floatVal = (JSONObject) obj.get("float");
//            eventType = attribute.get("value").toString();
//            probability = floatVal.get("value").toString();
//        }
//
//        return new FtaEventResult(failureEntity, mtbf, eventType, probability);
//    }
//}
