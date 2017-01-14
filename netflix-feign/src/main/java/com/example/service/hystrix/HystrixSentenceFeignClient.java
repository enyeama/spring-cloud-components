package com.example.service.hystrix;


import org.springframework.stereotype.Component;

import com.example.service.SentenceFeignClient;

@Component
public class HystrixSentenceFeignClient implements SentenceFeignClient {

	@Override
	public String getSingleWord(Integer type) {
		return "Invoke method in HystrixSentenceFeignClient";
	}

}
