package org.zs.phm3.models.unit;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
//@Table(name = "ts_kv")

public class UnitModelAttribute {
//    @EmbeddedId
//    private AttributeKey id;
@EmbeddedId
    private UnitModelAttributeKey id;

    @Column(name = "bool_v")
    private Boolean booleanValue;

    @Column(name = "str_v")
    private String strValue;

    @Column(name = "long_v")
    private Long longValue;

    @Column(name = "dbl_v")
    private Double doubleValue;

    public UnitModelAttributeKey getId() {
        return id;
    }

/*    public void setTsKvId(UnitModelAttributeKey id) {
        this.id = id;
    }*/

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public UnitModelAttribute(UnitModelAttributeKey id, Boolean booleanValue) {
        this.id = id;
        this.booleanValue = booleanValue;
    }
    public UnitModelAttribute(UnitModelAttributeKey id, String strValue) {
        this.id = id;
        this.strValue = strValue;
    }
    public UnitModelAttribute(UnitModelAttributeKey id, Long longValue) {
        this.id = id;
        this.longValue = longValue;
    }
    public UnitModelAttribute(UnitModelAttributeKey id, Double doubleValue) {
        this.id = id;
        this.doubleValue = doubleValue;
    }

    public UnitModelAttribute(UnitModelAttributeKey unitModelAttributeKey) {
    }

    @Override
    public String toString() {
        return this.getId().toString()+", "+this.getStrValue()+", "+this.getBooleanValue();
    }

    public UnitModelAttribute() {
    }
}
