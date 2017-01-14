package org.application.list.scheduler.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class TimeScheduler {

	private static Logger logger = Logger.getLogger(TimeScheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	Random r = new Random();

	@Scheduled(fixedRate = 5000)
	@HystrixCommand(fallbackMethod = "callback")
	public String reportCurrentTime() {
		int i = r.nextInt(200);
		if (i > 150) {
			String time = dateFormat.format(new Date());
			logger.info(time);
			return "当前时间：" + time;
		} else {
			throw new RuntimeException("Error then fallback");
		}
	}

	public String callback() {
		logger.info("fallback to callback");
		return "00:00:00";
	}

}
