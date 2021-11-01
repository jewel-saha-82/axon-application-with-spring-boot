package org.axon.poc.query.api.test.repository;

import org.axon.poc.query.api.test.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,String> {
}
