package com.golo.golo_monitoring.repository;

import com.golo.golo_monitoring.domain.HostStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author jitin sharda
 */
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public interface HostStatusRepository extends Repository<HostStatus, Long> {

    @Query("SELECT c FROM HostStatus c WHERE ( :responseCode IS NULL OR c.responseCode = :responseCode) AND ( :hostName IS NULL OR c.hostName = :hostName)")
    public List findAll(@Param("responseCode")String responseCode, @Param("hostName")String hostName);

    public void save(HostStatus hostStatus);

}
