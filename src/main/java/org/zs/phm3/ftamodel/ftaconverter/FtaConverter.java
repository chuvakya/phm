package org.zs.phm3.ftamodel.ftaconverter;


import org.zs.phm3.ftamodel.FtaDiagramEntity;
import org.zs.phm3.ftamodel.ftaconverter.entity.base.ExpressionType;
import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaElement;
import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaEvent;
import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;
import org.zs.phm3.ftamodel.ftaconverter.entity.event.FtaBasicEvent;
import org.zs.phm3.ftamodel.ftaconverter.entity.event.FtaConditionalEvent;
import org.zs.phm3.ftamodel.ftaconverter.entity.event.FtaHouseEvent;
import org.zs.phm3.ftamodel.ftaconverter.entity.event.FtaUndevelopedEvent;
import org.zs.phm3.ftamodel.ftaconverter.entity.gate.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class FtaConverter {
    private boolean isValidate;
    private StringBuilder xmlFta;
    private StringBuilder ftaError;
    private List<FtaElement> ftaElementContainer;
    private JSONObject jsonObj;


    public boolean isValidate() {
        return isValidate;
    }

    private List<String> ftaErrorList;

    public FtaConverter(FtaDiagramEntity ftaDiagramEntity) throws ParseException {
        ftaElementContainer = new ArrayList<>();
        ftaErrorList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(buildFtaDiagram3(ftaDiagramEntity));
        jsonObj = (JSONObject) obj;
        xmlFta = new StringBuilder();
        ftaError = new StringBuilder();
        isValidate = true;
    }


    private String buildFtaDiagram3(FtaDiagramEntity ftaDiagramEntity) throws ParseException {
        String jsonNode ="";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(ftaDiagramEntity.getBody().toString());
        JSONObject jsonObg = (JSONObject) obj;
        jsonNode += jsonObg.get("nodes");

        for (FtaDiagramEntity ftaDiagramEntity1: ftaDiagramEntity.getChild()) {
            obj = parser.parse(ftaDiagramEntity1.getBody().toString());
            jsonObg = (JSONObject) obj;
            jsonNode = jsonNode.substring(0, jsonNode.length()-1);
            jsonNode += ", " + jsonObg.get("nodes").toString().substring(1);
        }

        System.out.println("{ \"nodes\": " + jsonNode + "}");
        return "{ \"nodes\": " + jsonNode + "}";
    }

    private FtaElement getFtaElementByFtaBlockId(String ftaBlockId){
        for(FtaElement ftaElement : ftaElementContainer){
            if (ftaElement.getFtaBlockId().equals(ftaBlockId)) {
                return ftaElement;
            }
        }
        return null;
    }

    public String getXmlFta(){
        JSONArray nodes = (JSONArray) jsonObj.get("nodes");

        for (Object jsonObj : nodes) {
            JSONObject obj = (JSONObject) jsonObj;
            JSONObject addInfo = (JSONObject) obj.get("addInfo");
            JSONObject failureIdEntity = (JSONObject) addInfo.get("failureIdEntity");

            if (addInfo.get("ftaBlockType").toString().equals("Event")) {
                switch (addInfo.get("entityType").toString()) {
                    case "basic":
                        ftaElementContainer.add(new FtaBasicEvent((Long) failureIdEntity.get("id") , addInfo.get("ftaBlockId").toString(), true, ExpressionType.CONSTANT, 0));
                        break;
                    case "conditional":
                        ftaElementContainer.add(new FtaConditionalEvent((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true, ExpressionType.CONSTANT, 0));
                        break;
                    case "undeveloped":
                        ftaElementContainer.add(new FtaUndevelopedEvent((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true, ExpressionType.CONSTANT, 0));
                        break;
                    case "house":
                        ftaElementContainer.add(new FtaHouseEvent((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), false));
                        break;  // какой state по умолчанию?
                }
            }
            if (addInfo.get("ftaBlockType").toString().equals("Gate")) {
                switch (addInfo.get("entityType").toString()) {
                    case "and":
                        ftaElementContainer.add(new FtaAndGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                    case "atleast":
                        ftaElementContainer.add(new FtaAtleastGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true, 2));
                        break;  // будет приходить из фронта
                    case "nand":
                        ftaElementContainer.add(new FtaNandGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                    case "nor":
                        ftaElementContainer.add(new FtaNorGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                    case "not":
                        ftaElementContainer.add(new FtaNotGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                    case "nullGate":
                        ftaElementContainer.add(new FtaNullGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                    case "or":
                        ftaElementContainer.add(new FtaOrGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                    case "xor":
                        ftaElementContainer.add(new FtaXorGate((Long) failureIdEntity.get("id"), addInfo.get("ftaBlockId").toString(), true));
                        break;
                }
            }
        }



        // если существует родитель получить родителя по ftaBlockId и поместить элемент в родителя
        for(Object jsonObj : nodes){
            JSONObject obj = (JSONObject) jsonObj;
            JSONObject addInfo = (JSONObject) obj.get("addInfo");
            if(addInfo.containsKey("parentEntity")) {
                if (addInfo.get("parentEntity") != null) {
                    FtaGate ftaGate = (FtaGate) getFtaElementByFtaBlockId(addInfo.get("parentEntity").toString());
                    ftaGate.pushElement(getFtaElementByFtaBlockId(addInfo.get("ftaBlockId").toString()));
                }
            }
        }

        xmlFta.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlFta.append("<opsa-mef>\n");

        for(FtaElement ftaElement : ftaElementContainer){
            if(ftaElement instanceof FtaGate){
                if(ftaElement.validate()) {
                    xmlFta.append(ftaElement.convert());
                } else{
                    ftaErrorList.add("{\"" + ftaElement.getFtaBlockId() + "\": \"" + ftaElement.getFtaElementError() + "\"}");
                }
            }
        }

        if(ftaErrorList.size() > 0){
            isValidate = false;
            ftaError.append("{\"ftaError\": [");
            for(int i = 0; i < ftaErrorList.size(); i++){
                ftaError.append(ftaErrorList.get(i));
                if (i != ftaErrorList.size() - 1){
                    ftaError.append(",\n");
                }
            }
            ftaError.append("]}");
            return ftaError.toString();
        }

        xmlFta.append("<model-data>\n");

        for(FtaElement ftaElement : ftaElementContainer){
            if(ftaElement instanceof FtaEvent){
                xmlFta.append(ftaElement.convert());
            }
        }

        xmlFta.append("</model-data>\n");
        xmlFta.append("</opsa-mef>");

        return xmlFta.toString();
    }


    public String createMapping(){
        JSONObject obj = new JSONObject();
        for (FtaElement ftaElement: ftaElementContainer) {
            String symbol = (ftaElement instanceof FtaEvent) ? "e" : "g";
            obj.put(symbol + ftaElement.getId(), ftaElement.getFailureEntityId());
        }
        return obj.toJSONString();
    }

}

