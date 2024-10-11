package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * The class is responsible for the attributes of the model from the model library
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "model_attribute_ptl")
public class ModelAttributePTL {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Attribute name */
    private String name;

    /** Uom symbol */
    @JsonIgnore
    private String uomSymbol;

    /** Modified time */
    private Long modifiedTime;

    /** Modified by */
    @JsonIgnore
    @ManyToOne
    private UserEntity modifiedBy;

    /** Uom ID */
    private Integer uomId;

    /** model attribute type PTL */
    @ManyToOne
    @JoinColumn(name = "model_attr_type_ptl_id")
    private ModelAttrTypePTL modelAttrTypePTL;

    /** Model PTL */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "model_ptl_id")
    private ModelPTL modelPTL;

    /** Data type */
    private String dataType;

    /** Attribute value */
    private String value;

    /** Empty constructor */
    public ModelAttributePTL() {
    }

    /**
     * Constructor
     * @param name name
     * @param modelPTL model PTL
     * @param modelAttrTypePTL model attribute type PTL
     * @param dataType data type
     * @param value value
     * @param uomSymbol uom symbol
     * @param uomId uom ID
     * @param modifiedTime modified time
     * @param modifiedBy modified by
     */
    public ModelAttributePTL(String name, ModelPTL modelPTL, ModelAttrTypePTL modelAttrTypePTL,
                             String dataType, String value, String uomSymbol, Integer uomId, Long modifiedTime,
                             UserEntity modifiedBy) {
        this.name = name;
        this.modelAttrTypePTL = modelAttrTypePTL;
        this.dataType = dataType;
        this.value = value;
        this.modelPTL = modelPTL;
        this.uomSymbol = uomSymbol;
        this.uomId = uomId;
        this.modifiedBy = modifiedBy;
        this.modifiedTime = modifiedTime;
    }

    /**
     * Gets modified time
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
     * Gets model PTL
     * @return model PTL
     */
    public ModelPTL getModelPTL() {
        return modelPTL;
    }

    /**
     * Sets model PTL
     * @param modelPTL model PTL
     */
    public void setModelPTL(ModelPTL modelPTL) {
        this.modelPTL = modelPTL;
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
     * Gets attribute name
     * @return attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets attribute name
     * @param name attribute name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets model type PTL
     * @return model type PTL
     */
    public ModelAttrTypePTL getModelAttrTypePTL() {
        return modelAttrTypePTL;
    }

    /**
     * Sets model type PTL
     * @param modelAttrTypePTL model type PTL
     */
    public void setModelAttrTypePTL(ModelAttrTypePTL modelAttrTypePTL) {
        this.modelAttrTypePTL = modelAttrTypePTL;
    }

    /**
     * Gets data type
     * @return data type
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets data type
     * @param dataType data type
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets attribute value
     * @return attribute value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets attribute value
     * @param value attribute value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets uom symbol
     * @return uom symbol
     */
    public String getUomSymbol() {
        return uomSymbol;
    }

    /**
     * Sets uom symbol
     * @param uomSymbol uom symbol
     */
    public void setUomSymbol(String uomSymbol) {
        this.uomSymbol = uomSymbol;
    }

    /**
     * Gets uom ID
     * @return uom ID
     */
    public Integer getUomId() {
        return uomId;
    }

    /**
     * Sets uom ID
     * @param uomId uom ID
     */
    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }
}
