package com.example.service;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 依赖调用服务，通过Hystrix包装调用服务
 * 
 * @author I327119
 *
 */
@Component
public class CallDependencyService {

	private Random random = new Random(10);

	@HystrixCommand(fallbackMethod = "fallback")
	public String mockService1() {
		int randomInt = random.nextInt(10);
		if (randomInt < 8) { // 模拟调用失败情况
			throw new RuntimeException("Call dependency service fail.");
		} else {
			return "The number is:" + randomInt;
		}
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public String mockService2() {
		int randomInt = random.nextInt(10);
		if (randomInt < 5) { // 模拟调用失败情况
			throw new RuntimeException("Call dependency service fail.");
		} else {
			return "The number is:" + randomInt;
		}
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public String mockService3() {
		int randomInt = random.nextInt(10);
		if (randomInt < 3) { // 模拟调用失败情况
			throw new RuntimeException("Call dependency service fail.");
		} else {
			return "The number is:" + randomInt;
		}
	}

	public String fallback() {
		return "Some exception occur call fallback method.";
	}

}
