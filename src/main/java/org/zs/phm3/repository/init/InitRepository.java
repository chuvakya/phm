package org.zs.phm3.repository.init;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.init.Init;

@Transactional
@Repository
public interface InitRepository extends CrudRepository<Init, Integer> {

    @Query(value = "FROM Init WHERE id = 1")
    Init getInit();
}
