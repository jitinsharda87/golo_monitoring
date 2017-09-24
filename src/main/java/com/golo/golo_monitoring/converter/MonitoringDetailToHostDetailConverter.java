package com.golo.golo_monitoring.converter;

import com.golo.golo_monitoring.domain.HostDetail;
import com.golo.golo_monitoring.model.MonitoringDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Calendar;

/**
 * Created by jsharda3 on 2017-08-09.
 */

@Component
public class MonitoringDetailToHostDetailConverter implements Converter<MonitoringDetail, HostDetail> {

    @Override
    public HostDetail convert(MonitoringDetail monitoringDetail) {
        HostDetail hostDetail = new HostDetail();
        hostDetail.setCreateDateTime(Calendar.getInstance().getTime());
        hostDetail.setNextExecutionTime(Calendar.getInstance().getTime());
        hostDetail.setHostName(monitoringDetail.getHostname());
        hostDetail.setInterval(monitoringDetail.getTimeInterval());
        hostDetail.setStatus(monitoringDetail.getStatus());
        return hostDetail;
    }
}