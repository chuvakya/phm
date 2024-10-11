package org.zs.phm3.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserRole;

import java.util.List;

@Transactional
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Query(value = "SELECT COUNT(*) FROM UserRole WHERE name = ?1")
    Integer getCountByName(String name);

    @Query(value = "FROM UserRole ORDER BY name")
    List<UserRole> getAll();


}
