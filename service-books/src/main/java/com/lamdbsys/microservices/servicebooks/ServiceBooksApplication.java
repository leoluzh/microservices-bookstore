package com.lamdbsys.microservices.servicebooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBooksApplication.class, args);
	}

}
