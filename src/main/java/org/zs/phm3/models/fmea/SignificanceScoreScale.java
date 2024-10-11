package org.zs.phm3.models.fmea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_significance_score_scales")

public class SignificanceScoreScale {
    @Id
    private String id;
    @Column(length=256)//DDL
    private String description;
    private Integer score;

    public SignificanceScoreScale(String id, String description, Integer score) {
        this.id = id;
        this.description = description;
        this.score=score;
    }

    public SignificanceScoreScale() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean isNew(){
        return id == null;
    };
}
