package org.zs.phm3.models.fmea;

import org.zs.phm3.models.basic.IdEntityDateTimeCreated;
import org.zs.phm3.models.ptl.UnitTypePTL;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fmea_failure_modes")
public class FailureMode extends IdEntityDateTimeCreated {

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    @JsonIgnore
    private UnitTypePTL categoryType;*/

    @ManyToMany//(fetch = FetchType.EAGER)//(mappedBy = "likedCourses") cascade = {CascadeType.ALL},
            Set<UnitTypePTL> categoryTypes;


    @Column(name = "failure_code")
    private String failureCode;

/*    @Enumerated(EnumType.ORDINAL)
    private ProductLifeCycleStage lifeCycleStage;*/

    @Column(unique = true, nullable = false, length = 128)//DDL
    private String name;

    @Column(length = 256)//DDL
    private String description;

    @Transient
    private Set<Integer> categoryTypeIdsSet;

    public FailureMode() {
    }

    public FailureMode(String failureCode, String name, String description) {
        this.failureCode = failureCode;
        this.name = name;
        this.description = description;
    }

    public FailureMode(Set<Integer> categoryTypeIdsSet, String failureCode, String name, String description) {
        this.categoryTypeIdsSet = categoryTypeIdsSet;
//        this.lifeCycleStage = lifeCycleStage;
        this.failureCode = failureCode;
        this.name = name;
        this.description = description;
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

/*    public UnitTypePTL getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(UnitTypePTL categoryType) {
        this.categoryType = categoryType;
    }*/

/*    public Set<UnitTypePTL> getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Set<UnitTypePTL> categoryType) {
        this.categoryType = categoryType;
    }*/

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

/*    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }*/

    public Set<UnitTypePTL> getCategoryTypes() {
        return categoryTypes;
    }

    public void setCategoryTypes(Set<UnitTypePTL> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }

    public Set<Integer> getCategoryTypeIdsSet() {
        return categoryTypeIdsSet;
    }

    public void setCategoryTypeIdsSet(Set<Integer> categoryTypeIdsSet) {
        this.categoryTypeIdsSet = categoryTypeIdsSet;
    }

    @PrePersist
    private void PrePersist() {
        dateCreated = LocalDateTime.now();
    }

/*    public ProductLifeCycleStage getLifeCycleStage() {
        return lifeCycleStage;
    }

    public void setLifeCycleStage(ProductLifeCycleStage lifeCycleStage) {
        this.lifeCycleStage = lifeCycleStage;
    }*/

    @PostPersist
    @PostUpdate
    @PostLoad
    private void onLoadAndUpdate() {
        if (this.getCategoryTypes() != null) {
            if (this.getCategoryTypeIdsSet()==null){
                Set<Integer> categoryTypesSet=new HashSet();
                this.setCategoryTypeIdsSet(categoryTypesSet);
            }
            for (UnitTypePTL unitType : this.getCategoryTypes()) {
                if (unitType.getId() != null) {
                    this.getCategoryTypeIdsSet().add(unitType.getId());
                }
            }
        }else {
            this.setCategoryTypeIdsSet(null);
        }
    }
}
