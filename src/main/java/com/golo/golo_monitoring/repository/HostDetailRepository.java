package com.golo.golo_monitoring.repository;

import com.golo.golo_monitoring.domain.HostDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

/**
 * @author jitin sharda
 */
public interface HostDetailRepository extends Repository<HostDetail, Long> {

    public List findByStatusAndReplaceDateTimeNullAndNextExecutionTimeLessThanEqual(String status, Date nextExecutionTime);

    public List findByReplaceDateTimeNull();

    public void save(HostDetail hostDetail);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE HostDetail c SET c.replaceDateTime = :replaceDateTime WHERE c.hostName = :hostName")
    int updateHostDetail(@Param("replaceDateTime") Date replaceDateTime, @Param("hostName") String hostName);

}
