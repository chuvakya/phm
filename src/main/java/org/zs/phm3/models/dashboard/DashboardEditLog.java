package org.zs.phm3.models.dashboard;

import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * Dashboard edit log entity
 * @author Pavel Chuvak
 */
@Entity
public class DashboardEditLog {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** User entity */
    @ManyToOne
    private UserEntity userEntity;

    /** Timestamp */
    private Long timestamp;

    /** Dashboard */
    @ManyToOne
    private Dashboard dashboard;

    /**
     * Constructor
     */
    public DashboardEditLog() {
    }

    /**
     * Constructor
     * @param userEntity user entity
     * @param timestamp timestamp
     * @param dashboard dashboard
     */
    public DashboardEditLog(UserEntity userEntity, Long timestamp, Dashboard dashboard) {
        this.userEntity = userEntity;
        this.timestamp = timestamp;
        this.dashboard = dashboard;
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
     * Gets user entity
     * @return user entity
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * Set user entity
     * @param userEntity user entity
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Gets timestamp
     * @return timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp
     * @param timestamp timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
