package org.application.list.swagger.entity;

public class User {

	private Long id;
	private String username;
	private Integer age;
	private String phone;

	public User() {
	}

	public User(Long id, String username, Integer age, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + ", phone=" + phone + "]";
	}

}
