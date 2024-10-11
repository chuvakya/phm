package org.zs.phm3.service.releability;

public class ReliabilityCalcEntity {
    private String unitId;
    private Double lambda;
    private Long commissioningTime;

    public ReliabilityCalcEntity(String unitId, Double lambda) {
        this.unitId = unitId;
        this.lambda = lambda;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Double getLambda() {
        return lambda;
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }

    public Long getCommissioningTime() {
        return commissioningTime;
    }

    public void setCommissioningTime(Long commissioningTime) {
        this.commissioningTime = commissioningTime;
    }

}
