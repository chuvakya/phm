package org.zs.phm3.models.dashboard.request;

import java.util.List;

/**
 * Helper class
 * @author Pavel Chuvak
 */
public class WidgetFilter {

    /** Project ID */
    private Integer projectId;

    /** Widget name */
    private String name;

    /** Types */
    private List<String> types;

    /** Is my widgets */
    private Boolean isMyWidgets;

    /** Is user widget */
    private Boolean isUserWidget;

    /** Is default */
    private Boolean isDefault;

    /**
     * Gets is default
     * @return is default
     */
    public Boolean getDefault() {
        return isDefault;
    }

    /**
     * Sets is default
     * @param aDefault is default
     */
    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
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
     * Gets types
     * @return types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Sets types
     * @param types types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * Gets is my widgets
     * @return is my widgets
     */
    public Boolean getMyWidgets() {
        return isMyWidgets;
    }

    /**
     * Sets is my widgets
     * @param myWidgets is my widgets
     */
    public void setMyWidgets(Boolean myWidgets) {
        isMyWidgets = myWidgets;
    }

    /**
     * Gets user widget
     * @return is user widget
     */
    public Boolean getUserWidget() {
        return isUserWidget;
    }

    /**
     * Sets is user widget
     * @param userWidget is user widget
     */
    public void setUserWidget(Boolean userWidget) {
        isUserWidget = userWidget;
    }
}
