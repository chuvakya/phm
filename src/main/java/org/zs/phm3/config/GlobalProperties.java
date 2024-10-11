package org.zs.phm3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("global") // no prefix, root level.
public class GlobalProperties {

    @Value(value = "${global.time-zone}")
    private String timeZone;

    private int threadPool;
    private String email;
    private String sourceTestFilePath;
    private String dateTimeFormat;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public int getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(int threadPool) {
        this.threadPool = threadPool;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSourceTestFilePath() {
        return sourceTestFilePath;
    }

    public void setSourceTestFilePath(String sourceTestFilePath) {
        this.sourceTestFilePath = sourceTestFilePath;
    }
}
