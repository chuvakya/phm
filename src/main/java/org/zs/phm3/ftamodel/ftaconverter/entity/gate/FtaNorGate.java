package org.zs.phm3.ftamodel.ftaconverter.entity.gate;


import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;

public class FtaNorGate extends FtaGate {
    public FtaNorGate(Long failureEntityId, String ftaBlockId, boolean isFta) {
        super(failureEntityId, ftaBlockId, isFta);
        setOperator("nor");
    }


}
