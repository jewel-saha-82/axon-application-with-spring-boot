package org.axon.poc.query.api.test.projections;

import org.axon.poc.query.api.test.model.DeviceRestModel;
import org.axon.poc.query.api.test.model.ProductRestModel;
import org.axon.poc.query.api.test.queries.GetDeviceQuery;
import org.axon.poc.query.api.test.queries.GetProductQuery;
import org.axon.poc.query.api.test.repository.DeviceRepository;
import org.axon.poc.query.api.test.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryProjection {

    private ProductRepository productRepository;
    private DeviceRepository deviceRepository;

    public QueryProjection(ProductRepository productRepository, DeviceRepository deviceRepository) {
        this.productRepository = productRepository;
        this.deviceRepository = deviceRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery query) {

        System.out.println("Entered into ProductQueryHandler.");

        return productRepository.findAll().stream().map(x -> ProductRestModel
                .builder()
                .name(x.getName())
                .price(x.getPrice())
                .quantity(x.getQuantity())
                .build()).collect(Collectors.toList());

    }

    @QueryHandler
    public List<DeviceRestModel> handle(GetDeviceQuery query) {

        System.out.println("Entered into DeviceQueryHandler.");

        return deviceRepository.findAll().stream().map(x -> DeviceRestModel
                .builder()
                .name(x.getName())
                .type(x.getType())
                .build()).collect(Collectors.toList());

    }
}
