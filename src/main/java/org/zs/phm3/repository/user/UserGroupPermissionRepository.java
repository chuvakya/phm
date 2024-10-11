package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserGroupPermission;
import org.zs.phm3.models.user.UserPermission;

import java.util.List;

@Transactional
@Repository
public interface UserGroupPermissionRepository extends CrudRepository<UserGroupPermission, Long> {


    @Query(value = "SELECT u.userPermissionGroup.id, u.userPermission.description, u.userPermission.id FROM UserGroupPermission u " +
            "WHERE u.userPermissionGroup.id IN ?1")
    List<List<Object>> getAllByUserPermissionGroupIds(List<Integer> userPermisiionGroupIds);


    @Query(value = "SELECT COUNT(*) FROM UserGroupPermission u WHERE u.userPermissionGroup.id = ?1 AND u.userPermission.id = ?2")
    Integer getCountByGroupIdAndPermissionId(Integer groupId, Integer permissionId);

    @Modifying
    @Query(value = "INSERT INTO user_group_permission (user_permission_group_id, user_permission_id) VALUES (?1, ?2)", nativeQuery = true)
    void addPermission(Integer groupId, Integer permissionId);

    @Modifying
    @Query(value = "DELETE FROM user_group_permission WHERE user_permission_group_id = ?1 AND user_permission_id = ?2", nativeQuery = true)
    void deleteByUserPermissionGroup_IdAnAndUserPermission_Id(Integer groupId, Integer permissionId);
}
