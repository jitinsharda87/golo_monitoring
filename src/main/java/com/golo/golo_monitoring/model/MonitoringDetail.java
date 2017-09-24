package com.golo.golo_monitoring.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by jsharda3 on 2017-09-22.
 */
public class MonitoringDetail{

    @NotNull
    @Valid
    private String hostname;

    @NotNull
    @Valid
    private Long timeInterval;

    @Valid
    @NotNull
    private String status;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
