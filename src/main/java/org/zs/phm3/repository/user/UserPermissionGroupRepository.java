package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserPermissionGroup;

import java.util.List;

@Transactional
@Repository
public interface UserPermissionGroupRepository extends CrudRepository<UserPermissionGroup, Integer> {

    @Query(value = "SELECT COUNT(*) FROM UserPermissionGroup WHERE name = ?1")
    Integer getCountByName(String name);

    @Query(value = "FROM UserPermissionGroup ORDER BY name")
    List<UserPermissionGroup> getAll();

}
