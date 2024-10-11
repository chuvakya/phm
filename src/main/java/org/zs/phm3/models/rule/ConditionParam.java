package org.zs.phm3.models.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the rule controller
 * @author Pavel Chuvak
 */
public class ConditionParam {

    /** Unit attribute ID */
    private Long attributeId;

    /** Condition type */
    private ConditionType conditionType;

    /** Condition value */
    private String value;

    /** Constructor */
    public ConditionParam() {}

    /**
     * Gets unit attribute ID
     * @return unit attribute ID
     */
    public Long getAttributeId() {
        return attributeId;
    }

    /**
     * Sets unit attribute ID
     * @param attributeId unit attribute ID
     */
    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
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

}
