package org.zs.phm3.models.unit;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.UnitDtoOutput;
import org.zs.phm3.dto.ptl.PtlOutput;
import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.ProjectEntity;
import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeType;
import org.zs.phm3.models.basic.BaseSqlEntity;
import org.zs.phm3.models.ptl.ModelPTL;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 0.6
 * @since 05-20-2020
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "JpaModelReferenceInspection"})
@Entity
@DynamicUpdate
@Table(name = "units")
@JsonIgnoreProperties(value = {"project", "parent", "icon", "background", "new"}) /* "parentId"*/
public class UnitEntity extends BaseSqlEntity {//DateFromToEntity {

    @Column(name = "name")
    private String name;

    @Column(length = 512)//DDL
    private String description;
    private String customId;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @OneToMany(
            mappedBy = "unit",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @ApiModelProperty(hidden = true)
//    @JsonIgnore
    @Transient
    private List<UnitAttribute> attributes = new ArrayList<>();

    //    @JsonIgnore
//    @OneToMany(mappedBy = "UnitEntity")
    @Transient
    private List<SchemeEntity> schemes = new ArrayList<>();

    private SchemeType activeScheme;

    @Transient
    @ApiModelProperty(hidden = true)
    private List<UnitEntity> childs = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @ApiModelProperty(hidden = true)
//    @JoinColumn(name = "model_old")
    private UnitModel model;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pic_file_id")
    @ApiModelProperty(hidden = true)
//    @JsonIgnore
    private FileEntity icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bckgrnd_file_id")
    @ApiModelProperty(hidden = true)
//    @JsonIgnore
    private FileEntity background;

    public FileEntity getBackground() {
        return background;
    }

    public void setBackground(FileEntity background) {
        this.background = background;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ApiModelProperty(hidden = true)
//    @JsonIgnore
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private UnitEntity parent;

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_type")
    private UnitTypePTL unitType;*/

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "model")
    private ModelPTL modelPtl;

//    @Transient
//    private Integer iconTypeId;//Zeniuk

    @Column(length = 30)//DDL
    private String serialNumber;

/*    @Transient
    private Long manufacturerId;

    @Transient
    private String manufacturerName;*/

    @Transient
    private Integer unitTypePicureId;

//    @Transient
//    private String unitTypeName;

    public List<SchemeEntity> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<SchemeEntity> schemes) {
        this.schemes = schemes;
    }

    public FileEntity getIcon() {
        return icon;
    }

    public void setIcon(FileEntity icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UnitEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<UnitEntity> childs) {
        this.childs = childs;
    }

    public ModelPTL getModelPtl() {
        return modelPtl;
    }

    public void setModelPtl(ModelPTL modelPtl) {
        this.modelPtl = modelPtl;
    }

    public Integer getUnitTypePicureId() {
        return unitTypePicureId;
    }

    public void setUnitTypePicureId(Integer unitTypePicureId) {
        this.unitTypePicureId = unitTypePicureId;
    }

    @Override
    public UnitDtoOutput toData() {
        /* data mapping unitEntity —> unitDto, used after save */
        UnitDtoOutput unitDto = new UnitDtoOutput();
        unitDto.setDateCreated(dateCreated);
        unitDto.setDateChanged(dateChanged);
        unitDto.setId(UUIDConverter.fromString(id).toString());
//        unitDtoNew.setId(id);
        unitDto.setName(name);
        unitDto.setDescription(description);
        if (icon != null)
            unitDto.setPic_id(icon.getId());
        if (background != null)
            unitDto.setPicbckgr_id(background.getId());

        unitDto.setProject_id(project.getId());
        if (parent != null)
            unitDto.setParentId(parent.id);
        unitDto.setSchemes(schemes);
        unitDto.setActiveScheme(activeScheme);

        unitDto.setAttributes(attributes);
/*        if (unitType != null){
            unitDto.setUnitType(unitType.getId());
//            unitDto.setIconId(unitType.getPictureId());
        }*/
        if (modelPtl != null) {

/* TODO 6 fields for PTL &
            unitDto.setModelPtlId(modelPtl.getId()); */
            if (modelPtl.getName().equals("empty model")) {
                PtlOutput ptlData = new PtlOutput(modelPtl.getId(), modelPtl.getName(),
                        modelPtl.getUnitTypePTL().getId(), modelPtl.getUnitTypePTL().getName(),
                        null, null,
                        modelPtl.getUnitTypePTL().getId());
                unitDto.setPtlOutput(ptlData);
            } else {
                Integer manufacturerPtlId;
                String manufacturerPtlName;
                if (modelPtl.getManufacturerPTL() == null) {
                    manufacturerPtlId = null;
                    manufacturerPtlName = null;
                } else {
                    manufacturerPtlId = Math.toIntExact(modelPtl.getManufacturerPTL().getId());
                    manufacturerPtlName = modelPtl.getManufacturerPTL().getName();
                }
                PtlOutput ptlData = new PtlOutput(modelPtl.getId(), modelPtl.getName(),
                        modelPtl.getUnitTypePTL().getId(), modelPtl.getUnitTypePTL().getName(),
                        manufacturerPtlId, manufacturerPtlName,
                        modelPtl.getUnitTypePTL().getId());
                unitDto.setPtlOutput(ptlData);
//            unitDto.setIconId(unitType.getPictureId());
            }
        }
        unitDto.setSerialNumber(serialNumber);
        return unitDto;
    }

    public UnitEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitModel getModel() {
        return model;
    }

    public void setModel(ProjectEntity project, UnitModel model) {
        this.model = model;
    }

    public UnitEntity(ProjectEntity project, String name) {
        this.project = project;
        this.name = name;
        dateCreated = LocalDateTime.now();
    }

    public UnitEntity(ProjectEntity project, String name, String description) {
        this.project = project;
        this.name = name;
        this.description = description;
        dateCreated = LocalDateTime.now();
    }

    public String getShortId() {
        return this.id;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public List<UnitAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<UnitAttribute> attributes) {
        this.attributes = attributes;
    }

    public UnitEntity getParent() {
        return parent;
    }

    public void setParent(UnitEntity parent) {
        this.parent = parent;
    }

    public void setModel(UnitModel model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getParentId() {
        if (getParent() == null) {
            return "";
        } else {
            return getParent().getId().toString();
        }
    }

    @Override
    public String toString() {
        return "UnitEntity{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attributes=" + attributes +
                ", schemes=" + schemes +
                ", childs=" + childs +
                ", model=" + model +
                ", icon=" + icon +
                '}';
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public SchemeType getActiveScheme() {
        return activeScheme;
    }

    public void setActiveScheme(SchemeType activeScheme) {
        this.activeScheme = activeScheme;
    }

/*    public Integer getIconTypeId() {
        return iconTypeId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }
*/

    @PostLoad
    @PostUpdate
    @PostPersist
    private void onLoadAndUpdate() {
        if (modelPtl != null) {
//            this.iconTypeId = modelPtl.getUnitTypePTL().getPictureId();
//            this.unitTypeId= modelPtl.getUnitTypePTL().getId();
//            this.unitTypeName= modelPtl.getUnitTypePTL().getName();
            this.unitTypePicureId = modelPtl.getUnitTypePTL().getPictureId();
//            if (modelPtl.getManufacturerPTL()!=null) {
//                this.manufacturerId = modelPtl.getManufacturerPTL().getId();
//                this.manufacturerName = modelPtl.getManufacturerPTL().getName();
//            }
        }
    }
}