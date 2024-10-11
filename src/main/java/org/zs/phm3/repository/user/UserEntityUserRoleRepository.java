package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserEntityUserRole;
import org.zs.phm3.models.user.UserRole;

import java.util.List;

@Transactional
@Repository
public interface UserEntityUserRoleRepository extends CrudRepository<UserEntityUserRole, Long> {

    @Query(value = "SELECT ue.user_entity_id, ur.name FROM user_entity_user_role ue " +
            "JOIN user_role ur on ue.user_role_id = ur.id " +
            "ORDER BY ur.name", nativeQuery = true)
    List<List<Object>> getAllUserEntityIdAndRoleName();

    @Query(value = "SELECT u.userRole FROM UserEntityUserRole u WHERE u.userEntity.id = ?1 ORDER BY u.userRole.name")
    List<UserRole> getAllUserRoleByUserEntityId(Long userEntityId);

    @Query(value = "SELECT COUNT(*) FROM UserEntityUserRole WHERE userEntity.id = ?1 AND userRole.id = ?2")
    Integer getCountByUserIdAndRoleId(Long userId, Integer roleId);

}
