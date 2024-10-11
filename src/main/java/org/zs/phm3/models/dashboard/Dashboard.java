package org.zs.phm3.models.dashboard;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dashboard entity class
 * @author Pavel Chuvak
 */
@Entity
public class Dashboard {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Dashboard name */
    private String name;

    /** Project ID */
    private Integer projectId;

    /** Model PTL */
    @ManyToOne
    private ModelPTL modelPTL;

    /** Unit ID */
    private String unitId;

    /** Dashboard category */
    @Enumerated(EnumType.STRING)
    private DashboardCategory dashboardCategory;

    /** Dashboard type */
    @Enumerated(EnumType.STRING)
    private DashboardType dashboardType;

    /** Created by */
    @ManyToOne
    private UserEntity createdBy;

    /** Created time */
    private Long createdTime = System.currentTimeMillis();

    /** Dashboard widgets */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "dashboard", cascade = CascadeType.ALL)
    private List<DashboardWidget> dashboardWidgets = new ArrayList<>();

    /** Dashboard edit log */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "dashboard", cascade = CascadeType.ALL)
    private List<DashboardEditLog> dashboardEditLogs = new ArrayList<>();

    /**
     * Constructor
     */
    public Dashboard() {
    }

    /**
     * Constructor
     * @param name dashboard name
     * @param projectId project ID
     * @param dashboardCategory dashboard category
     * @param dashboardType dashboard type
     * @param modelPTL model PTL
     * @param unitId unit ID
     * @param createdBy created by
     */
    public Dashboard(String name, Integer projectId, DashboardCategory dashboardCategory,
                     DashboardType dashboardType, ModelPTL modelPTL, String unitId, UserEntity createdBy) {
        this.name = name;
        this.projectId = projectId;
        this.dashboardCategory = dashboardCategory;
        this.dashboardType = dashboardType;
        this.modelPTL = modelPTL;
        this.unitId = unitId;
        this.createdBy = createdBy;
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
     * @param createdBy
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
     * Gets dashboard edit logs
     * @return dashboard edit logs
     */
    public List<DashboardEditLog> getDashboardEditLogs() {
        return dashboardEditLogs;
    }

    /**
     * Sets dashboard edit logs
     * @param dashboardEditLogs dashboard edit logs
     */
    public void setDashboardEditLogs(List<DashboardEditLog> dashboardEditLogs) {
        this.dashboardEditLogs = dashboardEditLogs;
    }

    /**
     * Gets model PTL
     * @return model PTL
     */
    public ModelPTL getModelPTL() {
        return modelPTL;
    }

    /**
     * Sets model PTL
     * @param modelPTL model PTL
     */
    public void setModelPTL(ModelPTL modelPTL) {
        this.modelPTL = modelPTL;
    }

    /**
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Gets dashboard type
     * @return dashboard type
     */
    public DashboardType getDashboardType() {
        return dashboardType;
    }

    /**
     * Sets dashboard type
     * @param dashboardType dashboard type
     */
    public void setDashboardType(DashboardType dashboardType) {
        this.dashboardType = dashboardType;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets dashboard widgets
     * @return dashboard widgets
     */
    public List<DashboardWidget> getDashboardWidgets() {
        return dashboardWidgets;
    }

    /**
     * Sets dashboard widgets
     * @param dashboardWidgets dashboard widgets
     */
    public void setDashboardWidgets(List<DashboardWidget> dashboardWidgets) {
        this.dashboardWidgets = dashboardWidgets;
    }

    /**
     * Gets dashboard name
     * @return dashboard name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets dashboard name
     * @param name dashboard name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets dashboard category
     * @return dashboard category
     */
    public DashboardCategory getDashboardCategory() {
        return dashboardCategory;
    }

    /**
     * Sets dashboard category
     * @param dashboardCategory dashboard category
     */
    public void setDashboardCategory(DashboardCategory dashboardCategory) {
        this.dashboardCategory = dashboardCategory;
    }
}
