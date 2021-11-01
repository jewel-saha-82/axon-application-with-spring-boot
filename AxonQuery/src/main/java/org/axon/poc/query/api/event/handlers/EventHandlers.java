package org.axon.poc.query.api.event.handlers;

import org.axon.poc.event.ProductCreatedEvent;
import org.axon.poc.query.api.entities.Product;
import org.axon.poc.query.api.repository.ProductRepository;
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

        System.out.println("Entered into Event Handlers...");

        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
    }
}
