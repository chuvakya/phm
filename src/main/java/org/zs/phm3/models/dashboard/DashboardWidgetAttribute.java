package org.zs.phm3.models.dashboard;

import org.zs.phm3.models.ts.TsKvAttribute;

import javax.persistence.*;

/**
 * Dashboard widget attribute entity
 * @author Pavel Chuvak
 */
@Entity
public class DashboardWidgetAttribute {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Dashboard widget */
    @ManyToOne
    private DashboardWidget dashboardWidget;

    /** Attribute name */
    private String name;

    /** Ts kv attribute */
    @ManyToOne
    private TsKvAttribute tsKvAttribute;

    /**
     * Constructor
     */
    public DashboardWidgetAttribute() {
    }

    /**
     * Constructor
     * @param dashboardWidget dashboard widget
     * @param tsKvAttribute ts kv attribute
     * @param name attribute name
     */
    public DashboardWidgetAttribute(DashboardWidget dashboardWidget, TsKvAttribute tsKvAttribute, String name) {
        this.dashboardWidget = dashboardWidget;
        this.tsKvAttribute = tsKvAttribute;
        this.name = name;
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
     * Gets dashboard widget
     * @return dashboard widget
     */
    public DashboardWidget getDashboardWidget() {
        return dashboardWidget;
    }

    /**
     * Sets dashboard widget
     * @param dashboardWidget dashboard widget
     */
    public void setDashboardWidget(DashboardWidget dashboardWidget) {
        this.dashboardWidget = dashboardWidget;
    }

    /**
     * Gets ts kv attribute
     * @return ts kv attribute
     */
    public TsKvAttribute getTsKvAttribute() {
        return tsKvAttribute;
    }

    /**
     * Sets ts kv attribute
     * @param tsKvAttribute ts kv attribute
     */
    public void setTsKvAttribute(TsKvAttribute tsKvAttribute) {
        this.tsKvAttribute = tsKvAttribute;
    }
}
