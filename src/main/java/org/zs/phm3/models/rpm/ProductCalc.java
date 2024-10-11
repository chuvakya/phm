package org.zs.phm3.models.rpm;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "pt_product_calc")
//@JsonIgnoreProperties(value = {"totalValue", "new"})
//@JsonIgnoreProperties(value={ "totalValue" }, allowGetters= true)
//@JsonIgnoreProperties(value = {"new"})
public class ProductCalc {
    @EmbeddedId
    private CalcId calcId;

    @Column
    private Integer productId;

    @Column
//    @ApiModelProperty(hidden = true) swagger
//    @JsonIgnore
    private Double totalValue;

    @Column
    private String parameters;

    @Column
    private CalculatedType calculatedType;

    @Column
    private String parentId;

    @Column
    private Integer quantity;

    @Column
    private Double value;

    public ProductCalc(CalcId calcId, String parentId, Integer quantity, Double value,
                       CalculatedType calculatedType, String parameters) {

        this.calcId = calcId;
        this.parentId = parentId;
        this.quantity = quantity;
        this.value = value;
//        this.totalValue = totalValue;
        this.calculatedType = calculatedType;
        this.parameters = parameters;
    }

    public ProductCalc() {
    }

    public CalcId getCalcId() {
        return calcId;
    }

    public void setCalcId(CalcId calcId) {
        this.calcId = calcId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getTotalValue() {
        return totalValue;
    }

//    @JsonIgnore
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public CalculatedType getCalculatedType() {
        return calculatedType;
    }

    public void setCalculatedType(CalculatedType calculatedType) {
        this.calculatedType = calculatedType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCalc that = (ProductCalc) o;
        return Objects.equals(getCalcId(), that.getCalcId()) &&
                getCalculatedType() == that.getCalculatedType() &&
                Objects.equals(getParentId(), that.getParentId()) &&
                Objects.equals(getQuantity(), that.getQuantity()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCalcId(), getCalculatedType(), getParentId(), getQuantity(), getValue());
    }
}
