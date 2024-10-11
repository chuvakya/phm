package org.zs.phm3.models.unit;

//import jakarta.validation.constraints.NotEmpty;
import org.zs.phm3.models.basic.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaModelReferenceInspection")
@Entity
@JsonIgnoreProperties(value = {"new"})
//@Table(name = "unit_type")
public class UnitModel extends IdEntity {
    @Column(length=255,unique = true)//DDL
//    @NotEmpty(message = "Please provide a name")//spring validation
    private String model;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)//(name = "parent")
    private UnitType type;

    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private List<UnitEntity> unitEntities = new ArrayList<>();

    @JsonIgnore
    @Transient
    @OneToOne(mappedBy = "unitModel")
    private UnitEntity unit;

    public List<UnitEntity> getUnitEntities() {
        return unitEntities;
    }

    public void setUnitEntities(List<UnitEntity> unitEntities) {
        this.unitEntities = unitEntities;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UnitType getType() {
        return type;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public UnitModel(String model, UnitType type) {
        this.model = model;
        this.type = type;
    }

//    public UnitModel(String model, Optional<UnitType> type_1) {
//    }

    public UnitModel() {
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }
    @Override
    public String toString(){
        return ("model: id = "+this.getId()+", name= "+this.getModel()+", type = '"+this.getType().getName()+"'");
    }
}
