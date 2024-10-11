package org.zs.phm3.models;

//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "component")
public class ComponentEntity implements Serializable {// extends IdEntity {
    @Id
    String unit;
/*    @OneToOne
    @MapsId
    private UnitEntity unit;*/

    String name;
    String description;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public ComponentEntity() {
    }

    public ComponentEntity(String unit, String name, String description) {
        this.unit = unit;
        this.name = name;
        this.description = description;
    }
}
