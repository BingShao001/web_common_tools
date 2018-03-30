package com.netcommon.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class User {
	@Min(0)
	private int age;
	@NotNull(message="name不为空")
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
