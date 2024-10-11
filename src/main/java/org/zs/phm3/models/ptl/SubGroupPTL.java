package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * The class is responsible for the library of groups for GJB
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "sub_group_ptl")
public class SubGroupPTL {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Class name GJB */
    private String className;

    /** Class code GJB */
    private Integer classCode;

    /** Unit type PTL */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "unit_type_ptl_id")
    private UnitTypePTL unitTypePTL;

    /**
     * Constructor
     */
    public SubGroupPTL() {
    }

    /**
     * Constructor
     * @param className class name GJB
     * @param classCode class code GJB
     * @param unitTypePTL unit type PTL
     */
    public SubGroupPTL(String className, Integer classCode, UnitTypePTL unitTypePTL) {
        this.className = className;
        this.classCode = classCode;
        this.unitTypePTL = unitTypePTL;
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
     * Class name GJB
     * @return class name GJB
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets class name GJB
     * @param className class name GJB
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets class code GJB
     * @return class code GJB
     */
    public Integer getClassCode() {
        return classCode;
    }

    /**
     * Sets class code GJB
     * @param classCode
     */
    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
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
}
