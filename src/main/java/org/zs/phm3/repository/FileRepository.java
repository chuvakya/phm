package org.zs.phm3.repository;

import org.zs.phm3.models.FileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FileRepository  extends CrudRepository<FileEntity, Integer> {


//    Iterable<FileEntity> findAllByFileType(FileType fileType);


    @Query(value = "SELECT id FROM files WHERE file_type = 'IMAGE'", nativeQuery = true)
    Iterable<Integer> findAllImgIds();

    @Query(value = "SELECT id FROM files WHERE file_type = 'ICO'", nativeQuery = true)
    Iterable<Integer> findAllIcoIds();

}

