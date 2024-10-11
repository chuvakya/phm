package org.zs.phm3.models.maintenance;

import org.zs.phm3.models.basic.IdEntity;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "MS_REGISTER")
public class RegisterEntity extends IdEntity {
    @ManyToOne
    @JoinColumn(nullable = false)//(name = "parent")
    private UnitEntity unit;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime dateTaskCreated;
    @Column(nullable = false)
    private MsTaskStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OperationTypeEntity typePlaned;

    @Column(nullable = false)
    private LocalDateTime plannedDateOperationStart;
    private DataSourceType dataSource;

    @ManyToOne
    @JoinColumn(nullable = true)
    private OperationTypeEntity typeActual;

    private LocalDateTime actualDateOperationStart;
    private LocalDateTime actualDateOperationEnd;

    private String comment;

    public RegisterEntity() {
    }

    public RegisterEntity(UnitEntity unit, UserEntity user, OperationTypeEntity typePlaned,
                          LocalDateTime plannedDateOperationStart, String comment) {
        this.unit = unit;
        this.user = user;
        this.dateTaskCreated = LocalDateTime.now();
        this.status = MsTaskStatus.PLANNED;
        this.typePlaned = typePlaned;
        this.dataSource=DataSourceType.DOCUMENTATION;
        this.plannedDateOperationStart = plannedDateOperationStart;
        this.comment=comment;
    }

    public static RegisterEntity createNewTask(UnitEntity unit, UserEntity user, OperationTypeEntity typePlaned,
                                               LocalDateTime plannedDateOperationStart, String comment) {
        return new RegisterEntity(unit, user, typePlaned, plannedDateOperationStart, comment);
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTaskCreated() {
        return dateTaskCreated;
    }

    public void setDateTaskCreated(LocalDateTime dateTaskCreated) {
        this.dateTaskCreated = dateTaskCreated;
    }

    public MsTaskStatus getStatus() {
        return status;
    }

    public void setStatus(MsTaskStatus status) {
        this.status = status;
    }

    public OperationTypeEntity getTypePlaned() {
        return typePlaned;
    }

    public void setTypePlaned(OperationTypeEntity typePlaned) {
        this.typePlaned = typePlaned;
    }

    public LocalDateTime getPlannedDateOperationStart() {
        return plannedDateOperationStart;
    }

    public void setPlannedDateOperationStart(LocalDateTime plannedDateOperationStart) {
        this.plannedDateOperationStart = plannedDateOperationStart;
    }

    public DataSourceType getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceType dataSource) {
        this.dataSource = dataSource;
    }

    public OperationTypeEntity getTypeActual() {
        return typeActual;
    }

    public void setTypeActual(OperationTypeEntity typeActual) {
        this.typeActual = typeActual;
    }

    public LocalDateTime getActualDateOperationStart() {
        return actualDateOperationStart;
    }

    public void setActualDateOperationStart(LocalDateTime actualDateOperationStart) {
        this.actualDateOperationStart = actualDateOperationStart;
    }

    public LocalDateTime getActualDateOperationEnd() {
        return actualDateOperationEnd;
    }

    public void setActualDateOperationEnd(LocalDateTime actualDateOperationEnd) {
        this.actualDateOperationEnd = actualDateOperationEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
