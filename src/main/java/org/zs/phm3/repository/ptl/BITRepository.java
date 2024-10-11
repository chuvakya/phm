package org.zs.phm3.repository.ptl;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.ptl.BIT;

import java.util.List;

@Transactional
@Repository
public interface BITRepository extends CrudRepository<BIT, Long> {

    @Modifying
    @Query(value = "DELETE FROM bit_ptl WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long bitId);

    @Query(value = "SELECT bit.error_code, bit.name AS bit_name, ue.name AS userName, " +
            "bit.update_time, model.name AS model_name, manufacturer.name AS manufacturer_name, bit.id, model.revision " +
            "FROM bit_ptl bit " +
            "JOIN model_ptl model ON bit.model_ptl_id = model.id " +
            "JOIN manufacturer_ptl manufacturer ON model.manufacturer_ptl = manufacturer.id " +
            "JOIN user_entity ue ON bit.modified_by_id = ue.id " +
            "ORDER BY manufacturer.name ASC OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<List<Object>> getAllByOffsetAndLimit(Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM bit_ptl", nativeQuery = true)
    Long getCount();

    @Query(value = "SELECT errorCode FROM BIT WHERE modelPTL.id = ?1")
    List<String> getErrorCodesByModelId(Long modelId);

    @Query(value = "SELECT bit.id AS bit_id, bit.name, bit.cause, bit.description, bit.error_code, bit.priority, " +
            "bit.severity, bit.troubleshooting, bit.model_ptl_id, model.manufacturer_ptl AS manufacturer_id, model.unit_type_ptl_id AS unit_type_id " +
            "FROM bit_ptl bit CROSS JOIN model_ptl model " +
            "WHERE bit.id = ?1 AND bit.model_ptl_id = model.id", nativeQuery = true)
    List<List<Object>> getSaveBITById(Long bitId);

    @Query(value = "SELECT b.id, b.name, b.errorCode FROM BIT b WHERE b.modelPTL.id = ?1 ORDER BY b.errorCode")
    List<List<Object>> getAllIdAndNameAndCodeByModelPTLId(Long modelPTLId);

}
