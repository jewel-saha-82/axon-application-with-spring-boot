package org.axon.poc.query.api.controller;

import org.axon.poc.query.api.model.DeviceRestModel;
import org.axon.poc.query.api.model.ProductRestModel;
import org.axon.poc.query.api.queries.GetDeviceQuery;
import org.axon.poc.query.api.queries.GetProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    private QueryGateway queryGateway;

    public QueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/products")
    public List<ProductRestModel> getProducts() {

        GetProductQuery getProductQuery = new GetProductQuery();

        List<ProductRestModel> productRestModels =
                queryGateway.query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return productRestModels;
    }

    @GetMapping("/devices")
    public List<DeviceRestModel> getDevices() {

        GetDeviceQuery getDeviceQuery = new GetDeviceQuery();

        List<DeviceRestModel> deviceRestModels =
                queryGateway.query(getDeviceQuery, ResponseTypes.multipleInstancesOf(DeviceRestModel.class)).join();

        return deviceRestModels;
    }
}
