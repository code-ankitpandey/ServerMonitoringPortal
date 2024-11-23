package com.moniotring.portal.Entity;


import com.moniotring.portal.DTO.MonitoringPK;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
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