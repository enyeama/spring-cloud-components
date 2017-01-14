package com.example.service.hystrix;


import feign.hystrix.FallbackFactory;

public class HystrixClientFallbackFactory implements FallbackFactory<HystrixSentenceFeignClient> {

	@Override
	public HystrixSentenceFeignClient create(Throwable throwable) {
		return new HystrixSentenceFeignClient();
	}

}
