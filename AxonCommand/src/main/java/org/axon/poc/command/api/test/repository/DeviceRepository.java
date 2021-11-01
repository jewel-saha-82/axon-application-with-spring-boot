package org.axon.poc.command.api.test.repository;

import org.axon.poc.command.api.test.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,String> {
}
