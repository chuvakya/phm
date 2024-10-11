package org.zs.phm3.repository.ml;

import org.zs.phm3.models.ml.MlJob;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MlJobRepository extends CrudRepository<MlJob, Long> {

    @Query(value = "FROM MlJob mlJob WHERE mlJob.projectId = ?1")
    List<MlJob> getAllByProjectId(Integer projectId);

    @Query(value = "SELECT job.id, job.name, job.state, ue.name AS userName, job.create_time, job.elapsed_time, ms.ml_algorithm " +
            "FROM ml_job job " +
            "JOIN user_entity ue ON job.modified_by_id = ue.id " +
            "JOIN model_schema ms ON job.model_schema_id = ms.id " +
            "WHERE job.project_id = ?1 ORDER BY job.name ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM ml_job WHERE project_id = ?1", nativeQuery = true)
    Integer getCountByProjectId(Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM ml_job WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long mlJobId);
}
