package org.axon.poc.command.api.event.handlers;

import org.axon.poc.command.api.entities.Product;
import org.axon.poc.command.api.event.ProductCreatedEvent;
import org.axon.poc.command.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EventHandlers {

    private ProductRepository productRepository;

    public EventHandlers(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void productEventHandler(ProductCreatedEvent event) {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
    }
}
