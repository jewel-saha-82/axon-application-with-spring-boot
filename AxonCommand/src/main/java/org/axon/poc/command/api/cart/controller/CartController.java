package org.axon.poc.command.api.cart.controller;

import org.axon.poc.command.api.cart.commands.ItemAddedCommand;
import org.axon.poc.command.api.cart.commands.CartCreateCommand;
import org.axon.poc.command.api.cart.commands.ItemRemovedCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartController {

    private CommandGateway commandGateway;

    public CartController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create-cart")
    public String createCart() {
        return commandGateway.sendAndWait(new CartCreateCommand(UUID.randomUUID().toString()));
    }

    @PostMapping("/add/{cartId}/{item}/{quantity}")
    public String addItem(@PathVariable("item") String item,
                          @PathVariable("quantity") int quantity,
                          @PathVariable("cartId") String cartId) {

        return commandGateway.sendAndWait(ItemAddedCommand.builder()
                .cartId(cartId)
                .item(item)
                .quantity(quantity)
                .build());
    }

    @DeleteMapping("/remove/{cartId}/{item}/{quantity}")
    public String removeItem(@PathVariable("item") String item,
                             @PathVariable("quantity") int quantity,
                             @PathVariable("cartId") String cartId) {

        return commandGateway.sendAndWait(ItemRemovedCommand.builder()
                .cartId(cartId)
                .item(item)
                .quantity(quantity)
                .build());
    }
}
