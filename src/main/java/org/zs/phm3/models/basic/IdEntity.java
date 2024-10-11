package org.zs.phm3.models.basic;

//import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass

public abstract class IdEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    protected Integer id;

//    protected LocalDate dateCreated;

    public Integer getId() {
        return id;
    }

    //    public void setId(int id) {
//        this.id = id;
//    }
    @ApiModelProperty(hidden = true)
    public boolean isNew() {
        return this.id == null;
    }
}