package org.zs.phm3.ftamodel.result;

import org.zs.phm3.failure.FailureEntity;

public class FtaEventResult{
    private FailureEntity failureEntity;
    private Double mtbf;
    private String eventType;
    private String probability;

    public FtaEventResult(FailureEntity failureEntity, Double mtbf, String eventType, String probability) {
        this.failureEntity = failureEntity;
        this.mtbf = mtbf;
        this.eventType = eventType;
        this.probability = probability;
    }

    public FailureEntity getFailureEntity() {
        return failureEntity;
    }

    public void setFailureEntity(FailureEntity failureEntity) {
        this.failureEntity = failureEntity;
    }

    public Double getMtbf() {
        return mtbf;
    }

    public void setMtbf(Double mtbf) {
        this.mtbf = mtbf;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "{" +
                "failureEntity=" + failureEntity +
                ", mtbf=" + mtbf +
                ", eventType='" + eventType + '\'' +
                ", probability='" + probability + '\'' +
                '}';
    }
}
