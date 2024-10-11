package org.zs.phm3.ftamodel.ftaconverter.entity.event;


import org.zs.phm3.ftamodel.ftaconverter.entity.base.ExpressionType;
import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaEvent;



import static org.zs.phm3.ftamodel.ftaconverter.entity.base.ExpressionType.*;


public class FtaBasicEvent extends FtaEvent {

    private boolean isExpression;
    private ExpressionType expressionType;
    private double value;



    public FtaBasicEvent(Long failureEntityId, String ftaBlockId, boolean isExpression, ExpressionType expressionType, double value) {
        super(failureEntityId, ftaBlockId);
        this.isExpression = isExpression;
        this.expressionType = expressionType;
        this.value = value;
        this.setXmlLink("<basic-event name=\"e" + getId() + "\"/>\n");
    }

    public boolean isExpression() {
        return isExpression;
    }

    public void setExpression(boolean expression) {
        isExpression = expression;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean validate() {
        boolean isValidate = false;

        if(isExpression){
            if(expressionType == CONSTANT){
                if(value >= 0 && value <= 1){
                    isValidate = true;
                }
                setFtaElementError("Value of expression must be >= 0 and <= 1");
            } else if(expressionType == EXPONENTIAL){
                // теоритически возможно >= 0
                if(value > 0){
                    isValidate = true;
                }
                setFtaElementError("Rate of expression must be >= 0");
            }
        }

        if(getFailureEntityId() != null){
            isValidate = isFailureEntityExist();
        }

        return isValidate;
    }


    @Override
    public String convert(){
        StringBuilder xml = new StringBuilder();
        xml.append("<define-basic-event name=\"e" + this.getId() + "\">\n");
        xml.append("<label>" + this.getFailureEntityId() + "</label>\n");
        if(this.isExpression){
            if(expressionType == EXPONENTIAL){
                xml.append("<exponential>\n");
                xml.append("<float value=\""+ this.value +"\"/>\n");
                xml.append("</exponential>\n");
            }
            if(expressionType == CONSTANT){
                xml.append("<float value=\""+ this.value +"\"/>\n");
            }
        }
        xml.append("</define-basic-event>\n");
        return xml.toString();
    }

}

