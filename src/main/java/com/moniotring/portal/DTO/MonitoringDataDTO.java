package com.moniotring.portal.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MonitoringDataDTO {
    @JsonProperty("mountPoint") // Map JSON field to this property
    String MountPoint;
    String totalSize;
    String availableSize;
    String usedSize;
    String usedPercentage;
    @JsonCreator
    public MonitoringDataDTO(@JsonProperty("mountPoint") String mountPoint,
                      @JsonProperty("totalSize") String totalSize,
                      @JsonProperty("availableSize") String availableSize,
                      @JsonProperty("usedSize") String usedSize,
                      @JsonProperty("usedPercentage") String usedPercentage) {
        this.MountPoint = mountPoint;
        this.totalSize = totalSize;
        this.availableSize = availableSize;
        this.usedSize = usedSize;
        this.usedPercentage = usedPercentage;
    }
}
