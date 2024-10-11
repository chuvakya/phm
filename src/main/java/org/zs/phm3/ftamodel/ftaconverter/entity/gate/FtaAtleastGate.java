package org.zs.phm3.ftamodel.ftaconverter.entity.gate;


import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaElement;
import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;

public class FtaAtleastGate extends FtaGate {
    private int min;

    public FtaAtleastGate(Long failureEntityId,  String ftaBlockId, boolean isFta, int min) {
        super(failureEntityId, ftaBlockId, isFta);
        this.min = min;
    }

    @Override
    public boolean validate(){
        boolean isValidate = false;
        if((getFtaElementList().size() >= 3)&&((min >= 2)&&(min <= getFtaElementList().size() - 1))){
            isValidate = true;
        } else {
            setFtaElementError("Gate connective requires at-least 3 or more arguments. Argument min must be > 2 and < n-1. ");
        }
        if(getFailureEntityId() != null){
            isValidate = isFailureEntityExist();
        }
        return isValidate;
    }

    @Override
    public String convert() {
        StringBuilder xml = new StringBuilder("<define-fault-tree name=\"ft"+ getId() + "\">\n");
        xml.append("<define-gate name=\"g" + getId() + "\">\n");
        xml.append("<label>" + getFailureEntityId() + "</label>\n");
        xml.append("<atleast min=\"" + this.min + "\">\n");
        for(FtaElement element : getFtaElementList()){
            xml.append(element.getXmlLink());
        }
        xml.append("</atleast>\n");
        xml.append("</define-gate>\n");
        xml.append("</define-fault-tree>\n");
        return xml.toString();
    }

}
