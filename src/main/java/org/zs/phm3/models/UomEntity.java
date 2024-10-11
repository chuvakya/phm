package org.zs.phm3.models;

import org.zs.phm3.models.basic.IdEntity;
import org.zs.phm3.models.basic.ToData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zs.phm3.dto.UomDto;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "UOM")
@JsonIgnoreProperties(value = {"new"})

public class UomEntity extends IdEntity implements ToData {

    @Column(unique = true, nullable = false)
    public String name;
    public String symbol;
    public String description;
    public Boolean display;
    public Boolean basic;

    public UomEntity() {
    }
    public UomEntity(String name, String symbol, UomTypes type, String description) {
        this.name=name;
        this.symbol=symbol;
        this.type=type;
        this.description=description;
    }

    @Enumerated(EnumType.STRING)
    public UomTypes type;

    public UomTypes getType() {
        return type;
    }

    public void setType(UomTypes type) {
        this.type = type;
    }

    public Boolean getBasic() {
        return basic;
    }

    public void setBasic(Boolean basic) {
        this.basic = basic;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public UomDto toData() {
        UomDto uomDtoNew;
        if (this.type==null) {
            uomDtoNew = new UomDto(this.id, "[ — ] " +
                    this.description
                    );
        }else {
            uomDtoNew = new UomDto(this.id, "[" + this.type.name() + "] " +
                   this.description
            );
        }
        return uomDtoNew;
    }
}
