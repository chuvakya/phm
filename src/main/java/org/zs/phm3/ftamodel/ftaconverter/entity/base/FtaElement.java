package org.zs.phm3.ftamodel.ftaconverter.entity.base;

import org.zs.phm3.failure.FailureRepository;
import org.zs.phm3.util.SpringContext;

import java.util.UUID;


public abstract class FtaElement {

    private UUID id;
    private String xmlLink;
    private String ftaBlockId;           // id fta element from front fta builder
    private String ftaElementError;
    private Long failureEntityId;


    public FtaElement(Long failureEntityId, String ftaBlockId) {
        id = UUID.randomUUID();
        this.failureEntityId = failureEntityId;
        this.ftaBlockId = ftaBlockId;
        ftaElementError = "";
    }

    public Long getFailureEntityId() {
        return failureEntityId;
    }

    public void setFailureEntityId(Long failureEntityId) {
        this.failureEntityId = failureEntityId;
    }

    public String getFtaElementError() {
        return ftaElementError;
    }

    public void setFtaElementError(String ftaElementError) {
        this.ftaElementError = ftaElementError;
    }

    public String getFtaBlockId() {
        return ftaBlockId;
    }

    public void setFtaBlockId(String ftaBlockId) {
        this.ftaBlockId = ftaBlockId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getXmlLink() {
        return xmlLink;
    }

    public void setXmlLink(String xmlLink) {
        this.xmlLink = xmlLink;
    }

    public abstract boolean validate();
    public abstract String convert();

    public boolean isFailureEntityExist(){
        FailureRepository failureRepository = SpringContext.getBean(FailureRepository.class);
        return failureRepository.existsById(failureEntityId);
    }

}
