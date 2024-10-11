package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit type library
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "unit_type_ptl")
public class UnitTypePTL {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Unit type name */
    private String name;

    /** Picture ID */
    private Integer pictureId;

    /** Is default type */
    private Boolean isDefaultType = false;

    /** Modified by */
    @JsonIgnore
    @ManyToOne
    private UserEntity modifiedBy;

    /** Modified time */
    private Long modifiedTime;

    /** Unit type PTL */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "unit_type_ptl_id")
    private UnitTypePTL unitTypePTL;

    /** Unit types PTL */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "unitTypePTL", cascade = CascadeType.ALL)
    private List<UnitTypePTL> unitTypePTLS = new ArrayList<>();

    /** Sub groups PTL */
    @JsonIgnore
    @OneToMany(mappedBy = "unitTypePTL")
    private List<SubGroupPTL> subGroupPTLS = new ArrayList<>();

    /** Models PTL */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "unitTypePTL", cascade = CascadeType.ALL)
    private List<ModelPTL> modelPTLS = new ArrayList<>();

    /** Constructor */
    public UnitTypePTL() {}

    /**
     * Constructor
     * @param name unit type name
     * @param pictureId picture ID
     * @param unitTypePTL unit type PTL
     * @param modifiedBy modeified by
     * @param modifiedTime modified time
     */
    public UnitTypePTL(String name, Integer pictureId, UnitTypePTL unitTypePTL, UserEntity modifiedBy,
                       Long modifiedTime) {
        this.pictureId = pictureId;
        this.name = name;
        this.unitTypePTL = unitTypePTL;
        this.modifiedBy = modifiedBy;
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
     * Modified time
     * @return modified time
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * Sets modified time
     * @param modifiedTime modified time
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * Gets models PTL
     * @return models PTL
     */
    public List<ModelPTL> getModelPTLS() {
        return modelPTLS;
    }

    /**
     * Sets models PTL
     * @param modelPTLS models PTL
     */
    public void setModelPTLS(List<ModelPTL> modelPTLS) {
        this.modelPTLS = modelPTLS;
    }

    /**
     * Gets default type
     * @return default type
     */
    public Boolean getDefaultType() {
        return isDefaultType;
    }

    /**
     * Sets default type
     * @param defaultType default type
     */
    public void setDefaultType(Boolean defaultType) {
        isDefaultType = defaultType;
    }

    /**
     * Gets sub groups PTL
     * @return sub groups PTL
     */
    public List<SubGroupPTL> getSubGroupPTLS() {
        return subGroupPTLS;
    }

    /**
     * Sets sub groups PTL
     * @param subGroupPTLS sub groups PTL
     */
    public void setSubGroupPTLS(List<SubGroupPTL> subGroupPTLS) {
        this.subGroupPTLS = subGroupPTLS;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets picture ID
     * @return picture ID
     */
    public Integer getPictureId() {
        return pictureId;
    }

    /**
     * Sets picture ID
     * @param pictureId picture ID
     */
    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * Gets unit type name
     * @return unit type name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets unit type name
     * @param name unit type name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets unit type PTL
     * @return unit type PTL
     */
    public UnitTypePTL getUnitTypePTL() {
        return unitTypePTL;
    }

    /**
     * Sets unit type PTL
     * @param unitTypePTL unit type PTL
     */
    public void setUnitTypePTL(UnitTypePTL unitTypePTL) {
        this.unitTypePTL = unitTypePTL;
    }

    /**
     * Gets unit types PTL
     * @return unit types PTL
     */
    public List<UnitTypePTL> getUnitTypePTLS() {
        return unitTypePTLS;
    }

    /**
     * Sets unit types PTL
     * @param unitTypePTLS unit types PTL
     */
    public void setUnitTypePTLS(List<UnitTypePTL> unitTypePTLS) {
        this.unitTypePTLS = unitTypePTLS;
    }
}
