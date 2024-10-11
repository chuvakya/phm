package org.zs.phm3.service.user;

import org.zs.phm3.models.user.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getById(Long userId);
    String getAllUsers();
    UserEntity save(UserEntity userEntity);
    UserEntity getByLogin(String login);
    void saveUserRoles(Long userEntityId, List<Integer> roleIds);
    String getList();
    String getJSONByUserEntityId(Long userId);
    void setRole(Long userId, Integer roleId);
    void deleteRoleByUserIdAndRoleId(Long userEntityId, Integer userRoleId);
    Integer getCountByUserIdAndRoleId(Long userId, Integer roleId);

}
