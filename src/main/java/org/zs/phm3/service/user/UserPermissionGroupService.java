package org.zs.phm3.service.user;

import org.zs.phm3.models.user.UserPermissionGroup;

import java.util.List;

public interface UserPermissionGroupService {
    void savePermissions(Integer userPermissionGroupId, List<Integer> permissionIds);
    UserPermissionGroup save(UserPermissionGroup userPermissionGroup);
    Integer getCountByName(String name);
    String getList();
    String getByIdJSON(Integer permissionGroupId);
    UserPermissionGroup getById(Integer permissionGroupId);
    Integer getCountByGroupIdAndPermissionId(Integer groupId, Integer permissionId);
    void addPermission(Integer groupId, Integer permissionId);
    void deletePermission(Integer groupId, Integer permissionId);
}
