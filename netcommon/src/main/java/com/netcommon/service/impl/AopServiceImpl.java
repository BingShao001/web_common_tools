package com.netcommon.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validator;

import com.netcommon.annotation.Ice;
import com.netcommon.bean.User;
import com.netcommon.entity.AopResult;
import com.netcommon.service.AopService;
@Service
@Validated
public class AopServiceImpl implements AopService {
	@Ice(value = 1)
	public Object execute() {
		System.out.println("I'm annotation !");
		Map<String, String> result = new HashMap<String, String>();
		String put = result.put("result", "我是结果集");
		return result;
	}

	public Object getObject(String str) throws Exception {
		// throw new Exception("我的异常");
		AopResult result = new AopResult();
		result.setAge(29);
		result.setName("bing");
		result.setIncome(35000.00);
		return result;
	}

	private static ValidatorFactory factory;
	private static Validator validator;

	public String validatorUser( User user) {
//		factory = Validation.buildDefaultValidatorFactory();
//		validator =  factory.getValidator();
		validator.validate(user);
		System.out.println("通过验证，没错！执行！");
		return null;
	}

}
