package org.zs.phm3.dto;

public class UnitAttributeDtoOutput {
    public void setUom(String uom) {
        this.uom = uom;
    }//} implements ToDao {
    private String unitId;
    private String attributeKey;
    private Boolean booleanValue;
    private String strValue;
    private Long longValue;
    private Double doubleValue;
    //    private Integer uomInput;
    private String uom;
    private String uomOutput;
    private String name;
    private String customUnitId;
    public UnitAttributeDtoOutput() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUom() {
        return uom;
    }

    public String getCustomUnitId() {
        return customUnitId;
    }

    public String getUomOutput() {
        return uomOutput;
    }

    public void setUomOutput(String uomOutput) {
        this.uomOutput = uomOutput;
    }

    public void setCustomUnitId(String customUnitId) {
        this.customUnitId = customUnitId;
    }

}
