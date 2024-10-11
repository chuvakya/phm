package org.zs.phm3.models.rule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.fault.FaultStatus;

import javax.persistence.*;

/**
 * This class is responsible for actions in the rules
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "rule_action")
public class Action {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Action type */
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    /** Action value */
    private String value;

    /** Case */
    @JsonIgnore
    @ManyToOne
    private Case caseEntity;

    /**
     * Constructor
     */
    public Action() {
    }

    /**
     * Constructor
     * @param caseEntity case
     * @param actionType action type
     * @param value action value
     */
    public Action(Case caseEntity, ActionType actionType, String value) {
        this.actionType = actionType;
        this.value = value;
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
     * Gets action type
     * @return action type
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Sets action type
     * @param actionType action type
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * Gets action value
     * @return action value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets action value
     * @param value action value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
