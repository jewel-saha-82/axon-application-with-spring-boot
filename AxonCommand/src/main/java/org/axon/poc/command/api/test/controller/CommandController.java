package org.axon.poc.command.api.test.controller;

import org.axon.poc.command.api.test.commands.CreateDeviceCommand;
import org.axon.poc.command.api.test.commands.CreateProductCommand;
import org.axon.poc.command.api.test.model.DeviceRestModel;
import org.axon.poc.command.api.test.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommandController {

    private CommandGateway commandGateway;

    public CommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = "/products/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createProduct(
            @RequestBody ProductRestModel productRestModel) {

        CreateProductCommand createProductCommand =
            CreateProductCommand
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .name(productRestModel.getName())
                    .price(productRestModel.getPrice())
                    .quantity(productRestModel.getQuantity())
                    .build();

        String result = commandGateway.sendAndWait(createProductCommand);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/devices/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createDevice(
            @RequestBody DeviceRestModel deviceRestModel) {

        CreateDeviceCommand createDeviceCommand =
                CreateDeviceCommand
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .name(deviceRestModel.getName())
                        .type(deviceRestModel.getType())
                        .build();

        String result = commandGateway.sendAndWait(createDeviceCommand);

        return ResponseEntity.ok().body(result);
    }
}
