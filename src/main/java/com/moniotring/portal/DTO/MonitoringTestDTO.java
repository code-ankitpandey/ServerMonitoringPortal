package com.moniotring.portal.DTO;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MonitoringTestDTO {
    String MountPoint;
    String totalSize;
    String availableSize;
    String usedSize;
    String usedPercentage;
    String serverIP;
    String dateRecorded;

    @JsonCreator
    public MonitoringTestDTO(@JsonProperty("mountPoint") String mountPoint,
                             @JsonProperty("totalSize") String totalSize,
                             @JsonProperty("availableSize") String availableSize,
                             @JsonProperty("usedSize") String usedSize,
                             @JsonProperty("usedPercentage") String usedPercentage,
                             @JsonProperty("dateRecorded") String dateRecorded,
                             @JsonProperty("serverIP") String ip) {
        this.MountPoint = mountPoint;
        this.totalSize = totalSize;
        this.availableSize = availableSize;
        this.usedSize = usedSize;
        this.usedPercentage = usedPercentage;
        this.dateRecorded = dateRecorded;
        this.serverIP = ip;
    }
}