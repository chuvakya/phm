package org.zs.phm3.models.calculation;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.ml.MlModel;

import javax.persistence.*;

/**
 * Calculation attribute class
 * @author Pavel Chuvak
 */
@Entity
public class CalculationAttribute {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Attribute name */
    private String name;

    /** Value */
    private Double value;

    /** Time */
    private Long time;

    /** Ml model */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private MlModel mlModel;

    /** Unit ID */
    private String unitId;

    /** Attribute type */
    @ManyToOne
    private CalculationAttrType attrType;

    /** Constructor */
    public CalculationAttribute() {}

    /**
     * Constructor
     * @param unitId unit ID
     * @param name attribute name
     * @param value value
     * @param mlModel ml model
     * @param time time
     * @param attrType attribute type
     */
    public CalculationAttribute(String unitId, String name, Double value, MlModel mlModel, Long time, CalculationAttrType
                                attrType) {
        this.unitId = unitId;
        this.name = name;
        this.value = value;
        this.mlModel = mlModel;
        this.time = time;
        this.attrType = attrType;
    }

    /**
     * Gets time
     * @return time
     */
    public Long getTime() {
        return time;
    }

    /**
     * Sets time
     * @param time time
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * Gets attribute type
     * @return attribute type
     */
    public CalculationAttrType getAttrType() {
        return attrType;
    }

    /**
     * Sets attribute type
     * @param attrType attribute type
     */
    public void setAttrType(CalculationAttrType attrType) {
        this.attrType = attrType;
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
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
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
     * Gets value
     * @return value
     */
    public Double getValue() {
        return value;
    }

    /**
     * Sets value
     * @param value value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Gets ml model
     * @return ml model
     */
    public MlModel getMlModel() {
        return mlModel;
    }

    /**
     * Sets ml model
     * @param mlModel
     */
    public void setMlModel(MlModel mlModel) {
        this.mlModel = mlModel;
    }
}
