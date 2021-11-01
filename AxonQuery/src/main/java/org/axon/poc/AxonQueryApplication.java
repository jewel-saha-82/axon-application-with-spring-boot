package org.axon.poc;

import org.axon.poc.query.api.test.exceptions.EventErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxonQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonQueryApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler(
				"errorHandler",
				c -> new EventErrorHandler()
		);
	}
}
