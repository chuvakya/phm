package org.zs.phm3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ml-service")
public class MlServiceProperties {

    private String mlFlowServer;
    private String mlFlowRestServer;
    private String datasetFolder;
    private String artifactsFolder;
    private String tempDir;

    private Autoscan autoscan;
    private Long delay;

    @Value("${ml-service.ml-flow-rest-server}")
    private String mlFlowRestServer1;

    @Value("${ml-service.autoscan.fixed.rate}")
    private String rate;

    public MlServiceProperties() {
    }

    public String getMlFlowServer() {
        return mlFlowServer;
    }

    public void setMlFlowServer(String mlFlowServer) {
        this.mlFlowServer = mlFlowServer;
    }

    public String getMlFlowRestServer() {
        return mlFlowRestServer;
    }

    public void setMlFlowRestServer(String mlFlowRestServer) {
        this.mlFlowRestServer = mlFlowRestServer;
    }

    public String getDatasetFolder() {
        return datasetFolder;
    }

    public void setDatasetFolder(String datasetFolder) {
        this.datasetFolder = datasetFolder;
    }

    public String getArtifactsFolder() {
        return artifactsFolder;
    }

    public void setArtifactsFolder(String artifactsFolder) {
        this.artifactsFolder = artifactsFolder;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public Autoscan getAutoscan() {
        return autoscan;
    }

    public void setAutoscan(Autoscan autoscan) {
        this.autoscan = autoscan;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public String getMlFlowRestServer1() {
        return mlFlowRestServer1;
    }

    public void setMlFlowRestServer1(String mlFlowRestServer1) {
        this.mlFlowRestServer1 = mlFlowRestServer1;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
