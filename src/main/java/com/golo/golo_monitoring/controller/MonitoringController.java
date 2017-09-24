package com.golo.golo_monitoring.controller;

import com.golo.golo_monitoring.converter.HostDetailToMonitoringDetailConverter;
import com.golo.golo_monitoring.converter.HostStatusToMonitoringStatusConverter;
import com.golo.golo_monitoring.converter.MonitoringDetailToHostDetailConverter;
import com.golo.golo_monitoring.domain.HostDetail;
import com.golo.golo_monitoring.model.MonitoringDetail;
import com.golo.golo_monitoring.controller.exception.StatusValueNotValid;
import com.golo.golo_monitoring.model.MonitoringStatus;
import com.golo.golo_monitoring.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsharda3 on 2017-09-22.
 */

@RestController
@RequestMapping(MonitoringController.MONITOR_SERVICE)
public class MonitoringController {
    public static final String MONITOR_SERVICE = "monitor";

    @Autowired
    MonitoringService monitoringService;

    @Autowired
    MonitoringDetailToHostDetailConverter monitoringToHostDetailConverter;

    @Autowired
    HostDetailToMonitoringDetailConverter hostDetailToMonitoringDetailConverter;

    @Autowired
    HostStatusToMonitoringStatusConverter hostStatusToMonitoringStatusConverter;

    @RequestMapping(path = "create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringDetail saveHostDetail(@Valid @NotNull @RequestBody final MonitoringDetail monitoringDetail) {
        if (monitoringDetail.getStatus().equals("START") || monitoringDetail.getStatus().equals("STOP")) {
            monitoringService.saveHostDetails(monitoringToHostDetailConverter.convert(monitoringDetail));
            return monitoringDetail;
        } else {
            throw new StatusValueNotValid();
        }
    }

    @RequestMapping(path = "hoststatus")
    public List<MonitoringStatus> getDetails(@Valid @RequestParam(value = "responseCode", required = false) final String responseCode,
                                             @Valid @RequestParam(value = "hostName", required = false) final String hostName) {
        return hostStatusToMonitoringStatusConverter.convert(monitoringService.getAllHostStatus(responseCode, hostName));
    }

    @RequestMapping(path = "hostlist")
    public List<MonitoringDetail> getAllMonitoredHost() {
        List<HostDetail> hostDetailList = monitoringService.findAllMonitoredHost();
        List<MonitoringDetail> monitoringDetailList = new ArrayList<>();
        for(HostDetail hostDetail : hostDetailList){
            monitoringDetailList.add(hostDetailToMonitoringDetailConverter.convert(hostDetail));
        }
        return monitoringDetailList;
    }

}




