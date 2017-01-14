package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.HystrixService;

@RestController
public class HystrixController {

	@Autowired
	private HystrixService service;

	/**
	 * 调用依赖的服务
	 */
	@RequestMapping("/call1")
	public String callDependencyService1() {
		return service.callDependencyService1();
	}

	/**
	 * 调用依赖的服务
	 */
	@RequestMapping("/call2")
	public String callDependencyService2() {
		return service.callDependencyService2();
	}

	/**
	 * 调用依赖的服务
	 */
	@RequestMapping("/call3")
	public String callDependencyService3() {
		return service.callDependencyService3();
	}

}
