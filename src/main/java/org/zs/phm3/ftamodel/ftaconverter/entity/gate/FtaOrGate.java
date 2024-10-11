package org.zs.phm3.ftamodel.ftaconverter.entity.gate;

import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;

public class FtaOrGate extends FtaGate {

    public FtaOrGate(Long failureEntityId, String ftaBlockId, boolean isFta) {
        super(failureEntityId, ftaBlockId, isFta);
        setOperator("or");
    }

}
