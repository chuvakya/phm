package org.zs.phm3.models.user;

import javax.persistence.*;

@Entity
public class UserGroupPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserPermission userPermission;

    @ManyToOne
    private UserPermissionGroup userPermissionGroup;

    public UserGroupPermission() {
    }

    public UserGroupPermission(UserPermission userPermission, UserPermissionGroup userPermissionGroup) {
        this.userPermission = userPermission;
        this.userPermissionGroup = userPermissionGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPermission getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(UserPermission userPermission) {
        this.userPermission = userPermission;
    }

    public UserPermissionGroup getUserPermissionGroup() {
        return userPermissionGroup;
    }

    public void setUserPermissionGroup(UserPermissionGroup userPermissionGroup) {
        this.userPermissionGroup = userPermissionGroup;
    }
}
