package com.moniotring.portal.Repository;

import com.moniotring.portal.DTO.MonitoringPK;
import com.moniotring.portal.Entity.MonitoringData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringRepository extends JpaRepository<MonitoringData , MonitoringPK> {
    List<MonitoringData> findByIdServerIPAndIdDateRecorded(String serverIP, String dateRecorded);
}
