package org.axon.poc.command.api.cart.aggregates;

import com.axon.common.events.cart.ItemAddedEvent;
import com.axon.common.events.cart.CartCreatedEvent;
import com.axon.common.events.cart.ItemRemovedEvent;
import org.axon.poc.command.api.cart.commands.ItemAddedCommand;
import org.axon.poc.command.api.cart.commands.CartCreateCommand;
import org.axon.poc.command.api.cart.commands.ItemRemovedCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;

@Aggregate
public class Cart {

    @AggregateIdentifier
    private String cartId;
    private Map<String, Integer> items;

    public Cart() {
    }
    
    @CommandHandler
    public Cart(CartCreateCommand command) {
        AggregateLifecycle.apply(new CartCreatedEvent(command.getCartId()));
    }

    @CommandHandler
    public void handle(ItemAddedCommand command) {
        AggregateLifecycle.apply(ItemAddedEvent.builder()
                .cartId(command.getCartId())
                .item(command.getItem())
                .quantity(command.getQuantity())
                .build());
    }

    @CommandHandler
    public void handle(ItemRemovedCommand command) {
        AggregateLifecycle.apply(ItemRemovedEvent.builder()
                .cartId(command.getCartId())
                .item(command.getItem())
                .quantity(command.getQuantity())
                .build());
    }

    @EventSourcingHandler
    public void on(ItemAddedEvent event) {
        if (items.containsKey(event.getItem())) {
            int val = items.get(event.getItem());
            items.put(event.getItem(), val+event.getQuantity());
        }
        else
            items.put(event.getItem(), event.getQuantity());

        System.out.println("Added: UUID = " + event.getCartId() + ", Item = " + event.getItem()
                + ", Current Quantity = " + items.get(event.getItem()));
    }

    @EventSourcingHandler
    public void on(ItemRemovedEvent event) {
        if (items.containsKey(event.getItem())) {
            int val = items.get(event.getItem());
            items.put(event.getItem(), val-event.getQuantity());
        }

        System.out.println("Removed: UUID = " + event.getCartId() + ", Item = " + event.getItem()
                + ", Current Quantity = " + items.get(event.getItem()));
    }

    @EventSourcingHandler
    public void on(CartCreatedEvent event) {
        this.cartId = event.getCartId();
        items = new HashMap<>();
    }


}
