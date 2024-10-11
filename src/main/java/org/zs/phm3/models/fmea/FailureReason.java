package org.zs.phm3.models.fmea;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.basic.IdEntityDateTimeCreated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fmea_failure_reasons")
public class FailureReason extends IdEntityDateTimeCreated {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    @JsonIgnore
    private FailureMode failureMode;

    private String ReasonCode;
    @Column(unique = true, nullable = false, length = 128)//DDL
    private String name;

    @Transient
    private Integer failureModeId;

    public FailureReason() {
    }

    public FailureReason(Integer failureModeId, String reasonCode, String name) {
        this.failureModeId = failureModeId;
        ReasonCode = reasonCode;
        this.name = name;
    }

    public FailureReason(Integer failureModeId, String name) {
        this.failureModeId = failureModeId;
        this.name = name;
    }

    public FailureMode getFailureMode() {
        return failureMode;
    }

    public Integer getFailureModeId() {
        return failureModeId;
    }

    public void setFailureModeId(Integer failureModeId) {
        this.failureModeId = failureModeId;
    }

    public void setFailureMode(FailureMode failureModeId) {
        this.failureMode = failureModeId;
    }

    public String getReasonCode() {
        return ReasonCode;
    }

    public void setReasonCode(String reasonCode) {
        ReasonCode = reasonCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PrePersist
    private void PrePersist() {
        dateCreated = LocalDateTime.now();
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    private void onLoadAndUpdate() {
        failureModeId=this.getFailureMode().getId();
    }
}
