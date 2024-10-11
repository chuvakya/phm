package org.zs.phm3.models.ts;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TsKvAttributeId implements Serializable {

    private String unitId;
    private String attributeKey;

    public TsKvAttributeId() {}

    public TsKvAttributeId(String unitId, String attributeKey) {
        this.unitId = unitId;
        this.attributeKey = attributeKey;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }
}
