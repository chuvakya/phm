package org.zs.phm3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"new"})
public class DtoIdName implements Dto {
    String id;
    String name;
    Boolean selected;

    public DtoIdName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public DtoIdName(String id, String name, Boolean selected) {
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
