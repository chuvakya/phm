package org.zs.phm3.ftamodel.ftaconverter.entity.event;

import org.zs.phm3.ftamodel.ftaconverter.entity.base.ExpressionType;

import static org.zs.phm3.ftamodel.ftaconverter.entity.base.ExpressionType.CONSTANT;
import static org.zs.phm3.ftamodel.ftaconverter.entity.base.ExpressionType.EXPONENTIAL;

public class FtaUndevelopedEvent extends FtaBasicEvent {

    public FtaUndevelopedEvent(Long failureEntityId, String ftaBlockId, boolean isExpression, ExpressionType expressionType, double value) {
        super(failureEntityId, ftaBlockId, isExpression, expressionType, value);
    }

    @Override
    public String convert() {
        StringBuilder xml = new StringBuilder("<define-basic-event name=\"e" + this.getId() + "\">\n");
        xml.append("<label>" + this.getFailureEntityId() + "</label>\n");
        xml.append("<attributes>\n");
        xml.append("<attribute name=\"flavor\" value=\"undeveloped\"/>\n");
        xml.append("</attributes>\n");
        if(this.isExpression()){
            if(this.getExpressionType() == EXPONENTIAL){
                xml.append("<exponential>\n");
                xml.append("<float value=\""+ this.getValue() +"\"/>\n");
                xml.append("</exponential>\n");
            }
            if(this.getExpressionType() == CONSTANT){
                xml.append("<float value=\""+ this.getValue() +"\"/>\n");
            }
        }
        xml.append("</define-basic-event>\n");
        return xml.toString();
    }
}
