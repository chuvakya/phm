package org.zs.phm3.models;

import org.zs.phm3.models.basic.IdEntity;

import javax.persistence.Column;

public class AttributeType extends IdEntity {
    @Column(unique = true, nullable = false, length=128)//DDL
    private String name;
    @Column(length=255)//DDL
    private String description;

    AttributeCategory category;

    public AttributeType(String name, String description, AttributeCategory category) {
        this.name = name;
        this.description = description;
        this.category=category;
    }

    public AttributeType() {
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

    public AttributeCategory getCategory() {
        return category;
    }

    public void setCategory(AttributeCategory category) {
        this.category = category;
    }

}
