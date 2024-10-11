package org.zs.phm3.ftamodel.ftaconverter.entity.event;

import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaEvent;

public class FtaHouseEvent extends FtaEvent {

    private boolean state;

    public FtaHouseEvent(Long failureEntityId, String ftaBlockId, boolean state) {
        super(failureEntityId, ftaBlockId);
        this.state = state;
        this.setXmlLink("<house-event name=\"e" + getId() + "\"/>\n");
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean validate() {
        boolean isValidate = true;
        if(getFailureEntityId() != null){
            isValidate = isFailureEntityExist();
        }
        return isValidate;
    }

    @Override
    public String convert() {
        StringBuilder xml = new StringBuilder("<define-house-event name=\"e" + this.getId() + "\">\n");

        xml.append("<label>" + this.getFailureEntityId() + "</label>\n");
        xml.append("<constant value=\"" + this.state + "\"/>\n");
        xml.append("</define-house-event>");
        return xml.toString();
    }

}
