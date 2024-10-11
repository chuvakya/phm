package org.zs.phm3.models.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.zs.phm3.data.UUIDConverter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseSqlEntity<D> implements BaseEntity<D> {

    @Id
    @Column(name = "id", updatable = false, nullable = false, length = 31)

    protected String id;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Column(updatable = false, nullable = false)

    protected LocalDateTime dateCreated;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    protected LocalDateTime dateChanged;

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BaseSqlEntity() {
    }

    public LocalDateTime getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(LocalDateTime dateChanged) {
        this.dateChanged = dateChanged;
    }

    @Override
    public UUID getId() {
        if (id == null) {
            return null;
        }
        return UUIDConverter.fromString(id);
    }

    public void setId(UUID id) {
        this.id = UUIDConverter.fromTimeUUID(id);
    }

    protected UUID toUUID(String src) {
        return UUIDConverter.fromString(src);
    }

    protected String toString(UUID timeUUID) {
        return UUIDConverter.fromTimeUUID(timeUUID);
    }

/*    protected String toShortUUID(String longUUID){
        return UUIDConverter.fromTimeUUID(UUID.fromString(longUUID));
    }*/

    public boolean isNew() {
        return this.id == null;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseSqlEntity)) return false;
        final BaseSqlEntity<?> other = (BaseSqlEntity<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseSqlEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
