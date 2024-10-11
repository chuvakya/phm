package org.zs.phm3.models.user;

import javax.persistence.*;

@Entity
public class UserRoleUserPermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserRole userRole;

    @ManyToOne
    private UserPermissionGroup userPermissionGroup;

    public UserRoleUserPermissionGroup() {
    }

    public UserRoleUserPermissionGroup(UserRole userRole, UserPermissionGroup userPermissionGroup) {
        this.userRole = userRole;
        this.userPermissionGroup = userPermissionGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserPermissionGroup getUserPermissionGroup() {
        return userPermissionGroup;
    }

    public void setUserPermissionGroup(UserPermissionGroup userPermissionGroup) {
        this.userPermissionGroup = userPermissionGroup;
    }
}
