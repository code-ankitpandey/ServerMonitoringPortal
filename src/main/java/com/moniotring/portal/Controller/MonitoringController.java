package com.moniotring.portal.Controller;


import com.moniotring.portal.DTO.MonitoringDataDTO;
import com.moniotring.portal.Entity.MonitoringData;
import com.moniotring.portal.Service.SSHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/public/monitoring")
@RestController
public class MonitoringController {
    @Autowired
    private SSHService sshService;
    @GetMapping("/getLiveMonitoring")
    public List<MonitoringDataDTO>getMonitoring(@RequestParam String serverIP){
        return sshService.getMonitoring(serverIP);
    }
    @GetMapping("/getMonitoringOfDate")
    public List<MonitoringData> getMonitoringData(
            @RequestParam String serverIP,
            @RequestParam String dateRecorded) {
        return sshService.getMonitoringData(serverIP, dateRecorded);
    }

}
