package org.zs.phm3.models.rpm;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CalcId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 31)
    private String unitId;

/*    @ManyToOne
    @JoinColumn(nullable = false)//(name = "parent")
    private UnitEntity unitId;*/

    private ParamCode paramCode;
//    private Integer paramCode;

    public CalcId(String unitId, ParamCode paramCode) {
        this.unitId = unitId;
        this.paramCode = paramCode;
    }

    public CalcId() {
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setParamCode(ParamCode paramCode) {
        this.paramCode = paramCode;
    }

    public ParamCode getParamCode() {
        return paramCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalcId calcId = (CalcId) o;
        return Objects.equals(getUnitId(), calcId.getUnitId()) &&
                getParamCode() == calcId.getParamCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnitId(), getParamCode());
    }
}