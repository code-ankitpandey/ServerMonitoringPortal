package com.moniotring.portal.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.moniotring.portal.DTO.MonitoringDataDTO;
import com.moniotring.portal.Entity.Server;
import com.moniotring.portal.Repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SSHService {

    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private PasswordService passwordService;
    public static String executeCommand(String ip, String userName, String password, int port, String command) {
        JSch jsch = new JSch();
        Session session = null;
        StringBuilder output = new StringBuilder();

        try {
            // Create the session with server's IP, username, and port
            session = jsch.getSession(userName, ip, port);
            session.setPassword(password);

            // Disable strict host key checking (not recommended for production)
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            // Open a channel and execute the command
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);

            // Read the input stream (command output)
            InputStream inputStream = channelExec.getInputStream();
            channelExec.connect();

            // Read the output from the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n"); // Append the line to the output
                }
            }

            // Disconnect the channel and session
            channelExec.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }

        return output.toString(); // Return the accumulated output as a string
    }
    public static List<MonitoringDataDTO> parseDiskUsage(String input) {
        List<MonitoringDataDTO> monitoringList = new ArrayList<>();

        // Split the input into lines
        String[] lines = input.split("\n");

        // Loop through the lines, starting from the second line (skipping the header)
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();

            // Split each line by spaces (multiple spaces can exist, so use regex to handle it)
            String[] parts = line.split("\\s+");

            if (parts.length == 6) {
                String totalSize = parts[1];
                String usedSize = parts[2];
                String availableSize = parts[3];
                String usedPercentage = parts[4];
                String mountPoint = parts[5];

                // Create a Monitoring object and add it to the list
                MonitoringDataDTO monitoring = new MonitoringDataDTO(mountPoint, totalSize, availableSize, usedSize, usedPercentage);
                monitoringList.add(monitoring);
            }
        }

        return monitoringList;
    }
    public List<MonitoringDataDTO>getMonitoring(String IP){

        List<MonitoringDataDTO>list=new LinkedList<>();
        Optional<Server> optionalServer= serverRepository.findById(IP);
        if(optionalServer.isEmpty())return list;
        Server server=optionalServer.get();
        return parseDiskUsage(executeCommand(server.getIP(),server.getUserName(), passwordService.decryptPassword(server.getPassword()),server.getPort(),"df -h"));
    }
}
