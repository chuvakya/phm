package org.zs.phm3.models.dashboard.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class
 * @author Pavel Chuvak
 */
public class WidgetRequest {

    /** Widget name */
    private String name;

    /** Additional JSON */
    private String additionalJSON;

    /** JSON */
    private String json;

    /** Front widget ID */
    private Integer frontWidgetId;

    /** Type */
    private String type;

    /** Attributes */
    private List<WidgetAttributeRequest> attributes = new ArrayList<>();

    /**
     * Gets type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets widget name
     * @return widget name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets widget name
     * @param name widget name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets additional JSON
     * @return additional JSON
     */
    public String getAdditionalJSON() {
        return additionalJSON;
    }

    /**
     * Sets additional JSON
     * @param additionalJSON additional JSON
     */
    public void setAdditionalJSON(String additionalJSON) {
        this.additionalJSON = additionalJSON;
    }

    /**
     * Gets json
     * @return json
     */
    public String getJson() {
        return json;
    }

    /**
     * Sets json
     * @param json json
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Gets front widget ID
     * @return front widget ID
     */
    public Integer getFrontWidgetId() {
        return frontWidgetId;
    }

    /**
     * Sets front widget ID
     * @param frontWidgetId front widget ID
     */
    public void setFrontWidgetId(Integer frontWidgetId) {
        this.frontWidgetId = frontWidgetId;
    }

    /**
     * Gets attributes
     * @return attributes
     */
    public List<WidgetAttributeRequest> getAttributes() {
        return attributes;
    }

    /**
     * Sets attributes
     * @param attributes attributes
     */
    public void setAttributes(List<WidgetAttributeRequest> attributes) {
        this.attributes = attributes;
    }
}
