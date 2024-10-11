package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserRoleUserPermissionGroup;

import java.util.List;

@Transactional
@Repository
public interface UserRoleUserPermissionGroupRepository extends CrudRepository<UserRoleUserPermissionGroup, Long> {

    @Query(value = "SELECT u.userPermissionGroup.id FROM UserRoleUserPermissionGroup u WHERE u.userRole.id = ?1")
    List<Integer> getPermissionGroupIdsByUserRoleId(Integer roleId);

    @Modifying
    @Query(value = "DELETE FROM user_role_user_permission_group WHERE user_role_id = ?1 AND user_permission_group_id = ?2",
            nativeQuery = true)
    void deleteByRoleIdAndGroupId(Integer roelId, Integer groupId);

    @Modifying
    @Query(value = "INSERT INTO user_role_user_permission_group (user_role_id, user_permission_group_id) VALUES (?1, ?2)",
            nativeQuery = true)
    void addGroup(Integer roleId, Integer groupId);

    @Query(value = "SELECT COUNT(*) FROM user_role_user_permission_group WHERE user_role_id = ?1 AND " +
            "user_permission_group_id = ?2", nativeQuery = true)
    Integer getCountByRoleIdAndGroupId(Integer roleId, Integer groupId);
}
