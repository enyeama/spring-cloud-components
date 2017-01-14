package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableZuulProxy
public class EurekaZuulApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaZuulApplication.class).web(true).run(args);
	}
}
