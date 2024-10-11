package org.zs.phm3.ftamodel.ftaconverter.entity.gate;


import org.zs.phm3.ftamodel.ftaconverter.entity.base.FtaGate;

public class FtaNandGate extends FtaGate {
    public FtaNandGate(Long failureEntityId, String ftaBlockId, boolean isFta) {
        super(failureEntityId, ftaBlockId, isFta);
        setOperator("nand");
    }


}
