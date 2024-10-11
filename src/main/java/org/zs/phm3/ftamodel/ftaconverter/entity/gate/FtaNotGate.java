package org.zs.phm3.ftamodel.ftaconverter.entity.gate;

import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;

public class FtaNotGate extends FtaGate {
    public FtaNotGate(Long failureEntityId, String ftaBlockId,boolean isFta) {
        super(failureEntityId, ftaBlockId, isFta);
        setOperator("not");
    }

    @Override
    public boolean validate(){
        boolean isValidate = false;
        if(getFtaElementList().size() == 1){
            isValidate = true;
        } else {
            setFtaElementError("Gate connective requires single argument");
        }
        if(getFailureEntityId() != null){
            isValidate = isFailureEntityExist();
        }
        return isValidate;
    }
}
