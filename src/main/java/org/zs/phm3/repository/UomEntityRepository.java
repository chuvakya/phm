package org.zs.phm3.repository;

import org.zs.phm3.models.UomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UomEntityRepository extends CrudRepository<UomEntity, Integer> {

    @Query(value = "SELECT * FROM uom "+
            "WHERE display=true order by Type", nativeQuery = true)
    List<UomEntity> getAllForDisplay();

    @Query(value = "SELECT * FROM uom WHERE name=?1", nativeQuery = true)
    UomEntity fingByName(String name);
}