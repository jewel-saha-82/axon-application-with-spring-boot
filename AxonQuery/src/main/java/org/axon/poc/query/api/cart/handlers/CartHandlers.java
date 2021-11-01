package org.axon.poc.query.api.cart.handlers;

import com.axon.common.events.cart.ItemAddedEvent;
import com.axon.common.events.cart.ItemRemovedEvent;
import org.axon.poc.query.api.cart.repository.CartRepository;
import org.axon.poc.query.api.cart.entities.CartItem;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("cartExceptionHandler")
public class CartHandlers {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private CartRepository repo;

    public CartHandlers(CartRepository repo) {
        this.repo = repo;
    }

    @EventHandler
    public void on(ItemAddedEvent event) {
        CartItem cart = repo.fetchByIdAndItem(event.getCartId(), event.getItem());
        if (cart != null)
            cart.setQuantity(cart.getQuantity() + event.getQuantity());
        else
            cart = CartItem.builder()
                    .cartId(event.getCartId())
                    .item(event.getItem())
                    .quantity(event.getQuantity())
                    .build();
        repo.save(cart);
    }

    @EventHandler
    public void on(ItemRemovedEvent event) {
        CartItem cart = repo.fetchByIdAndItem(event.getCartId(), event.getItem());
        if (cart != null)
            cart.setQuantity(cart.getQuantity() - event.getQuantity());
        else
            cart = CartItem.builder()
                    .cartId(event.getCartId())
                    .item(event.getItem())
                    .quantity(event.getQuantity())
                    .build();
        repo.save(cart);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        logger.warn(exception.getMessage());
    }
}
