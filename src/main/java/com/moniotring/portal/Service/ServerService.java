package com.moniotring.portal.Service;

import com.moniotring.portal.DTO.ServerDTO;
import com.moniotring.portal.DTO.ServerResponseDTO;
import com.moniotring.portal.Entity.Server;
import com.moniotring.portal.Repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServerService {
    @Autowired
    ServerRepository serverRepository;
    @Autowired PasswordService passwordService;

    public String addServer(ServerDTO serverDTO) {
        System.out.println(serverDTO);
        if(serverRepository.existsById(serverDTO.getIP())){
            return serverDTO.getIP()+" already exists in the system";
        }
        Server server =new Server();
        server.setActive(serverDTO.isActive());
        server.setPassword(passwordService.encryptPassword(serverDTO.getPassword()));
        server.setIP(serverDTO.getIP());
        server.setName(serverDTO.getName());
        server.setDescription(serverDTO.getDescription());
        server.setPort(serverDTO.getPort());
        server.setGrowthAlertPercentage(serverDTO.getGrowthAlertPercentage());
        server.setConsumptionAlertPercentage(serverDTO.getConsumptionAlertPercentage());
        server.setUserName(serverDTO.getUserName());
        serverRepository.save(server);
        return serverDTO.getIP()+" server has been added successfully";
    }

    public ServerResponseDTO getServer(String serverIP) {
        Optional<Server>optionalServer=serverRepository.findById(serverIP);
        if (optionalServer.isEmpty()){
            return new ServerResponseDTO();
        }
        ServerResponseDTO serverResponseDTO = new ServerResponseDTO();
        serverResponseDTO.setDescription(optionalServer.get().getDescription());
        serverResponseDTO.setPort(optionalServer.get().getPort());
        serverResponseDTO.setName(optionalServer.get().getName());
        serverResponseDTO.setActive(optionalServer.get().isActive());
        serverResponseDTO.setConsumptionAlertPercentage(optionalServer.get().getConsumptionAlertPercentage());
        serverResponseDTO.setGrowthAlertPercentage(optionalServer.get().getGrowthAlertPercentage());
        serverResponseDTO.setUserName(optionalServer.get().getUserName());
        return serverResponseDTO;
    }

    public String modifyServer(ServerResponseDTO serverResponseDTO) {
        System.out.println(serverResponseDTO);
        Optional<Server>optionalServer=serverRepository.findById(serverResponseDTO.getIP());
        if(optionalServer.isEmpty()){
            return serverResponseDTO.getIP()+" does not exists in the system";
        }
        optionalServer.get().setName(serverResponseDTO.getName());
        optionalServer.get().setPort(serverResponseDTO.getPort());
        optionalServer.get().setActive(serverResponseDTO.isActive());
        optionalServer.get().setGrowthAlertPercentage(serverResponseDTO.getGrowthAlertPercentage());
        optionalServer.get().setDescription(serverResponseDTO.getDescription());
        optionalServer.get().setConsumptionAlertPercentage(serverResponseDTO.getConsumptionAlertPercentage());
        optionalServer.get().setUserName(serverResponseDTO.getUserName());
        serverRepository.save(optionalServer.get());

        return serverResponseDTO.getIP()+" has been modified in the system";
    }

    public String resetPassword(String serverIP, String newPassword) {
        Optional<Server>optionalServer=serverRepository.findById(serverIP);
        if(optionalServer.isEmpty()){
            return serverIP+" does not exists in the system";
        }
        optionalServer.get().setPassword(passwordService.encryptPassword(newPassword));
        serverRepository.save(optionalServer.get());
        return "Password reset successfully";
    }
}
