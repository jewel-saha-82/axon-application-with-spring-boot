package org.axon.poc.command.api.repository;

import org.axon.poc.command.api.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeviceRepository extends JpaRepository<Device,String> {
}
