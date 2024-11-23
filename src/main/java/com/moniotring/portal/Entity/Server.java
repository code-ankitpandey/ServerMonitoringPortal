package com.moniotring.portal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Server {
    @Id
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
