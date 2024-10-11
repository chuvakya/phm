package org.zs.phm3.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "userRole")
    private List<UserRoleUserPermissionGroup> userRoleUserPermissionGroups = new ArrayList<>();

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "userRole")
    private List<UserEntityUserRole> userEntityUserRoles = new ArrayList<>();

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole() {
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

    public List<UserRoleUserPermissionGroup> getUserRoleUserPermissionGroups() {
        return userRoleUserPermissionGroups;
    }

    public void setUserRoleUserPermissionGroups(List<UserRoleUserPermissionGroup> userRoleUserPermissionGroups) {
        this.userRoleUserPermissionGroups = userRoleUserPermissionGroups;
    }

    public List<UserEntityUserRole> getUserEntityUserRoles() {
        return userEntityUserRoles;
    }

    public void setUserEntityUserRoles(List<UserEntityUserRole> userEntityUserRoles) {
        this.userEntityUserRoles = userEntityUserRoles;
    }
}
