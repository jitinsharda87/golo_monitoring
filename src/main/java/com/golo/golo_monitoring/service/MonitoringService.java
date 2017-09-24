package com.golo.golo_monitoring.service;

import com.golo.golo_monitoring.domain.HostDetail;
import com.golo.golo_monitoring.domain.HostStatus;
import com.golo.golo_monitoring.repository.HostDetailRepository;
import com.golo.golo_monitoring.repository.HostStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jsharda3 on 2017-09-23.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class MonitoringService {

    @Autowired
    private HostStatusRepository hostStatusRepository;

    @Autowired
    private HostDetailRepository hostDetailRepository;

    public void saveHostDetails(HostDetail hostDetail){
        hostDetailRepository.updateHostDetail(Calendar.getInstance().getTime(), hostDetail.getHostName());
        hostDetailRepository.save(hostDetail);

    }

    public List<HostDetail> getAllHostDetails(String status){
        return hostDetailRepository.findByStatusAndReplaceDateTimeNullAndNextExecutionTimeLessThanEqual(status,Calendar.getInstance().getTime());
    }


    public List<HostStatus> getAllHostStatus(String responseCode, String hostName){
        return hostStatusRepository.findAll(responseCode, hostName);
    }

    public List<HostDetail> findAllMonitoredHost(){
        return hostDetailRepository.findByReplaceDateTimeNull();
    }

    public void saveHostStatus(HostStatus hostStatus, HostDetail hostDetail){

        hostStatusRepository.save(hostStatus);
        hostDetailRepository.updateHostDetail(new Date(), hostDetail.getHostName());
        hostDetail.setNextExecutionTime(new Date(hostDetail.getNextExecutionTime().getTime() + hostDetail.getInterval()));
        hostDetailRepository.save(hostDetail);

    }
}
