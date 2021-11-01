package org.axon.poc.command.api.cart.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateCommand {
    @TargetAggregateIdentifier
    private String cartId;
}
