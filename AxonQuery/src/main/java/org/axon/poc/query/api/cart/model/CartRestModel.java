package org.axon.poc.query.api.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRestModel {
    private String item;
    private int quantity;
}
