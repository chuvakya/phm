package org.zs.phm3.models.fmea;

import org.zs.phm3.models.basic.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_failure_detection_methods")
//        @JsonIgnoreProperties(value = {"id", "new"})

public class FailureDetectionMethod extends IdEntity {
    @Column(unique = true, nullable = false, length = 128)//DDL
    private String name;
    @Column(length = 256)//DDL
    private String description;

    public FailureDetectionMethod(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FailureDetectionMethod() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
