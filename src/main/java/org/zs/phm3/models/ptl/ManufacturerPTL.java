package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.maintenance.supplier.Supplier;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the manufacturer library
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "manufacturer_ptl")
public class ManufacturerPTL {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name */
    private String name;

    /** Modified by */
    @JsonIgnore
    @ManyToOne
    private UserEntity modifiedBy;
//    @JsonIgnore
    /** Create time */
    private Long createTime;

    /**
     * Model PTLs
     */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "manufacturerPTL", cascade = CascadeType.ALL)
    private List<ModelPTL> modelPTLS = new ArrayList<>();

    /**
     * Empty constructor
     */
    public ManufacturerPTL() {}

    /**
     * Constructor
     * @param name name
     * @param modifiedBy modified by
     * @param createTime create time
     */
    public ManufacturerPTL(String name, UserEntity modifiedBy, Long createTime) {
        this.createTime = createTime;
        this.modifiedBy = modifiedBy;
        this.name = name;
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
     * Gets model PTLs
     * @return model PTLs
     */
    public List<ModelPTL> getModelPTLS() {
        return modelPTLS;
    }

    /**
     * Sets model PTLs
     * @param modelPTLS model PTLs
     */
    public void setModelPTLS(List<ModelPTL> modelPTLS) {
        this.modelPTLS = modelPTLS;
    }
}
