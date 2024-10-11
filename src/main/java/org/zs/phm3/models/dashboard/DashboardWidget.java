package org.zs.phm3.models.dashboard;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dashboard widget entity
 * @author Pavel Chuvak
 */
@Entity
public class DashboardWidget {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Front widget ID */
    private Integer frontWidgetId;

    /** JSON */
    @Type(type = "text")
    private String json;

    /** Additional JSON */
    @Type(type = "text")
    private String additionalJSON;

    /** Dashboard widget name */
    private String name;

    /** Dashboard */
    @ManyToOne
    private Dashboard dashboard;

    /** Dashboard widget attributes */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "dashboardWidget", cascade = CascadeType.ALL)
    private List<DashboardWidgetAttribute> dashboardWidgetAttributes = new ArrayList<>();

    /**
     * Constructor
     */
    public DashboardWidget() {
    }

    /**
     * Constructor
     * @param frontWidgetId front widget ID
     * @param json json
     * @param additionalJSON additional JSON
     * @param dashboard dashboard
     * @param name dashboard widget name
     */
    public DashboardWidget(Integer frontWidgetId, String json, String additionalJSON, Dashboard dashboard, String name) {
        this.frontWidgetId = frontWidgetId;
        this.json = json;
        this.dashboard = dashboard;
        this.additionalJSON = additionalJSON;
        this.name = name;
    }

    /**
     * Gets dashboard widget name
     * @return dashboard widget name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets dashboard widget name
     * @param name dashboard widget name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets dashboard widget attributes
     * @return dashboard widget attributes
     */
    public List<DashboardWidgetAttribute> getDashboardWidgetAttributes() {
        return dashboardWidgetAttributes;
    }

    /**
     * Sets dashboard widget attributes
     * @param dashboardWidgetAttributes dashboard widget attributes
     */
    public void setDashboardWidgetAttributes(List<DashboardWidgetAttribute> dashboardWidgetAttributes) {
        this.dashboardWidgetAttributes = dashboardWidgetAttributes;
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
     * Gets dashboard
     * @return dashboard
     */
    public Dashboard getDashboard() {
        return dashboard;
    }

    /**
     * Sets dashboard
     * @param dashboard dashboard
     */
    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
