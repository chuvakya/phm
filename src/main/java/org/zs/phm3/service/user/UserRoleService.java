package org.zs.phm3.service.user;

import org.zs.phm3.models.user.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    void saveGroups(Integer userRoleId, List<Integer> groupIds);
    Integer getCountByName(String name);
    List<UserRole> getAll();
    String getByIdJSON(Integer roleId);
    UserRole getById(Integer roleId);
    void addGroup(Integer roleId, Integer groupId);
    Integer getCountByRoleIdAndGroupId(Integer roleId, Integer groupId);
    void deleteGroup(Integer roleId, Integer groupId);
}
