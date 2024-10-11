package org.zs.phm3.models.dashboard;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Widget {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Front widget ID */
    private Integer frontWidgetId;

    /** Widget name */
    private String name;

    /** JSON */
    @Type(type = "text")
    private String json;

    /** Project ID */
    private Integer projectId;

    /** Default widget */
    private Boolean defaultWidget = false;

    /** Widget type */
    private String type;

    /** Created by */
    @ManyToOne
    private UserEntity createdBy;

    /** Created time */
    private Long createdTime = System.currentTimeMillis();

    /** Widget attributes */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "widget", cascade = CascadeType.ALL)
    private List<WidgetAttribute> widgetAttributes = new ArrayList<>();

    /** Widget edit logs */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "widget", cascade = CascadeType.ALL)
    private List<WidgetEditLog> widgetEditLogs = new ArrayList<>();

    /**
     * Constructor
     */
    public Widget() {
    }

    /**
     * Constructor
     * @param frontWidgetId front widget ID
     * @param name widget name
     * @param json json
     * @param projectId project ID
     * @param type widget type
     * @param createdBy create by
     */
    public Widget(Integer frontWidgetId, String name, String json, Integer projectId, String type, UserEntity createdBy) {
        this.frontWidgetId = frontWidgetId;
        this.name = name;
        this.json = json;
        this.projectId = projectId;
        this.type = type;
        this.createdBy = createdBy;
    }

    /**
     * Gets is default widget
     * @return is default widget
     */
    public Boolean getDefaultWidget() {
        return defaultWidget;
    }

    /**
     * Sets is default widget
     * @param defaultWidget is default widget
     */
    public void setDefaultWidget(Boolean defaultWidget) {
        this.defaultWidget = defaultWidget;
    }

    /**
     * Gets created by
     * @return created by
     */
    public UserEntity getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets created by
     * @param createdBy created by
     */
    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets created time
     * @return created time
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets created time
     * @param createdTime created time
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Gets widget edit logs
     * @return widget edit logs
     */
    public List<WidgetEditLog> getWidgetEditLogs() {
        return widgetEditLogs;
    }

    /**
     * Sets widget edit logs
     * @param widgetEditLogs widget edit logs
     */
    public void setWidgetEditLogs(List<WidgetEditLog> widgetEditLogs) {
        this.widgetEditLogs = widgetEditLogs;
    }

    /**
     * Gets widget type
     * @return widget type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets widget type
     * @param type widget type
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets widget attributes
     * @return widget attributes
     */
    public List<WidgetAttribute> getWidgetAttributes() {
        return widgetAttributes;
    }

    /**
     * Sets widget attributes
     * @param widgetAttributes widget attributes
     */
    public void setWidgetAttributes(List<WidgetAttribute> widgetAttributes) {
        this.widgetAttributes = widgetAttributes;
    }

}
