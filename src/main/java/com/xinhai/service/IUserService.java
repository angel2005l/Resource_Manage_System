package com.xinhai.service;

import com.xinhai.entity.User;
import com.xinhai.util.Result;

public interface IUserService {

	public Result<User> login(String account) throws Exception;

	public Result<Object> uptPassWord(String id, String oldPassword, String newPassword) throws Exception;

}
