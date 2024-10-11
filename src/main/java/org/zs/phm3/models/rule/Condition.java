package org.zs.phm3.models.rule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.ts.TsKvAttribute;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the trigger in the rule
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "rule_condition")
public class Condition {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Condition type */
    @Enumerated(EnumType.STRING)
    private ConditionType conditionType;

    /** Condition value */
    private String value;

    /** Unit attribute */
    @JsonIgnore
    @ManyToOne
    private TsKvAttribute tsKvAttribute;

    /** Case */
    @ManyToOne
    private Case caseEntity;

    /** Constructor */
    public Condition() {
    }

    /**
     * Constructor
     * @param conditionType condition type
     * @param value condition value
     * @param tsKvAttribute unit attribute
     * @param caseEntity case
     */
    public Condition(ConditionType conditionType, String value, TsKvAttribute tsKvAttribute, Case caseEntity) {
        this.conditionType = conditionType;
        this.value = value;
        this.tsKvAttribute = tsKvAttribute;
        this.caseEntity = caseEntity;
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
     * Gets condition type
     * @return condition type
     */
    public ConditionType getConditionType() {
        return conditionType;
    }

    /**
     * Sets condition type
     * @param conditionType condition type
     */
    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    /**
     * Gets condition value
     * @return condition value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets condition value
     * @param value condition value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets unit attribute
     * @return unit attribute
     */
    public TsKvAttribute getTsKvAttribute() {
        return tsKvAttribute;
    }

    /**
     * Sets unit attribute
     * @param tsKvAttribute unit attribute
     */
    public void setTsKvAttribute(TsKvAttribute tsKvAttribute) {
        this.tsKvAttribute = tsKvAttribute;
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
}
