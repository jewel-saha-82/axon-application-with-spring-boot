package org.axon.poc.query.api.projections;


import org.axon.poc.command.api.model.ProductRestModel;
import org.axon.poc.command.api.repository.ProductRepository;
import org.axon.poc.query.api.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductQueryProjection {

    private ProductRepository productRepository;

    public ProductQueryProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler(queryName = "queryAll")
    public List<ProductRestModel> handle(GetProductQuery query) {

        return productRepository.findAll().stream().map(x -> ProductRestModel
                .builder()
                .name(x.getName())
                .price(x.getPrice())
                .quantity(x.getQuantity())
                .build()).collect(Collectors.toList());

    }
}
