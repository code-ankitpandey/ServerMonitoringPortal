package com.moniotring.portal.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class MonitoringPK implements Serializable {

    @Column(name = "server_ip")
    private String serverIP;

    @Column(name = "mounted_on")
    private String mountedOn;

    @Column(name = "date_recorded")
    private String dateRecorded;
}