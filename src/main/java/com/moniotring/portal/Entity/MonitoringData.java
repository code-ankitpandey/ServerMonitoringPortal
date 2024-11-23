package com.moniotring.portal.Entity;


import com.moniotring.portal.DTO.MonitoringPK;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class MonitoringData {

    @EmbeddedId
    private MonitoringPK id;  // Composite primary key

    private String size;

    private String used;

    private String available;

    @Column(name = "used_percentage")
    private String usedPercentage;
}