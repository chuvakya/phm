package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for the device model
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "model_ptl")
public class ModelPTL {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Model name */
    private String name;

    /** Modified by */
    @JsonIgnore
    @ManyToOne
    private UserEntity modifiedBy;

    /** Create time */
    private Long createTime;

    /** Revision */
    private String revision;

    /** Manufacturer PTL */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manufacturer_ptl")
    private ManufacturerPTL manufacturerPTL;

    /** Unit type PTL */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "unit_type_ptl_id")
    private UnitTypePTL unitTypePTL;

    /** Model attributes PTL */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "modelPTL")
    private List<ModelAttributePTL> modelAttributePTLList = new ArrayList<>();

    /** BIT codes */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "modelPTL")
    private List<BIT> bits = new ArrayList<>();

    /**
     * Constructor
     */
    public ModelPTL() {}

    /**
     * Constructor
     * @param name model name
     * @param manufacturerPTL manufacturer PTL
     * @param unitTypePTL unit type PTL
     * @param modifiedBy modified by
     * @param createTime create time
     * @param revision revision
     */
    public ModelPTL(String name, ManufacturerPTL manufacturerPTL, UnitTypePTL unitTypePTL, UserEntity modifiedBy,
                    Long createTime, String revision) {
        this.name = name;
        this.manufacturerPTL = manufacturerPTL;
        this.unitTypePTL = unitTypePTL;
        this.modifiedBy = modifiedBy;
        this.createTime = createTime;
        this.revision = revision;
    }

    /**
     * Gets revision
     * @return revision
     */
    public String getRevision() {
        return revision;
    }

    /**
     * Sets revision
     * @param revision revision
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * Gets BIT codes
     * @return BIT codes
     */
    public List<BIT> getBits() {
        return bits;
    }

    /**
     * Sets BIT codes
     * @param bits BIT codes
     */
    public void setBits(List<BIT> bits) {
        this.bits = bits;
    }

    /**
     * Gets model attributes PTL
     * @return model attributes PTL
     */
    public List<ModelAttributePTL> getModelAttributePTLList() {
        return modelAttributePTLList;
    }

    /**
     * Sets model attributes PTL
     * @param modelAttributePTLList model attributes PTL
     */
    public void setModelAttributePTLList(List<ModelAttributePTL> modelAttributePTLList) {
        this.modelAttributePTLList = modelAttributePTLList;
    }

    /**
     * Gets modified by
     * @return modified by
     */
    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets modified by
     * @param modifiedBy modified by
     */
    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets create time
     * @return create time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time
     * @param createTime create time
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets model name
     * @return model name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets model name
     * @param name model name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets manufacturer PTL
     * @return manufacturer PTL
     */
    public ManufacturerPTL getManufacturerPTL() {
        return manufacturerPTL;
    }

    /**
     * Sets manufacturer PTL
     * @param manufacturerPTL manufacturer PTL
     */
    public void setManufacturerPTL(ManufacturerPTL manufacturerPTL) {
        this.manufacturerPTL = manufacturerPTL;
    }

    /**
     * Unit type PTL
     * @return unit type PTL
     */
    public UnitTypePTL getUnitTypePTL() {
        return unitTypePTL;
    }

    /**
     * Sets unit type PTL
     * @param unitTypePTL
     */
    public void setUnitTypePTL(UnitTypePTL unitTypePTL) {
        this.unitTypePTL = unitTypePTL;
    }
}
