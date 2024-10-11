package org.zs.phm3.models.unit;

import org.zs.phm3.models.basic.ToData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zs.phm3.dto.UnitAttributeDtoOutput;
import org.zs.phm3.models.UomEntity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@JsonIgnoreProperties(value = {"new"})
public class UnitAttribute implements ToData {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitAttribute that = (UnitAttribute) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(booleanValue, that.booleanValue) &&
                Objects.equals(strValue, that.strValue) &&
                Objects.equals(longValue, that.longValue) &&
                Objects.equals(doubleValue, that.doubleValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booleanValue, strValue, longValue, doubleValue);
    }

    @EmbeddedId
    private UnitAttributeKey id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private UnitAttributeType type;

    @Column(name = "bool_v")
    private Boolean booleanValue;

    @Column(name = "str_v")
    private String strValue;

    @Column(name = "long_v")
    private Long longValue;

    @Column(name = "dbl_v")
    private Double doubleValue;

    @ManyToOne
    private UomEntity uomInput;

    @ManyToOne
    private UomEntity uomOutput;

    public UomEntity getUomInput() {
        return uomInput;
    }

    public UomEntity getUomOutput() {
        return uomOutput;
    }

    public void setUomOutput(UomEntity uomOutput) {
        this.uomOutput = uomOutput;
    }

    public void setUomInput(UomEntity uomInput) {
        this.uomInput = uomInput;
    }

    public UnitAttributeKey getId() {
        return id;
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

    public void setId(UnitAttributeKey id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitAttributeType getType() {
        return type;
    }

    public void setType(UnitAttributeType type) {
        this.type = type;
    }

    public UnitAttribute(UnitAttributeKey id, Boolean booleanValue, String name, UomEntity uom) {
        this.id = id;
        this.booleanValue = booleanValue;
        this.uomInput = uom;
        this.name = name;
    }

    public UnitAttribute(UnitAttributeKey id, String strValue, String name, UomEntity uom) {
        this.id = id;
        this.strValue = strValue;
        this.uomInput = uom;
        this.name = name;
    }

    public UnitAttribute(UnitAttributeKey id, Long longValue, String name, UomEntity uom) {
        this.id = id;
        this.longValue = longValue;
        this.uomInput = uom;
        this.name = name;
    }

    public UnitAttribute(UnitAttributeKey id, Double doubleValue, String name, UomEntity uom) {
        this.id = id;
        this.doubleValue = doubleValue;
        this.uomInput = uom;
        this.name = name;
    }

    public UnitAttribute() {
    }


    public String valueToString() {
        String returnedValue = "";
//        return this.getId().toString()+", "+this.getStrValue()+", "+this.getBooleanValue()+", "+this.getDoubleValue();
        if (this.strValue != null)
            returnedValue = this.strValue;
        else if (this.booleanValue != null) {
            returnedValue = this.booleanValue.toString();
        } else if (this.longValue != null) {
            returnedValue = this.longValue.toString();
        } else if (this.doubleValue != null) {
            returnedValue = this.doubleValue.toString();
        }
        return returnedValue;
    }

    @Override
    public UnitAttributeDtoOutput toData() {
        UnitAttributeDtoOutput unitAttributeDto = new UnitAttributeDtoOutput();
        unitAttributeDto.setAttributeKey(this.id.getAttributeKey());
        unitAttributeDto.setUnitId(this.id.getUnitId());
        unitAttributeDto.setBooleanValue(this.booleanValue);
        unitAttributeDto.setDoubleValue(this.doubleValue);
        unitAttributeDto.setLongValue(this.longValue);
        unitAttributeDto.setStrValue(this.strValue);
        unitAttributeDto.setName(this.name);

        unitAttributeDto.setUom(this.getUomInput().getSymbol());

        return unitAttributeDto;
    }
}

