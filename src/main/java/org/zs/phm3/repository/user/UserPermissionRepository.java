package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserPermission;

import java.util.List;

@Transactional
@Repository
public interface UserPermissionRepository extends CrudRepository<UserPermission, Integer> {

    @Query(value = "FROM UserPermission ORDER BY id")
    List<UserPermission> getAll();

}
