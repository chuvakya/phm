package org.zs.phm3.models.dashboard.request;

/**
 * Helper class
 * @author Pavel Chuvak
 */
public class DashboardFilter {

    /** Project ID */
    private Integer projectId;

    /** Dashboard name */
    private String name;

    /** Is monitoring type */
    private Boolean isMonitoringType;

    /** Is prognosis type */
    private Boolean isPrognosisType;

    /** Is unit model */
    private Boolean isUnitModel;

    /** Is custom unit */
    private Boolean isCustomUnit;

    /** Is my dashboards */
    private Boolean isMyDashboards;

    /** Is system dashboard */
    private Boolean isSystemDashboard;

    /**
     * Gets is system dashboard
     * @return is system dashboard
     */
    public Boolean getSystemDashboard() {
        return isSystemDashboard;
    }

    /**
     * Sets is system dashboard
     * @param systemDashboard is system dashboard
     */
    public void setSystemDashboard(Boolean systemDashboard) {
        isSystemDashboard = systemDashboard;
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
     * Gets is monitoring type
     * @return is monitoring type
     */
    public Boolean getMonitoringType() {
        return isMonitoringType;
    }

    /**
     * Sets is monitoring type
     * @param monitoringType is monitoring type
     */
    public void setMonitoringType(Boolean monitoringType) {
        isMonitoringType = monitoringType;
    }

    /**
     * Gets is prognosis type
     * @return is prognosis type
     */
    public Boolean getPrognosisType() {
        return isPrognosisType;
    }

    /**
     * Sets is prognosis type
     * @param prognosisType is prognosis type
     */
    public void setPrognosisType(Boolean prognosisType) {
        isPrognosisType = prognosisType;
    }

    /**
     * Gets unit model
     * @return unit model
     */
    public Boolean getUnitModel() {
        return isUnitModel;
    }

    /**
     * Sets unit model
     * @param unitModel unit model
     */
    public void setUnitModel(Boolean unitModel) {
        isUnitModel = unitModel;
    }

    /**
     * Gets custom unit
     * @return custom unit
     */
    public Boolean getCustomUnit() {
        return isCustomUnit;
    }

    /**
     * Sets custom unit
     * @param customUnit custom unit
     */
    public void setCustomUnit(Boolean customUnit) {
        isCustomUnit = customUnit;
    }

    /**
     * Gets is my dashboards
     * @return is my dashboards
     */
    public Boolean getMyDashboards() {
        return isMyDashboards;
    }

    /**
     * Sets is my dashboards
     * @param myDashboards is my dashboards
     */
    public void setMyDashboards(Boolean myDashboards) {
        isMyDashboards = myDashboards;
    }
}
