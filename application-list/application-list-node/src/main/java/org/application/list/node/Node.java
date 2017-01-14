package org.application.list.node;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class Node {

	@Autowired
	private NodeService service;

	@RequestMapping("/")
	public String node() {
		return this.service.node();
	}
	
	@RequestMapping("/info")
	public String nodeInfo() {
		return this.service.info();
	}

	@Component
	public static class NodeService {

		Random r = new Random(10);

		public String info() {
			return "Node Service";
		}
		
		@HystrixCommand(fallbackMethod = "fallback")
		public String node() {
			Integer i = r.nextInt(20);
			if (i > 7) {
				return "The node is " + i;
			} else {
				throw new RuntimeException("Some exception!");
			}
		}

		public String fallback() {
			return "Fallback " + 0;
		}
	}
}
