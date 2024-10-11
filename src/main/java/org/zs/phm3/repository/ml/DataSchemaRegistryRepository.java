package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.DataSchemaRegistry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DataSchemaRegistryRepository extends CrudRepository<DataSchemaRegistry, Long> {

    @Query(value = "SELECT COUNT(*) FROM data_schema_registry WHERE project_id = ?1", nativeQuery = true)
    Long getSizeByProjectId(Integer projectId);

}
