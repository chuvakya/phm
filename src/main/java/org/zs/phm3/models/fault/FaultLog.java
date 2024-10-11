package org.zs.phm3.models.fault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * The class responsible for the log in the module Fault Diagnostic
 * @author Pavel Chuvak
 */
@Entity
public class FaultLog {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Timestamp */
    private Long timestamp;

    /** Attribute name */
    private String attrName;

    /** Current value */
    private String currentValue;

    /** Status */
    @Enumerated(EnumType.ORDINAL)
    private FaultStatus status;

    /** Fault */
    @JsonIgnore
    @ManyToOne
    private Fault fault;

    /** Constructor */
    public FaultLog() {
    }

    /**
     * Controller
     * @param timestamp timestamp
     * @param attrName attr name
     * @param currentValue current value
     * @param fault fault
     * @param status status
     */
    public FaultLog(Long timestamp, String attrName, String currentValue, Fault fault, FaultStatus status) {
        this.timestamp = timestamp;
        this.attrName = attrName;
        this.currentValue = currentValue;
        this.fault = fault;
        this.status = status;
    }

    /**
     * Gets status
     * @return status
     */
    public FaultStatus getStatus() {
        return status;
    }

    /**
     * Sets status
     * @param status status
     */
    public void setStatus(FaultStatus status) {
        this.status = status;
    }

    /**
     * Gets attribute name
     * @return attribute name
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * Sets attribute name
     * @param attrName attribute name
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * Gets current value
     * @return current value
     */
    public String getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets current value
     * @param currentValue current value
     */
    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
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
     * Gets timestamp
     * @return timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp
     * @param timestamp timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets fault
     * @return fault
     */
    public Fault getFault() {
        return fault;
    }

    /**
     * Gets fault
     * @param fault fault
     */
    public void setFault(Fault fault) {
        this.fault = fault;
    }
}
