package org.zs.phm3.repository.unit;

import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitModelAttributeKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UnitModelAttributeRepository extends CrudRepository<UnitModelAttribute, UnitModelAttributeKey> {
    @Query(value = "SELECT COUNT(*)=0 FROM unit_model_attribute WHERE " +
            "attribute_key=?1 AND unit_model_id=?2", nativeQuery = true)
    Boolean isNew(String attributeKey, Integer unitModelId);

    @Query(value = "SELECT * FROM public.unit_model_attribute "+
            "WHERE unit_model_id=?1", nativeQuery = true)
    List<UnitModelAttribute> getAllByUnitModelId(Integer unitModelId);
}
