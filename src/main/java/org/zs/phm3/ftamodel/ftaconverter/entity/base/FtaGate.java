package org.zs.phm3.ftamodel.ftaconverter.entity.base;

import java.util.ArrayList;
import java.util.List;

public abstract class FtaGate extends FtaElement {

    private List<FtaElement> ftaElementList;
    private boolean isFta;
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public FtaGate(Long failureEntityId, String ftaBlockId, boolean isFta) {
        super(failureEntityId, ftaBlockId);
        this.isFta = isFta;
        this.setXmlLink("<gate name=\"g" + getId() + "\"/>\n");
        ftaElementList = new ArrayList<>();
    }


    public void pushElement(FtaElement ftaElement){
        ftaElementList.add(ftaElement);
    }

    public List<FtaElement> getFtaElementList() {
        return ftaElementList;
    }

    public void setFtaElementList(List<FtaElement> ftaElementList) {
        this.ftaElementList = ftaElementList;
    }

    public boolean isFta() {
        return isFta;
    }

    public void setFta(boolean fta) {
        isFta = fta;
    }

    /*
            and, or, xor, nand, nor
     */
    @Override
    public boolean validate() {
        boolean isValidate = false;
        if(ftaElementList.size() >= 2){
            isValidate = true;
        }else{
            setFtaElementError("Gate connective requires 2 or more arguments");
        }
        if(getFailureEntityId() != null){
            isValidate = isFailureEntityExist();
        }
        return isValidate;
    }

    @Override
    public String convert() {
        StringBuilder xml = new StringBuilder();
        xml.append("<define-fault-tree name=\"ft"+ getId() + "\">\n");
        xml.append("<define-gate name=\"g" + getId() + "\">\n");
        xml.append("<label>" + getFailureEntityId() + "</label>\n");
        xml.append("<" + operator + ">\n");
        for(FtaElement element : getFtaElementList()){
            if(element != null) {
                xml.append(element.getXmlLink());
            }
        }
        xml.append("</" + operator + ">\n");
        xml.append("</define-gate>\n");
        xml.append("</define-fault-tree>\n");
        return xml.toString();
    }
}
