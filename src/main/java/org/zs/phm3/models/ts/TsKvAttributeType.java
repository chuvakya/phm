package org.zs.phm3.models.ts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TsKvAttributeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectId;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "tsKvAttributeType")
    private List<TsKvAttribute> tsKvAttributes = new ArrayList<>();

    public TsKvAttributeType() {}

    public TsKvAttributeType(Integer projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    public List<TsKvAttribute> getTsKvAttributes() {
        return tsKvAttributes;
    }

    public void setTsKvAttributes(List<TsKvAttribute> tsKvAttributes) {
        this.tsKvAttributes = tsKvAttributes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
