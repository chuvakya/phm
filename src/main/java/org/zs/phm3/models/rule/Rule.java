package org.zs.phm3.models.rule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for the rule
 * @author Pavel Chuvak
 */
@Entity
public class Rule {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Rule type */
    @Enumerated(EnumType.STRING)
    private RuleType ruleType;

    /** Rule name */
    private String name;

    /** Modified time */
    private Long modifiedTime;

    /** Modified by */
    @ManyToOne
    private UserEntity modifiedBy;

    /** Is stop */
    private Boolean isStop = false;

    /** Period */
    private Long period;

    /** Cases */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
    private List<Case> cases = new ArrayList<>();

    /** Project ID */
    private Integer projectId;

    /** Last scan time */
    private Long lastScan = System.currentTimeMillis() - 1;

    /** Constructor */
    public Rule() {
    }

    /**
     * Constructor
     * @param ruleType rule type
     * @param name rule name
     * @param projectId project ID
     * @param modifiedBy modified by
     * @param modifiedTime modified time
     * @param period period
     */
    public Rule(RuleType ruleType, String name, Integer projectId,
                UserEntity modifiedBy, Long modifiedTime, Long period) {
        this.ruleType = ruleType;
        this.name = name;
        this.projectId = projectId;
        this.modifiedBy = modifiedBy;
        this.modifiedTime = modifiedTime;
        this.period = period;
    }

    /**
     * Gets last scan time
     * @return last scan time
     */
    public Long getLastScan() {
        return lastScan;
    }

    /**
     * Sets last scan time
     * @param lastScan last scan time
     */
    public void setLastScan(Long lastScan) {
        this.lastScan = lastScan;
    }

    /**
     * Gets is stop
     * @return is stop
     */
    public Boolean getStop() {
        return isStop;
    }

    /**
     * Sets is stop
     * @param stop is stop
     */
    public void setStop(Boolean stop) {
        isStop = stop;
    }

    /**
     * Gets cases
     * @return cases
     */
    public List<Case> getCases() {
        return cases;
    }

    /**
     * Sets cases
     * @param cases cases
     */
    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    /**
     * Gets period
     * @return period
     */
    public Long getPeriod() {
        return period;
    }

    /**
     * Sets period
     * @param period period
     */
    public void setPeriod(Long period) {
        this.period = period;
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
     * Gets project ID
     * @return project ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets project ID
     * @param projectId project ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
     * Gets rule name
     * @return rule name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets rule name
     * @param name rule name
     */
    public void setName(String name) {
        this.name = name;
    }

}
