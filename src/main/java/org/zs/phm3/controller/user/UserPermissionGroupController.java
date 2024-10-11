package org.zs.phm3.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.models.user.UserPermissionGroup;
import org.zs.phm3.service.user.UserPermissionGroupService;
import org.zs.phm3.service.util.SQLHelper;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/permission_group/")
public class UserPermissionGroupController {

    @Autowired
    private UserPermissionGroupService userPermissionGroupService;

    @Autowired
    private SQLHelper sqlHelper;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestParam String name, @RequestBody List<Integer> permissionIds) {
        if (userPermissionGroupService.getCountByName(name) > 0) {
            return "This name already exist!";
        }
        UserPermissionGroup userPermissionGroup = new UserPermissionGroup(name);
        userPermissionGroupService.save(userPermissionGroup);
        userPermissionGroupService.savePermissions(userPermissionGroup.getId(), permissionIds);
        return "Success!";
    }

    @GetMapping(value = "getList", produces = "application/json")
    public String getList() {
        return userPermissionGroupService.getList();
    }

    @GetMapping(value = "getById/{permissionGroupId}", produces = "application/json")
    public String getById(@PathVariable Integer permissionGroupId) {
        return userPermissionGroupService.getByIdJSON(permissionGroupId);
    }

    @PatchMapping(value = "updateName/{permissionGroupId}")
    public String updateName(@RequestParam String name, @PathVariable Integer permissionGroupId) {
        UserPermissionGroup userPermissionGroup = userPermissionGroupService.getById(permissionGroupId);
        if (userPermissionGroupService.getCountByName(name) > 0 &&
            !userPermissionGroup.getName().equals(name)) {
            return "This name already exist!";
        } else if (!userPermissionGroup.getName().equals(name)) {
            userPermissionGroup.setName(name);
            userPermissionGroupService.save(userPermissionGroup);
        }
        return "Success!";
    }

    @PatchMapping(value = "addPermission/{permissionGroupId}/{permissionId}")
    public String addPermission(@PathVariable Integer permissionGroupId, @PathVariable Integer permissionId) {
        if (userPermissionGroupService.getCountByGroupIdAndPermissionId(permissionGroupId, permissionId) > 0) {
            return "Error! This record already exist!";
        }
        userPermissionGroupService.addPermission(permissionGroupId, permissionId);
        return "Success!";
    }

    @PatchMapping(value = "deletePermission/{permissionGroupId}/{permissionId}")
    public void deletePermission(@PathVariable Integer permissionGroupId, @PathVariable Integer permissionId) {
        userPermissionGroupService.deletePermission(permissionGroupId, permissionId);
    }

    @DeleteMapping(value = "deleteGroup/{groupId}")
    public void deleteGroup(@PathVariable Integer groupId) {
        sqlHelper.deleteAll("user_permission_group","id", Arrays.asList(groupId));
    }
}
