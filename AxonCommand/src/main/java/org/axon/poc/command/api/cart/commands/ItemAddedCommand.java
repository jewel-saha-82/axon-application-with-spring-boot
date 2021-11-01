package org.axon.poc.command.api.cart.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@Builder
public class ItemAddedCommand {
    @TargetAggregateIdentifier
    private String cartId;
    private String item;
    private int quantity;
}
