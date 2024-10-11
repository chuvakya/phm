package org.zs.phm3.models;

import org.zs.phm3.data.UUIDConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class SchemeEntityId implements Serializable {
    @Column(length = 31)//DDL
    private String unitId;
        @Enumerated(EnumType.ORDINAL)
    private SchemeType schemeType;


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

    public SchemeEntityId(String unitId, SchemeType schemeType) {

        this.unitId = UUIDConverter.fromTimeUUID(UUID.fromString(unitId));

        this.schemeType = schemeType;
    }

    public SchemeEntityId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemeEntityId that = (SchemeEntityId) o;
        return Objects.equals(unitId, that.unitId) &&
                schemeType == that.schemeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, schemeType);
    }
}
