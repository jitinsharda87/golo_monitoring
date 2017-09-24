package com.golo.golo_monitoring.converter;

import com.golo.golo_monitoring.domain.HostDetail;
import com.golo.golo_monitoring.domain.HostStatus;
import com.golo.golo_monitoring.model.MonitoringDetail;
import com.golo.golo_monitoring.model.MonitoringStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsharda3 on 2017-08-09.
 */

@Component
public class HostStatusToMonitoringStatusConverter implements Converter<List<HostStatus>, List<MonitoringStatus>> {

    @Override
    public List<MonitoringStatus> convert(List<HostStatus> hostStatusList) {
        List<MonitoringStatus> monitoringStatusList = new ArrayList<>();

        for(HostStatus hostStatus : hostStatusList){
            MonitoringStatus monitoringStatus = new MonitoringStatus();
            monitoringStatus.setHostName(hostStatus.getHostName());
            monitoringStatus.setCreateDateTime(hostStatus.getCreateDateTime().toString());
            monitoringStatus.setId(hostStatus.getId().toString());
            monitoringStatus.setReturnCode(hostStatus.getResponseCode());
            monitoringStatus.setReturnStatus(hostStatus.getReturnStatus());
            monitoringStatusList.add(monitoringStatus);
        }

        return monitoringStatusList;
    }
}