package org.zs.phm3.dto;


import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeType;
import org.zs.phm3.models.unit.UnitAttribute;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"new"})
public abstract class UnitDto implements Dto {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;

    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateChanged;

    public LocalDateTime getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(LocalDateTime dateChanged) {
        this.dateChanged = dateChanged;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    private List<UnitAttribute> attributes = new ArrayList<>();

    private List<SchemeEntity> schemes = new ArrayList<>();

    private List<UnitDto> childs = new ArrayList<>();

    private Integer pic_id;

    private Integer picbckgr_id;

    private Integer project_id;

    public String componentVersion;

    private String parentId;
    private String customId;

    private SchemeType activeScheme;

    private String serialNumber;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<SchemeEntity> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<SchemeEntity> schemes) {
        this.schemes = schemes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UnitDto> getChilds() {
        return childs;
    }

    public void setChilds(List<UnitDto> childs) {
        this.childs = childs;
    }

    public UnitDto() {
    }

    public boolean isNew() {
        return this.id == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitDto(Integer projectId, String name, String description) {
        this.project_id = projectId;
        this.name = name;
        this.description = description;
    }

    public List<UnitAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<UnitAttribute> attributes) {
        this.attributes = attributes;
    }

    public Integer getPic_id() {
        return pic_id;
    }

    public void setPic_id(Integer pic_id) {
        this.pic_id = pic_id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getPicbckgr_id() {
        return picbckgr_id;
    }

    public void setPicbckgr_id(Integer picbckgr_id) {
        this.picbckgr_id = picbckgr_id;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public SchemeType getActiveScheme() {
        return activeScheme;
    }

    public void setActiveScheme(SchemeType activeScheme) {
        this.activeScheme = activeScheme;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "UnitDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateChanged=" + dateChanged +
                ", attributes=" + attributes +
                ", schemes=" + schemes +
                ", childs=" + childs +
                ", pic_id=" + pic_id +
                ", picbckgr_id=" + picbckgr_id +
                ", project_id=" + project_id +
                ", componentVersion='" + componentVersion + '\'' +
                ", parentId='" + parentId + '\'' +
                ", customId='" + customId + '\'' +
                ", activeScheme=" + activeScheme +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
