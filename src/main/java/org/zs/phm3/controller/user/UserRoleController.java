package org.zs.phm3.controller.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.user.UserRole;
import org.zs.phm3.service.user.UserRoleService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/role/")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SQLHelper sqlHelper;

    @PostMapping(value = "create")
    public Object create(@RequestParam String name, @RequestBody List<Integer> groupIds) {
        if (userRoleService.getCountByName(name) > 0) {
            return "This name already exist!";
        }
        UserRole userRole = new UserRole(name);
        userRoleService.save(userRole);
        userRoleService.saveGroups(userRole.getId(), groupIds);
        return "Success!";
    }

    @GetMapping(value = "getAll")
    private List<UserRole> getAll() {
        return userRoleService.getAll();
    }

    @GetMapping(value = "getById/{roleId}", produces = "application/json")
    public String getById(@PathVariable Integer roleId) {
        return userRoleService.getByIdJSON(roleId);
    }

    @PatchMapping(value = "updateName/{roleId}")
    public String getById(@RequestParam String name, @PathVariable Integer roleId) {
        UserRole userRole = userRoleService.getById(roleId);
        if (userRoleService.getCountByName(name) > 0 && !userRole.getName().equals(name)) {
            return "This name already exist!";
        } else if (!userRole.getName().equals(name)) {
            userRole.setName(name);
            userRoleService.save(userRole);
        }
        return "Success!";
    }

    @PatchMapping(value = "addGroup/{roleId}/{groupId}")
    public String addGroup(@PathVariable Integer roleId, @PathVariable Integer groupId) {
        if (userRoleService.getCountByRoleIdAndGroupId(roleId, groupId) > 0) {
            return "Error! This record already exist!";
        }
        userRoleService.addGroup(roleId, groupId);
        return "Success!";
    }

    @PatchMapping(value = "deleteGroup/{roleId}/{groupId}")
    public void deleteGroup(@PathVariable Integer roleId, @PathVariable Integer groupId) {
        userRoleService.deleteGroup(roleId, groupId);
    }

    @DeleteMapping(value = "deleteRole/{roleId}")
    public void deleteRole(@PathVariable Integer roleId) {
        sqlHelper.deleteAll("user_role","id", Arrays.asList(roleId));
    }


}
