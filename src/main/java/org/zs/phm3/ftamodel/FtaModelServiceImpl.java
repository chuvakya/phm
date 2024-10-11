package org.zs.phm3.ftamodel;


import org.zs.phm3.failure.FailureEntity;
import org.zs.phm3.failure.FailureRepository;
import org.zs.phm3.failure.FailureService;
import org.zs.phm3.ftamodel.result.FtaEventResult;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.repository.calculation.CalculationAttrRepository;
import org.zs.phm3.repository.unit.UnitAttributeRepository;
import org.zs.phm3.util.mapping.PhmUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

@Transactional
@Service
public class FtaModelServiceImpl implements FtaModelService {


    @Value(value = "${fta.url-fta-probability}")
    private String urlProbability;

    @Autowired
    private FtaModelRepository ftaModelRepository;

    @Autowired
    private CalculationAttrRepository calculationAttrRepository;

    @Autowired
    private FailureService failureService;

    @Autowired
    private FailureRepository failureRepository;

    @Autowired
    private UnitAttributeRepository unitAttributeRepository;


    @Override
    public FtaModelEntity save(FtaModelEntity ftaModelEntity) {
        return ftaModelRepository.save(ftaModelEntity);
    }

    @Override
    public FtaModelEntity update(FtaModelEntity ftaModelEntity) {
        FtaModelEntity ftaModelEntityForSave = ftaModelRepository.findByProjectId(ftaModelEntity.getProjectId());
        ftaModelEntityForSave.setBody(ftaModelEntity.getBody());
        return ftaModelRepository.save(ftaModelEntityForSave);
    }

    @Override
    public void deleteByProjectId(Long projectId) {
        ftaModelRepository.deleteByProjectId(projectId);
    }

    @Override
    public FtaModelEntity findByProjectId(Long projectId) {
        return ftaModelRepository.findByProjectId(projectId);
    }


