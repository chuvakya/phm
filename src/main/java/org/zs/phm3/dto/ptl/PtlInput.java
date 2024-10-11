package org.zs.phm3.dto.ptl;

public class PtlInput {
    private Long modelPtlId;
    private Integer unitTypePtlId;

    public PtlInput(Long modelPtlId, Integer unitTypePtlId) {
        this.modelPtlId = modelPtlId;
        this.unitTypePtlId = unitTypePtlId;
    }

    public Long getModelPtlId() {
        return modelPtlId;
    }

    public void setModelPtlId(Long modelPtlId) {
        this.modelPtlId = modelPtlId;
    }

    public Integer getUnitTypePtlId() {
        return unitTypePtlId;
    }

    public void setUnitTypePtlId(Integer unitTypePtlId) {
        this.unitTypePtlId = unitTypePtlId;
    }

    public PtlInput() {
    }
}
