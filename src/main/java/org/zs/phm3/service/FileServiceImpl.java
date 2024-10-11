package org.zs.phm3.service;

import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.FileEntity;
import org.zs.phm3.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public FileEntity save(FileEntity fileEntityForSave) {
        return fileRepository.save(fileEntityForSave);
    }

    @Override
    public FileEntity findById(Integer idEntity) throws PhmException {
        FileEntity fileEntity;
        try {
            fileEntity = fileRepository.findById(idEntity).get();
        } catch (NoSuchElementException e) {
            throw new PhmException("File with id = "+idEntity+" not found. ",e, PhmErrorCode.ITEM_NOT_FOUND);
        }
        return fileEntity;
    }


    @Override
    public void deleteById(Integer idEntity) throws PhmException {
        try {
            fileRepository.deleteById(idEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new PhmException();
        }
    }

    @Override
    public Iterable<Integer> findAllImg() {
        return fileRepository.findAllImgIds();
    }

    @Override
    public Iterable<Integer> findAllIco() {
        return fileRepository.findAllIcoIds();
    }

    @Override
    public ArrayList<FileEntity> findAll() {
        return (ArrayList<FileEntity>) fileRepository.findAll();
    }

    @Override
    public void saveAll(List<FileEntity> filesList) {
        fileRepository.saveAll(filesList);
    }

}

