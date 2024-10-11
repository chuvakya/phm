package org.zs.phm3.models.dashboard;

import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * Widget edit log entity
 * @author Pavel Chuvak
 */
@Entity
public class WidgetEditLog {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** User entity */
    @ManyToOne
    private UserEntity userEntity;

    /** Widget */
    @ManyToOne
    private Widget widget;

    /** Timestamp */
    private Long timestamp;

    /**
     * Constructor
     */
    public WidgetEditLog() {}

    /**
     * Constructor
     * @param userEntity user entity
     * @param widget widget
     * @param timestamp timestamp
     */
    public WidgetEditLog(UserEntity userEntity, Widget widget, Long timestamp) {
        this.userEntity = userEntity;
        this.widget = widget;
        this.timestamp = timestamp;
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
     * Sets user entity
     * @param userEntity user entity
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Gets widget
     * @return widget
     */
    public Widget getWidget() {
        return widget;
    }

    /**
     * Sets widget
     * @param widget widget
     */
    public void setWidget(Widget widget) {
        this.widget = widget;
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
}
