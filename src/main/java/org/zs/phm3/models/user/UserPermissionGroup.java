package org.zs.phm3.models.user;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserPermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "userPermissionGroup")
    private List<UserGroupPermission> userGroupPermissions = new ArrayList<>();

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "userPermissionGroup")
    private List<UserRoleUserPermissionGroup> userRoleUserPermissionGroups = new ArrayList<>();

    public UserPermissionGroup(String name) {
        this.name = name;
    }

    public List<UserRoleUserPermissionGroup> getUserRoleUserPermissionGroups() {
        return userRoleUserPermissionGroups;
    }

    public void setUserRoleUserPermissionGroups(List<UserRoleUserPermissionGroup> userRoleUserPermissionGroups) {
        this.userRoleUserPermissionGroups = userRoleUserPermissionGroups;
    }

    public UserPermissionGroup() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGroupPermission> getUserGroupPermissions() {
        return userGroupPermissions;
    }

    public void setUserGroupPermissions(List<UserGroupPermission> userGroupPermissions) {
        this.userGroupPermissions = userGroupPermissions;
    }
}
