package com.moniotring.portal.DTO;

import lombok.Data;

@Data
public class ServerDTO {
    String IP;
    String password;
    String userName;
    String Name;
    String Description;
    float consumptionAlertPercentage;
    float growthAlertPercentage;
    boolean isActive;
    int port;
}
