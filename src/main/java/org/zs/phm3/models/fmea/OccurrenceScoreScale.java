package org.zs.phm3.models.fmea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_occurrence_score_scales")

public class OccurrenceScoreScale {
    @Id
    private String id;
    @Column(length = 256)//DDL
    private String defectFrequence;

    private Integer occurrenceScore;

    public OccurrenceScoreScale(String id, String defectFrequence, Integer occurrenceScore) {
        this.id = id;
        this.defectFrequence = defectFrequence;
        this.occurrenceScore = occurrenceScore;
    }

    public OccurrenceScoreScale() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefectFrequence() {
        return defectFrequence;
    }

    public void setDefectFrequence(String defectFrequence) {
        this.defectFrequence = defectFrequence;
    }

    public Integer getOccurrenceScore() {
        return occurrenceScore;
    }

    public void setOccurrenceScore(Integer occurrenceScore) {
        this.occurrenceScore = occurrenceScore;
    }
}
