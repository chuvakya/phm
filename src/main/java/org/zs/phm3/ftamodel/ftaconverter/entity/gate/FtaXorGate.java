package org.zs.phm3.ftamodel.ftaconverter.entity.gate;


import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;

public class FtaXorGate extends FtaGate {
    public FtaXorGate(Long failureEntityId, String ftaBlockId, boolean isFta) {
        super(failureEntityId, ftaBlockId, isFta);
        setOperator("xor");
    }


}
