package org.zs.phm3.models.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the rule checker
 * @author Pavel Chuvak
 */
public class CaseParam {

    /** Type */
    private String type;

    /** Triggers */
    private List<ConditionParam> triggers = new ArrayList<>();

    /** Actions */
    private List<Action> actions = new ArrayList<>();

    /** Constructor */
    public CaseParam() {
    }

    /**
     * Constructor
     * @param type type
     */
    public CaseParam(String type) {
        this.type = type;
    }

    /**
     * Gets type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets actions
     * @return actions
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Sets actions
     * @param actions actions
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    /**
     * Sets type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets triggers
     * @return triggers
     */
    public List<ConditionParam> getTriggers() {
        return triggers;
    }

    /**
     * Sets triggers
     * @param triggers triggers
     */
    public void setTriggers(List<ConditionParam> triggers) {
        this.triggers = triggers;
    }
}
