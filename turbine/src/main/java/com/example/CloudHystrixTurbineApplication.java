package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableTurbine
@EnableHystrixDashboard
public class CloudHystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudHystrixTurbineApplication.class, args);
	}
}
