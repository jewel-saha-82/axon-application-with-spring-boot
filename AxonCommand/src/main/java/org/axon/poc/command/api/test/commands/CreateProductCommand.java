package org.axon.poc.command.api.test.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;
}
