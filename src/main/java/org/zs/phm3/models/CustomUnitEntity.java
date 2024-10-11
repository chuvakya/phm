package org.zs.phm3.models;

import org.zs.phm3.models.basic.IdEntity;

import javax.persistence.Entity;

@Entity(name = "custom_unit")
public class CustomUnitEntity extends IdEntity {
    public String name;
    public String customId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomUnitEntity() {
    }

    public CustomUnitEntity(String customId, String name) {
        this.customId = customId;
        this.name = name;
    }
}
