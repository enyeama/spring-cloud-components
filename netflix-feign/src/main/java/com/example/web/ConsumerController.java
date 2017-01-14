package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.SentenceFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class ConsumerController {

	@Autowired
	SentenceFeignClient sentenceFeignClient;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "callRecommendFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100") })
	public String sentence() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(sentenceFeignClient.getSingleWord(1))//
				.append(sentenceFeignClient.getSingleWord(2))//
				.append(sentenceFeignClient.getSingleWord(3))//
				.append(sentenceFeignClient.getSingleWord(4));
		return buffer.toString();
	}
	
    public String callRecommendFallback() {
        return "Hystrix not found";
    }
    

}
