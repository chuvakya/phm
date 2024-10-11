package org.zs.phm3.ftamodel;


public interface FtaDiagramService {
    FtaDiagramEntity save(FtaDiagramEntity ftaDiagramEntity);
    FtaDiagramEntity findById(Long id);
    Iterable<FtaDiagramEntity> findAll();
    void delete(Long id);
    Iterable<FtaDiagramEntity> findByParentIdIsNull();
    Iterable<FtaDiagramEntity> findByParentIdIsNullAndProjectId(Long projectId);
    Iterable<FtaDiagramEntity> getList1(Long projectId);
}