    @Override
    public String calculate(Long projectId,  int missionTime, int limitOrder) throws ParserConfigurationException,
            TransformerException, SAXException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        writeProbabilityInFtaModel(projectId);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlProbability + "?mission-time=" + missionTime + "&limit-order=" + limitOrder);
        FtaModelEntity ftaModel = ftaModelRepository.findByProjectId(projectId);
        StringEntity entity = new StringEntity(ftaModel.getBody());
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);

        String xmlStr = EntityUtils.toString(response.getEntity());
        String jsonFromXml = XML.toJSONObject(xmlStr, true).toString();
        JSONObject json = (JSONObject) parser.parse(jsonFromXml);
        JSONObject result = (JSONObject) ((JSONObject) json.get("report")).get("results");
        JSONObject mapping = (JSONObject) parser.parse(findByProjectId(projectId).getMapping());

        result = (JSONObject) parser.parse(result.toJSONString().replace("name", "failureEntity"));

        JSONArray eventList = new JSONArray();
        for(FtaEventResult ftaEventResult: getEventList(projectId)){
            eventList.add((JSONObject) parser.parse(PhmUtil.objectToJsonString(ftaEventResult)));
        }
        result.put("event-list",  eventList);

        String resStr = result.toJSONString();

        for (Object key : mapping.keySet()) {
            FailureEntity failureEntity = failureRepository.findById(Long.valueOf(mapping.get(key).toString())).get();
            resStr = resStr.replace("\""+ key.toString() + "\"", PhmUtil.objectToJsonString(failureEntity));
        }

        return resStr;
    }

    // получает fta model по projectId
    // подставляет вероятности отказа в события из calculation attribute, а если вероятности нет берет из failureEntity
    private void writeProbabilityInFtaModel(Long projectId) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        FtaModelEntity ftaModel = ftaModelRepository.findByProjectId(projectId);
        Document document = builder.parse(new InputSource(new StringReader(ftaModel.getBody())));
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("define-basic-event");

        for (int i = 0; i < nList.getLength(); i++){
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Element eFloat = (Element) eElement.getElementsByTagName("float").item(0);
                Long failureId = Long.valueOf(eElement.getElementsByTagName("label").item(0).getTextContent());
                String unitId = PhmUtil.toShortUUID(failureService.findById(failureId).getUnitId());
                Double probability = calculationAttrRepository.getByAttrType_NameAndNameAndUnitId(
                        "Reliability Service","Probability", unitId).getValue();

                // если нет вероятности в calculationAttr то взять из failureEntity
                if(probability == null){
                    probability = failureService.findById(failureId).getBasicProbability();
                }
                if(probability == null){
                    probability = 0D;
                }
                eFloat.setAttribute("value", probability.toString());
            }
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        StringWriter sw = new StringWriter();
        t.transform(new DOMSource(document), new StreamResult(sw));

        ftaModel.setBody(sw.toString());
        ftaModelRepository.save(ftaModel);
    }

    private ArrayList<FtaEventResult> getEventList(Long projectId){
        ArrayList<FtaEventResult> ftaEventResultArrayList = new ArrayList<>();
        String xmlBody = ftaModelRepository.findByProjectId(projectId).getBody();
        org.json.JSONObject json;
        try {
            json = XML.toJSONObject(xmlBody, true);
            JSONParser parser = new JSONParser();
            JSONObject simpJson = (JSONObject) parser.parse(String.valueOf(json));
            JSONObject opsaMef = (JSONObject) simpJson.get("opsa-mef");
            JSONObject modelData = (JSONObject) opsaMef.get("model-data");

            if(modelData.get("define-basic-event") instanceof JSONArray){
                JSONArray basicEvents = (JSONArray) modelData.get("define-basic-event");
                ftaEventResultArrayList.addAll(getEventResultList(basicEvents));
            } else if(modelData.get("define-basic-event") instanceof JSONObject){
                JSONObject basicEvent = (JSONObject) modelData.get("define-basic-event");
                ftaEventResultArrayList.addAll(getEventResultList(basicEvent));
            }
            if(modelData.get("define-house-event") instanceof JSONArray){
                JSONArray houseEvents = (JSONArray) modelData.get("define-house-event");
                ftaEventResultArrayList.addAll(getEventResultList(houseEvents));
            } else if(modelData.get("define-house-event") instanceof JSONObject){
                JSONObject houseEvent = (JSONObject) modelData.get("define-house-event");
                ftaEventResultArrayList.addAll(getEventResultList(houseEvent));
            }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

        return ftaEventResultArrayList;
    }

    private ArrayList<FtaEventResult> getEventResultList(JSONObject obj){
        ArrayList<FtaEventResult> ftaEventResults = new ArrayList<>();
        ftaEventResults.add(makeFtaEventResult(obj));
        return ftaEventResults;
    }

    private ArrayList<FtaEventResult> getEventResultList(JSONArray arr){
        ArrayList<FtaEventResult> ftaEventResults = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++){
            ftaEventResults.add(makeFtaEventResult((JSONObject) arr.get(i)));
        }
        return ftaEventResults;
    }

    private FtaEventResult makeFtaEventResult(JSONObject obj){
        FailureEntity failureEntity = failureRepository.findById(Long.valueOf(obj.get("label").toString())).get();
        Double mtbf = unitAttributeRepository.findById(new UnitAttributeKey(failureEntity.getUnitId(), "MTBF"))
                .get().getDoubleValue();
        JSONObject attributes = (JSONObject) obj.get("attributes");
        JSONObject constant = (JSONObject) obj.get("constant");
        String eventType = null;
        String probability = null;

        if((attributes == null) && (constant == null)){
            eventType = "basic";
            JSONObject floatVal = (JSONObject) obj.get("float");
            probability = floatVal.get("value").toString();
        } else if((attributes == null) && (constant != null)){
            eventType = "house";
            JSONObject floatVal = (JSONObject) obj.get("constant");
            probability = floatVal.get("value").toString();
        } else if(attributes != null){
            JSONObject attribute = (JSONObject) attributes.get("attribute");
            JSONObject floatVal = (JSONObject) obj.get("float");
            eventType = attribute.get("value").toString();
            probability = floatVal.get("value").toString();
        }

        return new FtaEventResult(failureEntity, mtbf, eventType, probability);
    }

}
