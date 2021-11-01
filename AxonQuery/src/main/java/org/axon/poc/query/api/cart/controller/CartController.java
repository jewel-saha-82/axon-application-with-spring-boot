package org.axon.poc.query.api.cart.controller;

import org.axon.poc.query.api.cart.model.CartRestModel;
import org.axon.poc.query.api.cart.query.GetCartQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    private QueryGateway queryGateway;

    public CartController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{cartId}")
    public List<CartRestModel> getCartIdDetails(@PathVariable("cartId") String cartId) {
        return queryGateway
                .query(new GetCartQuery(cartId), ResponseTypes.multipleInstancesOf(CartRestModel.class))
                .join();
    }
}
