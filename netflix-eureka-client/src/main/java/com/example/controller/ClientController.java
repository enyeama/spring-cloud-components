package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
public class ClientController {

	@Value("${words.noun}")
//	@Value("${from}")
	private String nounWords;

	@Value("${words.verb}")
//	@Value("${from}")
	private String verbWords;

	@Value("${words.adjective}")
//	@Value("${from}")
	private String adjectiveWords;

	@Value("${words.subject}")
//	@Value("${from}")
	private String subjectWords;

	@Value("${author.name}")
//	@Value("${from}")
	private String name;

	@Value("${author.city}")
//	@Value("${from}")
	private String city;

	@Value("${author.age}")
//	@Value("${from}")
	private Integer age;

	@RequestMapping("/author")
	public String getAuthor() {
		return "Name: " + name + ", age: " + age + ", city: " + city;
	}
	
	@RequestMapping("/name")
	public String name() {
		return "Name: " + name;
	}
	
	@RequestMapping("/city")
	public String city() {
		return "City: " + city;
	}
	
	@RequestMapping("/age")
	public String age() {
		return "Age: " + age;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "fallback")
	public String getSingleWord(@RequestParam("type") Integer type) {
		String word = "";
		switch (type) {
		case 1:
			word = getRandomWord(nounWords);
			break;
		case 2:
			word = getRandomWord(verbWords);
			break;
		case 3:
			word = getRandomWord(adjectiveWords);
			break;
		case 4:
			word = getRandomWord(subjectWords);
			break;
		default:
			word = "Param is legal only within 1 to 4, 1 => noun, 2 => verb, 3 => adjective, 4 => subject";
			break;
		}
		return word;
	}
	
	public String fallback(Integer type) {
		return "fallback";
	}

	public String getRandomWord(String words) {
		String[] wordArray = words.split(",");
		int i = (int) Math.round(Math.random() * (wordArray.length - 1));
		return wordArray[i];
	}

	public String getNounWords() {
		return nounWords;
	}

	public void setNounWords(String nounWords) {
		this.nounWords = nounWords;
	}

	public String getVerbWords() {
		return verbWords;
	}

	public void setVerbWords(String verbWords) {
		this.verbWords = verbWords;
	}

	public String getAdjectiveWords() {
		return adjectiveWords;
	}

	public void setAdjectiveWords(String adjectiveWords) {
		this.adjectiveWords = adjectiveWords;
	}

	public String getSubjectWords() {
		return subjectWords;
	}

	public void setSubjectWords(String subjectWords) {
		this.subjectWords = subjectWords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
