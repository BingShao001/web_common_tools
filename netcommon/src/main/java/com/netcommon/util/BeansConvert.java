package com.netcommon.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

public class BeansConvert {

	public static <T> T beansCopy(Object source, T target) {
		// source(被复制对象), target(目标对象)
		if (source != null && target != null) {
			BeanUtils.copyProperties(source, target);
		}
		return target;
	}

	public static void main(String[] args) {
		People people = new People();
		List<Long> list = new ArrayList<Long>();
		list.add(1233l);
		list.add(13213l);
		people.setAge(28);
		people.setName("bing");
		people.setList(list);
		List<People> p = new ArrayList();
		p.add(people);
		Student student = new Student();
		List<Student> s = new ArrayList();
		
		Student newStudent = beansCopy(people, student);
		System.out.println(JSON.toJSONString(newStudent));
	}

}

class People {
	private String name;
	private int age;
	private List<Long> list;
	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

class Student {
	private String name;
	private int age;
	private List<Long> list;
	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}