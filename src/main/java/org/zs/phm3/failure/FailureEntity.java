package org.zs.phm3.failure;

import org.zs.phm3.failure.failureid.FailureIdEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "failure")
public class FailureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String unitId;

    @ManyToOne
    private FailureIdEntity failureIdEntity;

    private Double basicProbability;

    public FailureEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public FailureIdEntity getFailureIdEntity() {
        return failureIdEntity;
    }

    public void setFailureIdEntity(FailureIdEntity failureIdEntity) {
        this.failureIdEntity = failureIdEntity;
    }

    public Double getBasicProbability() {
        return basicProbability;
    }

    public void setBasicProbability(Double basicProbability) {
        this.basicProbability = basicProbability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FailureEntity that = (FailureEntity) o;
        return Objects.equals(unitId, that.unitId) &&
                failureIdEntity.equals(that.failureIdEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, failureIdEntity);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", unitId='" + unitId + '\'' +
                ", failureIdEntity=" + failureIdEntity +
                '}';
    }
}
