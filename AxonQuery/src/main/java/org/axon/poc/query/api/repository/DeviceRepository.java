package org.axon.poc.query.api.repository;

import org.axon.poc.query.api.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,String> {
}
