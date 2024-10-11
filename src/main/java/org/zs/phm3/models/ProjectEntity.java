package org.zs.phm3.models;

import org.zs.phm3.models.basic.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
//import org.hibernate.validator.constraints.Length;
import org.zs.phm3.data.UUIDConverter;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

/**
 * @version 0.6
 * @since 05-20-2020
 */

@SuppressWarnings("ALL")
@Entity
@Table(name = "PROJECTS")

@JsonIgnoreProperties(value = {"inActive", "schemes", "picture_file"})

public class ProjectEntity extends IdEntity {

    @Column(length = 255, unique = true, nullable = false)

    private String name;

    private String description;

    @ApiModelProperty(hidden = true)
    @Column(name = "inactive")
    private Boolean inActive;

    @ApiModelProperty(hidden = true)
    @JsonIgnore

    @Column(length = 31)
    public String firstUnitId;
    @Transient
    public String unitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pictfile_id")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private FileEntity picture_file;

    @Transient
    private Integer pic_id;

    public FileEntity getPicture_file() {
        return picture_file;
    }

    public void setPicture_file(FileEntity picture_file) {
        this.picture_file = picture_file;
    }

    public Integer getPic_id() {
        return pic_id;
    }

    public void setPic_id(Integer pic_id) {
        this.pic_id = pic_id;
    }

    @PostLoad
    @PostUpdate
    private void onLoadAndUpdate() {
        if (firstUnitId != null)
            this.unitId = UUIDConverter.fromString(firstUnitId).toString();
        if (firstUnitId != null)
            this.unitId = UUIDConverter.fromString(firstUnitId).toString();
        if (picture_file != null)
            this.pic_id = picture_file.getId();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getFirstUnitId() {
        return firstUnitId;
    }

    public void setFirstUnitId(String firstUnitId) {
        this.firstUnitId = firstUnitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectEntity() {
    }

    public ProjectEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Boolean getInActive() {
        return inActive;
    }

    public void setInActive(Boolean inActive) {
        this.inActive = inActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
