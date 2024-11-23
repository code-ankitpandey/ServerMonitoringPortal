package com.moniotring.portal.Controller;


import com.moniotring.portal.DTO.MonitoringDataDTO;
import com.moniotring.portal.Service.SSHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitoringController {
    @Autowired
    private SSHService sshService;
    @GetMapping("/getMonitoring")
    public List<MonitoringDataDTO>getMonitoring(@RequestParam String serverIP){
        return sshService.getMonitoring(serverIP);
    }

}
