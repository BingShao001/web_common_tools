package com.netcommon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import com.alibaba.fastjson.JSON;
import com.netcommon.bean.OrderBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcommon.bean.User;
import com.netcommon.service.AopService;

@Controller
public class HelloController {
	private Logger log = Logger.getLogger(getClass());
	@Autowired
	private AopService aopService;


	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderBean bean = new OrderBean();
		bean.setOrderId(213213);
		bean.setOrderName("test");
		bean.setPrice(111.11);
		String data = JSON.toJSONString(bean);
		System.out.println(data);
		request.setAttribute("data", data);
		return "index";
	}
	@RequestMapping("/hello")
	@ResponseBody
	public Map hello() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("say", "hello!");
		// String json = (String) aopService.execute();
		// System.out.println(json);
		Object result = aopService.getObject("ice");
		System.out.println(result);
		log.info(result);
		return map;
	}

	@RequestMapping("/hi")
	public Map hi() {
		Map<String, String> map = null;
		try {
			map = new HashMap<String, String>();
			map.put("say", "hello!");
			User user = null;
			//user.setName(null);
			aopService.validatorUser(user);
		} catch (ValidationException e) {
			if (e instanceof ConstraintViolationException) {
				StringBuffer sb = new StringBuffer();
				final Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e)
						.getConstraintViolations();
				for (ConstraintViolation constraint : constraintViolations) {
					sb.append(constraint.getMessage());
				}
				System.out.println(sb.toString() + "," + e);
			} else {
				System.out.println(e.getMessage() + "," + e);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "," + e);
		}

		return map;
	}
}
