package org.zs.phm3.models.fault;

import javax.persistence.*;

/**
 * Fault unit class
 * @author Pavel Chuvak
 */
@Entity
public class FaultUnit {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unit ID */
    private String unitId;

    /** Fault */
    @ManyToOne
    private Fault fault;

    /** Constructor */
    public FaultUnit() {
    }

    /**
     * Constructor
     * @param unitId unit ID
     * @param fault fault
     */
    public FaultUnit(String unitId, Fault fault) {
        this.unitId = unitId;
        this.fault = fault;
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
     * Gets fault
     * @return fault
     */
    public Fault getFault() {
        return fault;
    }

    /**
     * Sets fault
     * @param fault fault
     */
    public void setFault(Fault fault) {
        this.fault = fault;
    }
}
