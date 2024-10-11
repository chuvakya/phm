package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zs.phm3.models.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "FROM UserEntity WHERE isDeleted = false ORDER BY name")
    List<UserEntity> getAll();
    UserEntity getByLogin(String login);

    @Modifying
    @Query(value = "INSERT INTO user_entity_user_role " +
            "(user_entity_id, user_role_id) VALUES (?1, ?2)", nativeQuery = true)
    void addUserEntityIdAndUserRoleId(Long userEntityId, Integer userRoleId);

    @Modifying
    @Query(value = "DELETE FROM user_entity_user_role WHERE user_entity_id = ?1 AND user_role_id = ?2", nativeQuery = true)
    void deleteRoleByUserIdAndRoleId(Long userEntityId, Integer userRoleId);

}
