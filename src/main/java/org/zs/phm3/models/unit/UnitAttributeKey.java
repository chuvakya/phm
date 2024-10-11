package org.zs.phm3.models.unit;

import org.zs.phm3.data.UUIDConverter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UnitAttributeKey implements Serializable {
    //    @Column(name = ENTITY_ID_COLUMN)
    private String unitId;
    //    @Column(name = ATTRIBUTE_TYPE_COLUMN)
//    private String attributeType;
//    @Column(name = ATTRIBUTE_KEY_COLUMN)
    private String attributeKey;

    public UnitAttributeKey(String unitId, String attributeKey) {
        if (unitId.length() == 36)
            this.unitId = UUIDConverter.fromTimeUUID(UUID.fromString(unitId));
        else if (unitId.length() == 31)
            this.unitId = unitId;
        this.attributeKey = attributeKey;
    }

    public UnitAttributeKey() {
    }

    public String getUnitId() {
        return UUIDConverter.fromString(unitId).toString();
//        return unitId;
    }

    public void setUnitId(String unitId) {
//        this.unitId = unitId;
        this.unitId = UUIDConverter.fromTimeUUID(UUID.fromString(unitId));
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }
}
