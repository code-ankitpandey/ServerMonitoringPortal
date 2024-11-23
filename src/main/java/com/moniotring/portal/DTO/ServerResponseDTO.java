package com.moniotring.portal.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServerResponseDTO {
    String IP;
    String Name;
    String userName;
    String Description;
    float consumptionAlertPercentage;
    float growthAlertPercentage;
    @JsonProperty("isActive")
    boolean isActive;
    int port;
}
