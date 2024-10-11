package org.zs.phm3.controller;

import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.FileType;
import org.zs.phm3.util.GetNullProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.FileService;

import java.io.IOException;


@RestController
    @RequestMapping("api/file/")
    public class FileController {

        @Autowired
        FileService fileService;

        @GetMapping(value = "img/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
        public byte[] getImgById(@PathVariable("id") Integer id) throws PhmException {
            return fileService.findById(id).getFile();
        }

        @GetMapping(value = "small-img/{id}", produces = MediaType.IMAGE_JPEG_VALUE )
        public byte[] getSmallImgById(@PathVariable("id") Integer id) throws PhmException {
            return fileService.findById(id).getSmallImg();
        }


        @GetMapping(value = "img/list")
        public Iterable<Integer> getImgIdsList(){
            return fileService.findAllImg();
        }


        @GetMapping(value = "info/{id}", produces = "application/json")
        public String getFileInfoById(@PathVariable("id") Integer id) throws PhmException {
            return fileService.findById(id).toString();
        }


        @PostMapping(value = "img/")
        public Integer saveImg(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("description") String description) throws IOException, PhmException {
            FileEntity fileEntityForSave = new FileEntity(file.getBytes(), name, description, FileType.IMAGE);
            FileEntity fileEntity = fileService.findById(fileService.save(fileEntityForSave).getId());
            if (fileEntityForSave.equals(fileEntity)){
                return fileEntityForSave.getId();
            }
            return null;
        }


        @PutMapping(value = "img/{id}")
        public Integer updateImgById(@PathVariable("id") Integer id, @RequestParam(name = "file", defaultValue = "")
                MultipartFile file, @RequestParam(name = "name", defaultValue = "") String name,
                                      @RequestParam(name = "description", defaultValue = "") String description) throws IOException, PhmException {
            FileEntity fileEntityForSave = new FileEntity(file.getBytes(), name, description, FileType.IMAGE);
            FileEntity fileEntity = fileService.findById(id);
            BeanUtils.copyProperties(fileEntityForSave, fileEntity, GetNullProperties.getNullPropertyNames(fileEntityForSave));
            fileService.save(fileEntity);
            if(fileEntity.equals(fileEntityForSave)){
                return fileEntityForSave.getId();
            }
            return null;
        }

        @DeleteMapping(value = "{id}")
        public boolean deleteFileById(@PathVariable("id") Integer entityId) throws PhmException {
            fileService.deleteById(entityId);
            return true;
        }


        @GetMapping(value = "ico/list")
        public Iterable<Integer> getIcoIdsList(){
            return fileService.findAllIco();
        }

        @PostMapping(value = "ico/")
        public Integer saveIco(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("description") String description) throws IOException, PhmException {
            FileEntity fileEntity = new FileEntity(file.getBytes(), name, description, FileType.ICO);
            return fileService.save(fileEntity).getId();
        }

        @GetMapping(value = "ico/{id}", produces = MediaType.IMAGE_JPEG_VALUE )
        public byte[] getIcoById(@PathVariable("id") Integer id) throws PhmException {
            return fileService.findById(id).getSmallImg();
        }
    }
