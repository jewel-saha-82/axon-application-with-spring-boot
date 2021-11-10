package org.axon.poc.event.handler.test;

import com.axon.common.events.cart.ItemAddedEvent;
import com.axon.common.events.cart.ItemRemovedEvent;
import org.assertj.core.api.BDDAssertions;
import org.axon.poc.query.api.cart.handlers.CartHandlers;
import org.axon.poc.query.api.cart.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class EventHandlerTest {

    private CartRepository repo = Mockito.mock(CartRepository.class);
    private CartHandlers cartHandlers = new CartHandlers(repo);

    @Test
    void itemAddedEventTest() {
        ItemAddedEvent event = ItemAddedEvent.builder()
                .cartId("12345")
                .item("PenDrive")
                .quantity(3)
                .build();

        ArgumentCaptor<String> a1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> a2 = ArgumentCaptor.forClass(String.class);
        cartHandlers.on(event);
        Mockito.verify(repo).fetchByIdAndItem(a1.capture(), a2.capture());
        BDDAssertions.then(a1.getValue()).isEqualTo("12345");
        BDDAssertions.then(a2.getValue()).isEqualTo("PenDrive");

    }

    @Test
    void itemRemovedEventTest() {
        ItemRemovedEvent event = ItemRemovedEvent.builder()
                .cartId("12345")
                .item("PenDrive")
                .quantity(3)
                .build();

        ArgumentCaptor<String> a1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> a2 = ArgumentCaptor.forClass(String.class);
        cartHandlers.on(event);
        Mockito.verify(repo).fetchByIdAndItem(a1.capture(), a2.capture());
        BDDAssertions.then(a1.getValue()).isEqualTo("12345");
        BDDAssertions.then(a2.getValue()).isEqualTo("PenDrive");

    }
}
