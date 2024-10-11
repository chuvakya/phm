package org.zs.phm3.models.fault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.rule.Case;
import org.zs.phm3.models.rule.RuleType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for the error in the error diagnostic module
 * @author Pavel Chuvak
 */
@Entity
public class Fault {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Fault status */
    @Enumerated(EnumType.ORDINAL)
    private FaultStatus status;

    /** Fault name */
    private String name;

    /** Priority */
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    /** Severity */
    @Enumerated(EnumType.ORDINAL)
    private Severity severity;

    /** Is error */
    private Boolean isError;

    /** Start time */
    private Long startTime;

    /** End time */
    private Long endTime;

    /** Rule type */
    @Enumerated(EnumType.STRING)
    private RuleType ruleType;

    /** Fault units */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "fault", cascade = CascadeType.ALL)
    private List<FaultUnit> faultUnits = new ArrayList<>();

    /** Fault logs */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "fault", cascade = CascadeType.ALL)
    private List<FaultLog> faultLogs = new ArrayList<>();


    /** Case entity */
    @JsonIgnore
    @ManyToOne
    private Case caseEntity;

    /**
     * Constructor
     */
    public Fault() {
    }

    /**
     * Constructor
     * @param status fault status
     * @param name fault name
     * @param priority priority
     * @param isError is error
     * @param caseEntity case
     * @param startTime start time
     * @param severity severity
     * @param ruleType rule type
     * @param endTime end time
     */
    public Fault(FaultStatus status, String name, Priority priority, Boolean isError, Case caseEntity, Long startTime,
                 Severity severity, RuleType ruleType, Long endTime) {
        this.status = status;
        this.ruleType = ruleType;
        this.name = name;
        this.priority = priority;
        this.isError = isError;
        this.caseEntity = caseEntity;
        this.startTime = startTime;
        this.severity = severity;
        this.endTime = endTime;
    }

    /**
     * Gets rule type
     * @return rule type
     */
    public RuleType getRuleType() {
        return ruleType;
    }

    /**
     * Sets rule type
     * @param ruleType rule type
     */
    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * Gets start time
     * @return start time
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * Sets start time
     * @param startTime start time
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time
     * @return end time
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * Sets end time
     * @param endTime end time
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets case
     * @return case
     */
    public Case getCaseEntity() {
        return caseEntity;
    }

    /**
     * Sets case
     * @param caseEntity case
     */
    public void setCaseEntity(Case caseEntity) {
        this.caseEntity = caseEntity;
    }

    /**
     * Gets is error
     * @return is error
     */
    public Boolean getError() {
        return isError;
    }

    /**
     * Sets is error
     * @param error is error
     */
    public void setError(Boolean error) {
        isError = error;
    }

    /**
     * Gets fault log
     * @return fault log
     */
    public List<FaultLog> getFaultLogs() {
        return faultLogs;
    }

    /**
     * Sets fault log
     * @param faultLogs fault log
     */
    public void setFaultLogs(List<FaultLog> faultLogs) {
        this.faultLogs = faultLogs;
    }

    /**
     * Gets fault log
     * @return fault log
     */
    public List<FaultUnit> getFaultUnits() {
        return faultUnits;
    }

    /**
     * Sets fault units
     * @param faultUnits fault units
     */
    public void setFaultUnits(List<FaultUnit> faultUnits) {
        this.faultUnits = faultUnits;
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
     * Gets fault status
     * @return fault status
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
     * Gets fault name
     * @return  fault name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets fault name
     * @param name fault name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets priority
     * @return priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets proiority
     * @param priority priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Gets severity
     * @return severity
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Sets severity
     * @param severity severity
     */
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
}
