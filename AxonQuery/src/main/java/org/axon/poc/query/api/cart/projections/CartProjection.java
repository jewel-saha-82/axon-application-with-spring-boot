package org.axon.poc.query.api.cart.projections;

import org.axon.poc.query.api.cart.entities.CartItem;
import org.axon.poc.query.api.cart.model.CartRestModel;
import org.axon.poc.query.api.cart.query.GetCartQuery;
import org.axon.poc.query.api.cart.repository.CartRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartProjection {

    private CartRepository repo;

    public CartProjection(CartRepository cartRepository) {
        this.repo = cartRepository;
    }

    @QueryHandler
    public List<CartRestModel> on(GetCartQuery query) {
        List<CartItem> cartItems = repo.findItemsByCartId(query.getCartId());
        return cartItems.stream().map(x -> new CartRestModel(x.getItem(), x.getQuantity())).collect(Collectors.toList());
    }
}
