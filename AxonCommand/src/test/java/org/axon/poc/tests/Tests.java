package org.axon.poc.tests;

import com.axon.common.events.cart.ItemAddedEvent;
import com.axon.common.events.cart.ItemRemovedEvent;
import org.axon.poc.command.api.cart.aggregates.Cart;
import org.axon.poc.command.api.cart.commands.CartCreateCommand;
import org.axon.poc.command.api.cart.commands.ItemAddedCommand;
import org.axon.poc.command.api.cart.commands.ItemRemovedCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

public class Tests {

    private CartCreateCommand createCommand = new CartCreateCommand("12345");
    private final String item = "Pendrive";

    @Test
    void itemAddedEventTest() {

        AggregateTestFixture<Cart> fixture = new AggregateTestFixture<>(Cart.class);

        ItemAddedCommand addedCommandGiven = ItemAddedCommand.builder()
                .cartId(createCommand.getCartId())
                .item(item)
                .quantity(1)
                .build();

        ItemAddedCommand addedCommandTest = ItemAddedCommand.builder()
                .cartId(addedCommandGiven.getCartId())
                .item(addedCommandGiven.getItem())
                .quantity(3)
                .build();


        ItemAddedEvent addedEvent = ItemAddedEvent.builder()
                .cartId(addedCommandTest.getCartId())
                .item(addedCommandTest.getItem())
                .quantity(addedCommandTest.getQuantity())
                .build();

        fixture
                // create the aggregate
                .givenCommands(createCommand)
                //.andGiven(...) // optional
                .andGivenCommands(addedCommandGiven) // optional

                .when(addedCommandTest) // define which command you want to test

                // multiple optional expectation matchers
                .expectSuccessfulHandlerExecution() // optional
                .expectEvents(addedEvent) // optional
                //.expectState(...) // optional
                ;
    }

    @Test
    void itemRemovedEventTest() {

        AggregateTestFixture<Cart> fixture = new AggregateTestFixture<>(Cart.class);

        ItemAddedCommand addedCommandGiven = ItemAddedCommand.builder()
                .cartId(createCommand.getCartId())
                .item(item)
                .quantity(5)
                .build();

        ItemRemovedCommand removedCommandTest = ItemRemovedCommand.builder()
                .cartId(addedCommandGiven.getCartId())
                .item(addedCommandGiven.getItem())
                .quantity(3)
                .build();


        ItemRemovedEvent removedEvent = ItemRemovedEvent.builder()
                .cartId(removedCommandTest.getCartId())
                .item(removedCommandTest.getItem())
                .quantity(removedCommandTest.getQuantity())
                .build();

        fixture
                // create the aggregate
                .givenCommands(createCommand)
                //.andGiven(...) // optional
                .andGivenCommands(addedCommandGiven) // optional

                .when(removedCommandTest) // define which command you want to test

                // multiple optional expectation matchers
                .expectSuccessfulHandlerExecution() // optional
                .expectEvents(removedEvent) // optional
        //.expectState(...) // optional
        ;
    }
}
