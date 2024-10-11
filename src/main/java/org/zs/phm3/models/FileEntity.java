package org.zs.phm3.models;

import org.zs.phm3.models.basic.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.imgscalr.Scalr;


import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "files")
@JsonIgnoreProperties(value = {"new"})
public class FileEntity extends IdEntity {

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Lob
    @Column(name = "small_img")
    private byte[] smallImg;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    public FileEntity() {
    }

    public FileEntity(byte[] file, String name, String description, FileType fileType) {
        this.file = file;
        this.name = name;
        this.description = description;
        this.fileType = fileType;

        if (fileType == FileType.IMAGE){
            this.smallImg = setSmallImgValue(file, 100);
        } else if (fileType == FileType.ICO){
            this.smallImg = setSmallImgValue(file, 48);
        }
    }

    public byte[] getSmallImg() {
        return smallImg;
    }

    private byte[] setSmallImgValue(byte[] img, int targetSize) {
        ByteArrayInputStream bais = new ByteArrayInputStream(img);
        byte[] smallPic = null;
        try {
            BufferedImage origImg = ImageIO.read(bais);
            BufferedImage smallImg = Scalr.resize(origImg, targetSize);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(smallImg, "png", baos);
            smallPic = baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return smallPic;
    }

    public void setSmallImg(byte[] smallImg) {
        this.smallImg = smallImg;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return Arrays.equals(file, that.file) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                fileType == that.fileType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, description, fileType);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "{\"name\" : \"" + name + "\", " +
                "\"description\":\"" + description + "\"}";
    }

}
