package org.zs.phm3.models.maintenance;

import org.zs.phm3.models.basic.IdEntity;

import javax.persistence.*;

@Table(name = "MS_OPERATION_TYPES")
@Entity
public class OperationTypeEntity extends IdEntity {

    @Column(unique = true, length = 64)//DDL
    private String name;

    @Column(length = 512)//DDL
    private String description;

    public OperationTypeEntity() {
    }

    public OperationTypeEntity(String name, String description) {
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
}
