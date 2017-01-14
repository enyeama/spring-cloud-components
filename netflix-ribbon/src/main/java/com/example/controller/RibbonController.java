package com.example.controller;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

	Logger logger = Logger.getLogger(RibbonController.class);

	@Autowired
	DiscoveryClient discoveryClient;
	@Autowired
	LoadBalancerClient loadBalanceClient;
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/sentence")
	public String sentence() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getWord("NOUN"))//
				.append(getWord("VERB"))//
				.append(getWord("ADJECTIVE"))//
				.append(getWord("SUBJECT"));
		return buffer.toString();
	}

	public String getWord(String word) {
		ServiceInstance instance = loadBalanceClient.choose("EUREKA-CLIENT");
		if (instance != null) {
			Integer type = 0;
			if ("NOUN".equals(word)) {
				type = 1;
			} else if ("VERB".equals(word)) {
				type = 2;
			} else if ("ADJECTIVE".equals(word)) {
				type = 3;
			} else if ("SUBJECT".equals(word)) {
				type = 4;
			}
			URI uri = instance.getUri();  
			logger.info("The Uri Modified is: " + uri.toString());
			uri = URI.create("http://EUREKA-CLIENT/?type=" + type);
			return restTemplate.getForEntity(uri, String.class).getBody();
		}
		return null;
	}

}
