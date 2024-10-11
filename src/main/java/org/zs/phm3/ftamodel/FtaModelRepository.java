package org.zs.phm3.ftamodel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FtaModelRepository extends CrudRepository<FtaModelEntity, Long> {
    FtaModelEntity findByProjectId(Long projectId);
    void deleteByProjectId(Long projectId);

}
