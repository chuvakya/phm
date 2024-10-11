package org.zs.phm3.models.rpm;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "pt_total_calc")
public class TotalCalc {

    @EmbeddedId
    private CalcId calcId;

    @Column
    private Integer quantity;

    @Column
    private Double value;

    public TotalCalc(CalcId calcId, Integer quantity, Double value) {
        this.calcId = calcId;
        this.quantity = quantity;
//        this.paramCode = paramCode;
        this.value = value;
    }

    public TotalCalc() {
    }

    public CalcId getCalcId() {
        return calcId;
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

    public void setCalcId(CalcId calcId) {
        this.calcId = calcId;
    }

}
