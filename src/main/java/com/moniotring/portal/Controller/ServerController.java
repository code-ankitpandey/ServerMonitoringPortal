package com.moniotring.portal.Controller;

import com.moniotring.portal.DTO.ServerDTO;
import com.moniotring.portal.DTO.ServerResponseDTO;
import com.moniotring.portal.DTO.UserResponseDTO;
import com.moniotring.portal.Service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/server")
public class ServerController {

    @Autowired
    private ServerService serverService;
    @PostMapping("/addServer")
    public String addServer(@RequestBody ServerDTO serverDTO){
        return serverService.addServer(serverDTO);
    }

    @GetMapping("/getServer")
    public ServerResponseDTO getServer(@RequestParam String serverIP) {
        return serverService.getServer(serverIP);
    }

    @PutMapping("/modifyServer")
    public String modifyServer(@RequestBody ServerResponseDTO serverResponseDTO) {
        return serverService.modifyServer(serverResponseDTO);
    }

    @PutMapping("/changePassword")
    public String resetPassword(@RequestParam String serverIP, String newPassword) {
        return serverService.resetPassword(serverIP, newPassword);
    }

}
