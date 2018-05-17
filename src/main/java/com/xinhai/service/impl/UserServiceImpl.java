package com.xinhai.service.impl;

import java.util.ResourceBundle;

import com.xinhai.entity.User;
import com.xinhai.service.IUserService;
import com.xinhai.util.Result;

public class UserServiceImpl implements IUserService {
	private ResourceBundle rb = ResourceBundle.getBundle("daoApi");
	

	@Override
	public Result<User> login(String account) throws Exception {
		
		
		
		
		return null;
	}

	@Override
	public Result<Object> uptPassWord(String id, String oldPassword, String newPassword) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

}
