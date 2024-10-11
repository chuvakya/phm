package org.zs.phm3.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.models.user.UserPermission;
import org.zs.phm3.service.user.UserPermissionService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/permission/")
public class UserPermissionController {

    @Autowired
    private UserPermissionService userPermissionService;

    @GetMapping(value = "getList")
    public List<UserPermission> getList() {
        return userPermissionService.getAll();
    }

}
