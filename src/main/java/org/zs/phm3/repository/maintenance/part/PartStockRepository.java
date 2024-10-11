package org.zs.phm3.repository.maintenance.part;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.part.PartStock;

import java.util.List;

@Transactional
@Repository
public interface PartStockRepository extends CrudRepository<PartStock, Long> {

    @Query(value = "SELECT quantity, storage FROM PartStock WHERE part.id = ?1")
    List<List<Object>> getQuantityAndStorageByPartId(Long partId);

    List<PartStock> getAllByPart_Id(Long partId);

    @Query(value = "SELECT id, part.name, quantity FROM PartStock WHERE id IN ?1")
    List<List<Object>> getIdNameQuantityByPartStockIds(List<Long> partStockIds);

}
