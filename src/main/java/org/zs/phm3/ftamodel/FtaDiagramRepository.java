package org.zs.phm3.ftamodel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FtaDiagramRepository extends CrudRepository<FtaDiagramEntity, Long> {
    Iterable<FtaDiagramEntity> findByParentIdIsNull();

    @Query("select d from FtaDiagramEntity d where (d.parent is null and d.projectId = :projectId)")
    Iterable<FtaDiagramEntity> findByParentIdIsNullAndProjectId(Long projectId);

    Iterable<FtaDiagramEntity> findAllByProjectId(Long projectId);

}
