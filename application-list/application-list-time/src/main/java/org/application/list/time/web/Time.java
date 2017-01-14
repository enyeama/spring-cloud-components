package org.application.list.time.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class Time {

	@Autowired
	TimeService timeService;

	@RequestMapping("/")
	public String time() {
		return timeService.time();
	}
	
	@RequestMapping("/info")
	public String timeInfo() {
		return timeService.info();
	}


	@Component
	public static class TimeService {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Random r = new Random(10);
		
		public String info() {
			return "Time Service";
		}

		@HystrixCommand(fallbackMethod = "fallback")
		public String time() {
			Integer i = r.nextInt(20);
			if (i > 3) {
				return format.format(new Date());
			} else {
				throw new RuntimeException("Some exception!");
			}
		}
		
		public String fallback() {
			return "Fallback 0000-00-00 00:00:00";
		}
		
	}

}
