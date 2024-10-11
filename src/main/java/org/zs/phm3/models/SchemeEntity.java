package org.zs.phm3.models;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.util.mapping.JsonStringType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity(name = "SCHEMES")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@IdClass(SchemeEntityId.class)
public class SchemeEntity {

    @Id

    @Column(length = 31)//DDL

    private String unitId;

    @Id
    private SchemeType schemeType;

    private String name;
    @Column(length = 128)//DDL
    private String label;
    @Type(type = "json")

    @Column(length = 1000000)//DDL

    private JsonNode body;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public SchemeType getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(SchemeType schemeType) {
        this.schemeType = schemeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonNode getBody() {
        return body;
    }

    public void setBody(JsonNode body) {
        this.body = body;
    }

    public SchemeEntity(String unitId, SchemeType schemeType, String name, JsonNode body, String label ) {
        if (unitId.length()>31)
            this.unitId = UUIDConverter.fromTimeUUID(UUID.fromString(unitId));
        else
            this.unitId = unitId;
        this.schemeType = schemeType;
        this.name = name;
        this.label = label;
        this.body = body;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SchemeEntity() {
    }

    @Override
    public String toString() {
        return "SchemeEntity{" +
                "unitId='" + unitId + '\'' +
                ", schemeType=" + schemeType +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
