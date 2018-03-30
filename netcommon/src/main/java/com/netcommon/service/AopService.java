package com.netcommon.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.netcommon.bean.User;
@Validated
public interface AopService {

	public Object execute();
	public Object getObject(String str) throws Exception;	
	public String validatorUser(@NotNull(message="user不为null") @Valid User user);
}
