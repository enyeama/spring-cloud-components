package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 依赖服务包装
 * @author I327119
 *
 */
@Component
public class HystrixService {
	
    @Autowired
    private CallDependencyService dependencyService;
    
    public String callDependencyService1() {
        return dependencyService.mockService1();
    }
    
    public String callDependencyService2() {
        return dependencyService.mockService2();
    }
    
    public String callDependencyService3() {
        return dependencyService.mockService3();
    }

}
