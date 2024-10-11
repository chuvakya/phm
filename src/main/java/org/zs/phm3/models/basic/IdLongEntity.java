package org.zs.phm3.models.basic;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class IdLongEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    protected Long id;

    public Long getId() {
        return id;
    }
    @ApiModelProperty(hidden = true)
    public boolean isNew() {
        return this.id == null;
    }
}
