package org.zs.phm3.ftamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class FtaDiagramServiceImpl implements FtaDiagramService {
    @Autowired
    FtaDiagramRepository ftaDiagramRepository;

    public FtaDiagramServiceImpl() {
    }

    public FtaDiagramEntity save(FtaDiagramEntity ftaDiagramEntity) {
        return ftaDiagramRepository.save(ftaDiagramEntity);
    }

    public FtaDiagramEntity findById(Long id) {
        return ftaDiagramRepository.findById(id).get();
    }

    public Iterable<FtaDiagramEntity> findAll() {
        return ftaDiagramRepository.findAll();
    }

    public void delete(Long id) {
        ftaDiagramRepository.delete(ftaDiagramRepository.findById(id).get());
    }

    public Iterable<FtaDiagramEntity> findByParentIdIsNull() {
        return ftaDiagramRepository.findByParentIdIsNull();
    }

    public Iterable<FtaDiagramEntity> findByParentIdIsNullAndProjectId(Long projectId) {
        return ftaDiagramRepository.findByParentIdIsNullAndProjectId(projectId);
    }

    public Iterable<FtaDiagramEntity> getList1(Long projectId){
        ArrayList<FtaDiagramEntity> list = new ArrayList<>();
        for (FtaDiagramEntity d: ftaDiagramRepository.findAllByProjectId(projectId)) {
            FtaDiagramEntity ftaDiagramEntity = new FtaDiagramEntity();
            ftaDiagramEntity.setId(d.getId());
            ftaDiagramEntity.setStatus(d.getStatus());
            ftaDiagramEntity.setName(d.getName());
            ftaDiagramEntity.setUser(d.getUser());
            list.add(ftaDiagramEntity);
        }
        return list;
    }




}