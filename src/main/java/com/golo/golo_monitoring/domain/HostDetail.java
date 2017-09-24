package com.golo.golo_monitoring.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by jsharda3 on 2017-09-22.
 */
@Entity
@Table(name = "hostdetail")
public class HostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String hostName;

    private Long interval;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date nextExecutionTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date replaceDateTime;

    public Date getNextExecutionTime() {
        return nextExecutionTime;
    }

    public void setNextExecutionTime(Date nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getReplaceDateTime() {
        return replaceDateTime;
    }

    public void setReplaceDateTime(Date replaceDateTime) {
        this.replaceDateTime = replaceDateTime;
    }
}
