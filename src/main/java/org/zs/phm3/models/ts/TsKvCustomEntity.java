package org.zs.phm3.models.ts;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "ts_kv_custom")

public class TsKvCustomEntity {
    @EmbeddedId
    private TsKvId tsKvId;

    @Column(name = "dbl_v")
    protected Double doubleValue;

/*    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }*/

    public TsKvId getTsKvId() {
        return tsKvId;
    }

    public void setTsKvId(TsKvId tsKvId) {
        this.tsKvId = tsKvId;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public TsKvCustomEntity(TsKvId tsKvId, Double doubleValue) {
        this.tsKvId = tsKvId;
        this.doubleValue = doubleValue;
    }

    public TsKvCustomEntity() {
    }

}
