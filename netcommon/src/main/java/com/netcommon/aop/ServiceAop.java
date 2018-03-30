package com.netcommon.aop;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.netcommon.annotation.Bing;

//@Component
//@Aspect
public class ServiceAop {

	@Pointcut("execution(* com.netcommon.service.*.*(..))")
	private void pointCutMethod() {
		System.out.println(":: pointCutMethod");
	}

	@Around("pointCutMethod()")
	public Object doBefore(ProceedingJoinPoint jPoint) throws Throwable {
		Object target = jPoint.getTarget();
		Class<?> clazz = target.getClass();
		System.out.println("aop : "+clazz.getName());
		Boolean hasAnnotation = clazz.isAnnotationPresent(Bing.class);
		Object[] args = jPoint.getArgs();
		Object result = jPoint.proceed(args);
		if (hasAnnotation) {
			String json = JSON.toJSONString(result);
			return json;
		}
		return result;
	}
    @AfterThrowing(value = "pointCutMethod()", throwing = "e")  
    public void afterThrowing(JoinPoint joinPoint, Throwable e) throws ClassNotFoundException {  
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
    }

}
