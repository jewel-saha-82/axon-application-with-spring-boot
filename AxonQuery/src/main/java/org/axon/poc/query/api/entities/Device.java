package org.axon.poc.query.api.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Device {

    @Id
    private String id;
    private String name;
    private String type;
}
