package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.netflix.discovery.DiscoveryManager;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class EurekaFeignApplication {
	private static final Logger LOG = Logger.getLogger(EurekaFeignApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EurekaFeignApplication.class, args);
		LOG.info("Register ShutdownHook");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOG.info("Shutting down feign service, unregister from Eureka!");
				DiscoveryManager.getInstance().shutdownComponent();
			}
		});
	}
}
