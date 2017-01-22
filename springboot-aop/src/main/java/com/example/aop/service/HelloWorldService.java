
package com.example.aop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

	@Value("${name:World}")
	private String name;

	public String getMsg() {
		return "Get (" + this.name + ")";
	}

	public String saveMsg() {
		return "Save (" + this.name + ")";
	}

	public String updateMsg() {
		return "Update (" + this.name + ")";
	}

	public String deleteMsg() {
		return "Delete (" + this.name + ")";
	}

	public String throwsMsg() {
		throw new RuntimeException("Errors");
	}

}
