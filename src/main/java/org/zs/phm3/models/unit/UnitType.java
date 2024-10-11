package org.zs.phm3.models.unit;

import org.zs.phm3.models.basic.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.zs.phm3.models.FileEntity;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@JsonIgnoreProperties(value = {"new"})
public class UnitType extends IdEntity {
    //    @Id
    @Column(length=255, unique=true, nullable = false)//DDL
//    @jakarta.validation.constraints.NotEmpty(message = "Please provide a name")//spring validation
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
//    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pictfile_id")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private FileEntity picture_file;

    @Transient
    private Integer pic_id;
    public UnitType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitType(String name) {
        this.name = name;
    }

    public FileEntity getPicture_file() {
        return picture_file;
    }

    public void setPicture_file(FileEntity picture_file) {
        this.picture_file = picture_file;
    }

    public Integer getPic_id() {
        return pic_id;
    }

    public void setPic_id(Integer pic_id) {
        this.pic_id = pic_id;
    }
    @PostLoad
    @PostUpdate
    private void onLoadAndUpdate() {
//        this.priceWithTaxes = price * taxes;

        if (picture_file != null)
            this.pic_id = picture_file.getId();
    }
}
