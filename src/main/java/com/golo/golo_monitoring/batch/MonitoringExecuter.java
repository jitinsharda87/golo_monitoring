package com.golo.golo_monitoring.batch;

import com.golo.golo_monitoring.domain.HostDetail;
import com.golo.golo_monitoring.domain.HostStatus;
import com.golo.golo_monitoring.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import java.util.Calendar;
import java.util.List;

@Controller
public class MonitoringExecuter{


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MonitoringService monitoringService;

    @Scheduled(fixedRateString = "1000")
    public void monitorHost() {

        List<HostDetail> hostDetailList = monitoringService.getAllHostDetails("START");
        for (HostDetail hostDetail : hostDetailList) {
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(hostDetail.getHostName(), String.class);
                monitoringService.saveHostStatus(createHostStatus(hostDetail.getHostName(), response.getStatusCode().toString(), HttpStatus.valueOf(response.getStatusCodeValue()).getReasonPhrase()), hostDetail);
            } catch (HttpStatusCodeException e) {
                monitoringService.saveHostStatus(createHostStatus(hostDetail.getHostName(), e.getStatusCode().toString(), HttpStatus.valueOf(e.getRawStatusCode()).getReasonPhrase()), hostDetail);

            }
        }
    }

    private HostStatus createHostStatus(String hostName, String responseCode, String status){
        HostStatus hostStatus = new HostStatus();
        hostStatus.setHostName(hostName);
        hostStatus.setCreateDateTime(Calendar.getInstance().getTime());
        hostStatus.setResponseCode(responseCode);
        hostStatus.setReturnStatus(status);
        return hostStatus;
    }

}