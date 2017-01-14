package org.application.list.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableTask
public class Application {

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new TimeCommandLineRunner();
	}

	public static class TimeCommandLineRunner implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			System.out.println("Now time is: " + new SimpleDateFormat().format(new Date()));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
