package org.zs.phm3.service;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.FileEntity;

import java.util.ArrayList;
import java.util.List;

public interface FileService {
    FileEntity save(FileEntity fileEntityForSave);
    FileEntity findById(Integer idEntity) throws PhmException;
//    FileEntity updateById(Integer idEntity, FileEntity fileEntityForSave) throws PhmException;
    void deleteById(Integer idEntity) throws PhmException;
    Iterable<Integer> findAllImg();
    ArrayList<FileEntity> findAll();
    void saveAll(List<FileEntity> filesList);

    Iterable<Integer> findAllIco();

}

