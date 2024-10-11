package org.zs.phm3.repository.ml;


import org.zs.phm3.models.ml.DatasetRegistry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetRegistryRepository extends CrudRepository<DatasetRegistry, Long> {

    @Query(value = "SELECT COUNT(*) FROM dataset_registry WHERE project_id = ?1", nativeQuery = true)
    Long getSizeByProjectId(Integer projectId);
}
