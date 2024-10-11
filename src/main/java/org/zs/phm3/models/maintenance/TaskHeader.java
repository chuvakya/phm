package org.zs.phm3.models.maintenance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.basic.IdEntity;
import org.zs.phm3.models.ptl.ModelAttributePTL;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MS_TASKHEADERS")

public class TaskHeader extends IdEntity {
    @ManyToOne
    @JoinColumn(nullable = false)//(name = "parent")
    private UnitEntity unit;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private MsTaskStatus status;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    private LocalDateTime dateClosing;

    @ManyToOne
    @JoinColumn//(nullable = false)
    private OperationTypeEntity typeFinal;
    private String comment;

//    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
//    cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "header",cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<TaskLine> taskLinesList = new ArrayList<>();

    public TaskHeader() {
    }

    public TaskHeader(UnitEntity unit, UserEntity user, String comment) {
        this.unit = unit;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.status = MsTaskStatus.PLANNED;
        this.comment=comment;
    }

    public static TaskHeader createNew(UnitEntity unit, UserEntity user, String comment) {
        return new TaskHeader(unit, user, comment);
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

    public MsTaskStatus getStatus() {
        return status;
    }

    public void setStatus(MsTaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateTaskCreated) {
        this.dateCreated = dateTaskCreated;
    }

    public LocalDateTime getDateClosing() {
        return dateClosing;
    }

    public void setDateClosing(LocalDateTime dateTaskClosing) {
        this.dateClosing = dateTaskClosing;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OperationTypeEntity getTypeFinal() {
        return typeFinal;
    }

    public void setTypeFinal(OperationTypeEntity typeFinal) {
        this.typeFinal = typeFinal;
    }

    public List<TaskLine> getTaskLinesList() {
        return taskLinesList;
    }

    public void setTaskLinesList(List<TaskLine> taskLinesList) {
        this.taskLinesList = taskLinesList;
    }

    @Override
    public String toString() {
        return "TaskHeader{" +
                "unit=" + unit.getId() +
                ", user=" + user +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateClosing=" + dateClosing +
                ", typeFinal=" + typeFinal +
                ", comment='" + comment + '\'' +
                ", taskLinesList=" + taskLinesList +
                '}';
    }
}
