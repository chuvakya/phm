package org.zs.phm3.models.fmea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_failure_detection_scores")
public class FailureDetectionScore {
    @Id
    private String id;

    @Column(length = 128)//DDL
    private String probabilityFaulureDetection;

    private String detectionScore;

    public FailureDetectionScore(String id, String probabilityFaulureDetection, String detectionScore) {
        this.id = id;
        this.probabilityFaulureDetection = probabilityFaulureDetection;
        this.detectionScore = detectionScore;
    }

    public FailureDetectionScore() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProbabilityFaulureDetection() {
        return probabilityFaulureDetection;
    }

    public void setProbabilityFaulureDetection(String probabilityFaulureDetection) {
        this.probabilityFaulureDetection = probabilityFaulureDetection;
    }

    public String getDetectionScore() {
        return detectionScore;
    }

    public void setDetectionScore(String detectionScore) {
        this.detectionScore = detectionScore;
    }
}
