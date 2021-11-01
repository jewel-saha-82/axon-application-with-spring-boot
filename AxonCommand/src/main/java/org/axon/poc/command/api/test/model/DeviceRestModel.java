package org.axon.poc.command.api.test.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceRestModel {
    private String name;
    private String type;
}
