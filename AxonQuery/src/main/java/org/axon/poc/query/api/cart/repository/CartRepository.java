package org.axon.poc.query.api.cart.repository;

import org.axon.poc.query.api.cart.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem,String> {
    @Query(value = "from CartItem where cartId = :cartId")
    List<CartItem> findItemsByCartId(@Param("cartId") String cartId);

    @Query(value = "from CartItem where cartId = :cartId and item = :item")
    CartItem fetchByIdAndItem(@Param("cartId") String cartId, @Param("item") String item);
}
