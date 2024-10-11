package org.zs.phm3.dto;

import org.zs.phm3.models.basic.ToDao;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.repository.unit.UnitAttributeTypeRepository;

//@Component
public class UnitAttributeDtoInput implements ToDao<UnitAttribute> {
    private String unitId;
    private Integer type;
    private String attributeKey;
    private Boolean booleanValue;
    private String strValue;
    private Long longValue;
    private Double doubleValue;
    private Integer uomInput;
    private Integer uomOutput;
//    private String customUnitId;
    private String name;

    @Autowired
    UnitAttributeTypeRepository unitAttributeTypeRepository;

    public UnitAttributeDtoInput() {
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

    public Integer getUomInput() {
        return uomInput;
    }

    public void setUomInput(Integer uomInput) {
        this.uomInput = uomInput;
    }

    public Integer getUomOutput() {
        return uomOutput;
    }

    public void setUomOutput(Integer uomOutput) {
        this.uomOutput = uomOutput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public UnitAttribute toDaoData() throws PhmException {
        UnitAttribute unitAttribute = new UnitAttribute();
        unitAttribute.setId(new UnitAttributeKey(this.unitId, this.attributeKey));

        unitAttribute.setBooleanValue(this.booleanValue);
        unitAttribute.setDoubleValue(this.doubleValue);
        unitAttribute.setLongValue(this.longValue);
        unitAttribute.setStrValue(this.strValue);

        unitAttribute.setName(this.name);
        return unitAttribute;
    }
}
