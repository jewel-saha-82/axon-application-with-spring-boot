package org.axon.poc.command.api.test.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateDeviceCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String type;
}
