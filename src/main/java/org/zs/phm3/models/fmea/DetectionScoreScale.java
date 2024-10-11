package org.zs.phm3.models.fmea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_detection_score_scales")

public class DetectionScoreScale {//} extends IdStringEntity {

    @Id
    String id;

    @Column(unique = true, nullable = false, length = 256)//DDL
    private String detectionCriteria;

    private Integer detectionScore;

    public DetectionScoreScale() {
    }

    public DetectionScoreScale(String id, String detectionCriteria, Integer detectionScore) {
        this.id = id;
        this.detectionCriteria = detectionCriteria;
        this.detectionScore = detectionScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetectionCriteria() {
        return detectionCriteria;
    }

    public void setDetectionCriteria(String detectionCriteria) {
        this.detectionCriteria = detectionCriteria;
    }

    public Integer getDetectionScore() {
        return detectionScore;
    }

    public void setDetectionScore(Integer detectionScore) {
        this.detectionScore = detectionScore;
    }
}
