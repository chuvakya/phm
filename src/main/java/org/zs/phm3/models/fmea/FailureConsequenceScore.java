package org.zs.phm3.models.fmea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_failure_consequence_scores")
public class FailureConsequenceScore {
    @Id
    private String id;

    @Column(length = 128)//DDL
    private String consequenceScore;

    public FailureConsequenceScore(String id, String consequenceScore) {
        this.id = id;
        this.consequenceScore = consequenceScore;
    }

    public FailureConsequenceScore() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsequenceScore() {
        return consequenceScore;
    }

    public void setConsequenceScore(String consequenceScore) {
        this.consequenceScore = consequenceScore;
    }
}
