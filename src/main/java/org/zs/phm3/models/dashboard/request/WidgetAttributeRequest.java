package org.zs.phm3.models.dashboard.request;

/**
 * Helper class
 * @author Pavel Chuvak
 */
public class WidgetAttributeRequest {

    /** Attribute name */
    private String name;

    /** Attribute ID */
    private Long attributeId;

    /**
     * Constructor
     * @param name attribute name
     * @param attributeId attribute ID
     */
    public WidgetAttributeRequest(String name, Long attributeId) {
        this.name = name;
        this.attributeId = attributeId;
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
     * Gets attribute ID
     * @return attribute ID
     */
    public Long getAttributeId() {
        return attributeId;
    }

    /**
     * Sets attribute ID
     * @param attributeId attribute ID
     */
    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }
}
