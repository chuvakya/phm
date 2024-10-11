package org.zs.phm3.models.unit;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UnitModelAttributeKey implements Serializable {
//    @Column(name = ENTITY_ID_COLUMN)
    private Integer unitModelId;
//    @Column(name = ATTRIBUTE_TYPE_COLUMN)
//    private String attributeType;
//    @Column(name = ATTRIBUTE_KEY_COLUMN)
    private String attributeKey;

    public Integer getUnitModelId() {
        return unitModelId;
    }

    public void setUnitModelId(Integer unitModelId) {
        this.unitModelId = unitModelId;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public UnitModelAttributeKey(Integer unitModelId, String attributeKey) {
        this.unitModelId = unitModelId;
        this.attributeKey = attributeKey;
    }

    public UnitModelAttributeKey() {
    }
    @Override
    public String toString(){
        return  unitModelId.toString()+" / "+attributeKey;
    }
}
