package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.zs.phm3.models.ml.MlAlgorithm;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * The class is responsible for the bit code library
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "bit_ptl")
public class BIT {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Model PTL */
    @ManyToOne
    @JoinColumn(name = "model_ptl_id")
    private ModelPTL modelPTL;

    /** Error code */
    private String errorCode;

    /** BIT code name */
    private String name;

    /** Description */
    @Type(type = "text")
    private String description;

    /** Cause */
    @Type(type = "text")
    private String cause;

    /** Troubleshooting */
    @Type(type = "text")
    private String troubleshooting;

    /** Severity */
    private String severity;

    /** Priority */
    private String priority;

    /** Modified by */
    @JsonIgnore
    @ManyToOne
    private UserEntity modifiedBy;

    /** Update time */
    private Long updateTime;

    /**
     * Empty constructor
     */
    public BIT() {
    }

    /**
     * Constructor
     * @param name BIT code name
     * @param modelPTL model PTL
     * @param errorCode error code
     * @param description description
     * @param cause cause
     * @param troubleshooting troubleshooting
     * @param severity severity
     * @param priority priority
     * @param modifiedBy modified by
     * @param updateTime update time
     */
    public BIT(String name, ModelPTL modelPTL, String errorCode, String description, String cause,
               String troubleshooting, String severity, String priority, UserEntity modifiedBy, Long updateTime) {
        this.modelPTL = modelPTL;
        this.errorCode = errorCode;
        this.description = description;
        this.cause = cause;
        this.troubleshooting = troubleshooting;
        this.severity = severity;
        this.priority = priority;
        this.name = name;
        this.modifiedBy = modifiedBy;
        this.updateTime = updateTime;
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
     * Gets update time
     * @return update time
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time
     * @param updateTime update time
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets error code
     * @return error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code
     * @param errorCode error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets cause
     * @return cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * Sets cause
     * @param cause cause
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * Gets troubleshooting
     * @return troubleshooting
     */
    public String getTroubleshooting() {
        return troubleshooting;
    }

    /**
     * Sets troubleshooting
     * @param troubleshooting troubleshooting
     */
    public void setTroubleshooting(String troubleshooting) {
        this.troubleshooting = troubleshooting;
    }

    /**
     * Gets severity
     * @return severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets severity
     * @param severity severity
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * Gets priority
     * @return priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets priority
     * @param priority priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
