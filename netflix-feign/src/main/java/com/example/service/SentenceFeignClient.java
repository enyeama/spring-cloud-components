package com.example.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.hystrix.HystrixSentenceFeignClient;


@FeignClient(name = "EUREKA-CLIENT", fallback = HystrixSentenceFeignClient.class)
public interface SentenceFeignClient {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getSingleWord(@RequestParam("type") Integer type);

}
