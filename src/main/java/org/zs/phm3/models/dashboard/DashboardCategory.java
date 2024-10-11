package org.zs.phm3.models.dashboard;

/**
 * Enum dashboard category
 * @author Pavel Chuvak
 */
public enum DashboardCategory {

    SYSTEM("System dashboards"),
    UNIT("Unit dashboards");

    /** Description */
    private String description;

    /**
     * Constructor
     * @param description description
     */
    DashboardCategory(String description) {
        this.description = description;
    }

    /**
     * Gets description
     * @return
     */
    public String getDescription() {
        return this.description;
    }
}
