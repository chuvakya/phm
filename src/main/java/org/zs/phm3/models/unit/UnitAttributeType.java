package org.zs.phm3.models.unit;


//import jakarta.validation.constraints.NotEmpty;
import org.zs.phm3.models.basic.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;

//import javax.validation.constraints.NotEmpty;
@Entity
@JsonIgnoreProperties(value = {"new"})
public class UnitAttributeType extends IdEntity {
/*    @Id
    @Column(length=255,unique = true)//DDL
    @NotEmpty(message = "Please provide a name")//spring validation*/
    @Column(length=255,unique = true)
    private String name;

    private String description;

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

    public UnitAttributeType() {
    }

//    public TsKvAttributeType(@NotEmpty(message = "Please provide a name") String name) {
        public UnitAttributeType(String name) {
        this.name = name;
    }
}
