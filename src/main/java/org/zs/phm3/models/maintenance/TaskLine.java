package org.zs.phm3.models.maintenance;

import org.zs.phm3.models.basic.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "MS_TASKLINES")
public class TaskLine extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "ms_taskheaders_id", nullable = false)//(name = "parent")
    private TaskHeader header;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    private String comment;

    @ManyToOne
    @JoinColumn//(nullable = false)
    private OperationTypeEntity typePlaned;

    @Column(nullable = false)
    private DataSourceType dataSource;

    @Column(nullable = false)
    LocalDateTime dateTaskStart;

    public TaskLine(TaskHeader header, DataSourceType dataSource, OperationTypeEntity typePlaned,
                    LocalDateTime dateTaskStart, String comment) {
        this.header=header;
        this.comment=comment;
        this.dataSource=dataSource;
        this.typePlaned=typePlaned;
        this.dateCreated=LocalDateTime.now();
        this.dateTaskStart=dateTaskStart;
    }

    public TaskLine() {
    }

    public TaskHeader getHeader() {
        return header;
    }

    public void setHeader(TaskHeader header) {
        this.header = header;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OperationTypeEntity getTypePlaned() {
        return typePlaned;
    }

    public void setTypePlaned(OperationTypeEntity typePlaned) {
        this.typePlaned = typePlaned;
    }

    public DataSourceType getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceType dataSource) {
        this.dataSource = dataSource;
    }

    public LocalDateTime getDateTaskStart() {
        return dateTaskStart;
    }

    public void setDateTaskStart(LocalDateTime dateTaskStart) {
        this.dateTaskStart = dateTaskStart;
    }

    /*    @ManyToOne
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


    @ManyToOne
    @JoinColumn(nullable = true)
    private OperationTypeEntity typeActual;

    private LocalDateTime actualDateOperationStart;
    private LocalDateTime actualDateOperationEnd;

    private String comment;

    public RegisterLine() {
    }

    public RegisterLine(UnitEntity unit, UserEntity user, OperationTypeEntity typePlaned,
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

    public static RegisterLine createNewTask(UnitEntity unit, UserEntity user, OperationTypeEntity typePlaned,
                                             LocalDateTime plannedDateOperationStart, String comment) {
        return new RegisterLine(unit, user, typePlaned, plannedDateOperationStart, comment);
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
    }*/
}
