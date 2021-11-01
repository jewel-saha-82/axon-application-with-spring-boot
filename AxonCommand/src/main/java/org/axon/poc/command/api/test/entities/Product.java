package org.axon.poc.command.api.test.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;
}
