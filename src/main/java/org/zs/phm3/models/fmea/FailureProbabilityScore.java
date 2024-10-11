package org.zs.phm3.models.fmea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_failure_probability_scores")

public class FailureProbabilityScore {

    @Id
    private String id;

    @Column(length = 128)//DDL
    private String expectedProbabilityFaulure;

    private Integer probabilityScore;

    public FailureProbabilityScore() {
    }

    public FailureProbabilityScore(String id, String expectedProbabilityFaulure, Integer probabilityScore) {
        this.id = id;
        this.expectedProbabilityFaulure = expectedProbabilityFaulure;
        this.probabilityScore = probabilityScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpectedProbabilityFaulure() {
        return expectedProbabilityFaulure;
    }

    public void setExpectedProbabilityFaulure(String expectedProbabilityFaulure) {
        this.expectedProbabilityFaulure = expectedProbabilityFaulure;
    }

    public Integer getProbabilityScore() {
        return probabilityScore;
    }

    public void setProbabilityScore(Integer probabilityScore) {
        this.probabilityScore = probabilityScore;
    }
}
