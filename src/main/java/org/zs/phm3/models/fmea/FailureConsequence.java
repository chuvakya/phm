package org.zs.phm3.models.fmea;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.basic.IdEntityDateTimeCreated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fmea_failure_consequences")
public class FailureConsequence extends IdEntityDateTimeCreated {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    @JsonIgnore
    private FailureMode failureMode;

    private String consequenceCode;

    @Column(unique = true, nullable = false, length = 128)//DDL
    private String name;

    @Transient
    private Integer failureModeId;
    public FailureConsequence() {
    }

    public FailureConsequence(Integer failureModeId, String consequenceCode, String name) {
        this.failureModeId = failureModeId;
        consequenceCode = consequenceCode;
        this.name = name;
    }
    public FailureConsequence(Integer failureModeId, String name) {
        this.failureModeId = failureModeId;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FailureMode getFailureMode() {
        return failureMode;
    }

    public void setFailureMode(FailureMode failureMode) {
        this.failureMode = failureMode;
    }

    public Integer getFailureModeId() {
        return failureModeId;
    }

    public void setFailureModeId(Integer failureModeId) {
        this.failureModeId = failureModeId;
    }

    public String getConsequenceCode() {
        return consequenceCode;
    }

    public void setConsequenceCode(String consequenceCode) {
        this.consequenceCode = consequenceCode;
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
