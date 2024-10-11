package org.zs.phm3.ftamodel.ftaconverter.entity.gate;

import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaElement;

public class FtaNullGate extends FtaNotGate {
    public FtaNullGate(Long failureEntityId, String ftaBlockId, boolean isFta) {
        super(failureEntityId, ftaBlockId, isFta);
    }

    @Override
    public String convert() {
        StringBuilder xml = new StringBuilder("<define-fault-tree name=\"ft"+ getId() + "\">\n");
        xml.append("<define-gate name=\"g" + getId() + "\">\n");
        xml.append("<label>" + getFailureEntityId() + "</label>\n");
        for(FtaElement element : getFtaElementList()){
            xml.append(element.getXmlLink());
        }
        xml.append("</define-gate>\n");
        xml.append("</define-fault-tree>\n");
        return xml.toString();
    }
}
