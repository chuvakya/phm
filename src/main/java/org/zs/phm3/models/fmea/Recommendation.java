package org.zs.phm3.models.fmea;

import org.zs.phm3.models.basic.IdEntityDateTimeCreated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fmea_recommendatios")
public class Recommendation  extends IdEntityDateTimeCreated {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private FailureMode failureMode;
    @Transient
    private Integer failureModeId;
    private String RecommendationCode;
    @Column(unique = true, nullable = false, length = 512)//DDL
    private String text;

    public Recommendation() {
    }

    public Recommendation(Integer failureModeId, String recommendationCode, String text) {
        this.failureModeId = failureModeId;
        RecommendationCode = recommendationCode;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FailureMode getFailureMode() {
        return failureMode;
    }

    public void setFailureMode(FailureMode failureMode) {
        this.failureMode = failureMode;
    }

    public Integer getFailureModeId() {
        return failureModeId;
    }

    public void setFailureModeId(Integer failureModeId) {
        this.failureModeId = failureModeId;
    }

    public String getRecommendationCode() {
        return RecommendationCode;
    }

    public void setRecommendationCode(String recommendationCode) {
        RecommendationCode = recommendationCode;
    }

    @PrePersist
    private void PrePersist() {
        dateCreated = LocalDateTime.now();
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    private void onLoadAndUpdate() {
        failureModeId=this.getFailureMode().getId();
    }
}
