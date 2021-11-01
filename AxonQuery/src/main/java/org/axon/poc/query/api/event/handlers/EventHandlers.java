package org.axon.poc.query.api.event.handlers;

import com.axon.common.event.DeviceCreatedEvent;
import com.axon.common.event.ProductCreatedEvent;
import org.axon.poc.query.api.entities.Device;
import org.axon.poc.query.api.entities.Product;
import org.axon.poc.query.api.repository.DeviceRepository;
import org.axon.poc.query.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("errorHandler")
public class EventHandlers {

    private ProductRepository productRepository;
    private DeviceRepository deviceRepository;

    public EventHandlers(ProductRepository productRepository, DeviceRepository deviceRepository) {
        this.productRepository = productRepository;
        this.deviceRepository = deviceRepository;
    }

    @EventHandler
    public void productEventHandler(ProductCreatedEvent event) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
        //throw new Exception("Threw exception...");
    }

    @EventHandler
    public void deviceEventHandler(DeviceCreatedEvent event) throws Exception {
        Device device = new Device();
        BeanUtils.copyProperties(event, device);
        deviceRepository.save(device);
        //throw new Exception("Threw exception...");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        System.out.println("Exception Occurred.");
        throw exception;
    }
}
