package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.ModelSchemaRegistry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ModelSchemaRegistryRepository extends CrudRepository<ModelSchemaRegistry, Long> {

    @Query(value = "SELECT COUNT(*) FROM model_schema_registry WHERE project_id = ?1", nativeQuery = true)
    Long getSizeByProjectId(Integer projectId);

}
