package org.zs.phm3.models.fmea;

import org.zs.phm3.models.basic.IdStringEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_failure_severities")

public class FailureSeverity {//} extends IdStringEntity {

    @Id
    String id;

    @Column(unique = true, nullable = false, length=128)//DDL
    private String name;
    @Column(length=256)//DDL
    private String description;

    public FailureSeverity() {
    }

    public FailureSeverity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
