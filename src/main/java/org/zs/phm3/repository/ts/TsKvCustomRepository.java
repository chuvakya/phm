package org.zs.phm3.repository.ts;

import org.zs.phm3.models.ts.TsKvCustomEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface TsKvCustomRepository extends CrudRepository<TsKvCustomEntity, TsKvId> {

    @Query(value = "SELECT DISTINCT unit_id FROM ts_kv_custom ORDER BY unit_id", nativeQuery = true)
    List<Object[]> getAllUnitIdWithTsData();

    @Query("SELECT u FROM TsKvCustomEntity u WHERE u.tsKvId.unitId = ?1")
    Collection<TsKvCustomEntity> findTsForUnitId(String unitId);
}
