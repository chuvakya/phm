package org.zs.phm3.models.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class IdEntityDateTimeCreated {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    protected Integer id;


    public Integer getId() {
        return id;
    }

    @Column(nullable = false,name="DATE_CREATED")
    @JsonIgnore
    protected LocalDateTime dateCreated;

    public IdEntityDateTimeCreated() {
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    @ApiModelProperty(hidden = true)
    public Boolean isNew(){
        return id == null;
    };
}