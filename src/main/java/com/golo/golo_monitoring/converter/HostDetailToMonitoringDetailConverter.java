package com.golo.golo_monitoring.converter;

import com.golo.golo_monitoring.domain.HostDetail;
import com.golo.golo_monitoring.model.MonitoringDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jsharda3 on 2017-08-09.
 */

@Component
public class HostDetailToMonitoringDetailConverter implements Converter<HostDetail, MonitoringDetail> {

    @Override
    public MonitoringDetail convert(HostDetail hostDetail) {
        MonitoringDetail monitoringDetail = new MonitoringDetail();
        monitoringDetail.setStatus(hostDetail.getStatus());
        monitoringDetail.setHostname(hostDetail.getHostName());
        monitoringDetail.setTimeInterval(hostDetail.getInterval());
        return monitoringDetail;
    }
}