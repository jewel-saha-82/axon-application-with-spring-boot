package org.axon.poc.command.api.event.handlers;

import com.axon.common.event.DeviceCreatedEvent;
import com.axon.common.event.ProductCreatedEvent;
import org.axon.poc.command.api.entities.Device;
import org.axon.poc.command.api.entities.Product;
import org.axon.poc.command.api.repository.DeviceRepository;
import org.axon.poc.command.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EventHandlers {

    private ProductRepository productRepository;
    private DeviceRepository deviceRepository;

    public EventHandlers(ProductRepository productRepository, DeviceRepository deviceRepository) {
        this.productRepository = productRepository;
        this.deviceRepository = deviceRepository;
    }

    @EventHandler
    public void productEventHandler(ProductCreatedEvent event) {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
    }

    @EventHandler
    public void deviceEventHandler(DeviceCreatedEvent event) {
        Device device = new Device();
        BeanUtils.copyProperties(event, device);
        deviceRepository.save(device);
    }
}
