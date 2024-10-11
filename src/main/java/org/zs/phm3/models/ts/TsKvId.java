package org.zs.phm3.models.ts;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
public class TsKvId implements Serializable {

    @Transient
    private static final long serialVersionUID = -4089175869616037523L;

    private String deviceId = "";
    private String unitId;
    private String key;
    private long ts;

    public TsKvId(String unitId, String key, long ts) {
        this.unitId = unitId;
        this.key = key;
        this.ts = ts;
    }

    public TsKvId() {
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getKey() {
        return key;
    }

    public long getTs() {
        return ts;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }



}