package org.axon.poc.command.api.test.aggregate;

import com.axon.common.event.DeviceCreatedEvent;
import org.axon.poc.command.api.test.commands.CreateDeviceCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class DeviceAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String type;

    public DeviceAggregate() {
    }

    @CommandHandler
    public DeviceAggregate(CreateDeviceCommand createDeviceCommand) {
        DeviceCreatedEvent event = new DeviceCreatedEvent();
        BeanUtils.copyProperties(createDeviceCommand, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeviceCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.type = event.getType();

        System.out.println("Device Aggregate ----->>>>  id = " + id + ", name = " + name + ", type = " + type);
    }
}
