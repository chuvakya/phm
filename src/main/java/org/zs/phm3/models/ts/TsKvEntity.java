package org.zs.phm3.models.ts;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "ts_kv")
public class TsKvEntity {


    @EmbeddedId
    private TsKvId id;


    @Column(name = "bool_v")
    protected Boolean booleanValue;


    @Column(name = "str_v")
    protected String strValue;


    @Column(name = "long_v")
    protected Long longValue;


    @Column(name = "dbl_v")
    protected Double doubleValue;


    public TsKvEntity() {
    }

    public TsKvEntity(TsKvId tsKvId, Double doubleValue) {
        this.id = tsKvId;
        this.doubleValue = doubleValue;
    }

    public TsKvEntity(TsKvId tsKvId, Boolean booleanValue) {
        this.id = tsKvId;
        this.booleanValue = booleanValue;
    }

    public TsKvEntity(TsKvId tsKvId, String strValue) {
        this.id = tsKvId;
        this.strValue = strValue;
    }

    public TsKvEntity(TsKvId tsKvId, Long longValue) {
        this.id = tsKvId;
        this.longValue = longValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public TsKvId getId() {
        return id;
    }

    public void setId(TsKvId id) {
        this.id = id;
    }
}
