package com.xinhai.service;

import com.xinhai.entity.User;
import com.xinhai.util.Result;

public interface IUserService {

	public Result<User> login(String account, String password) throws Exception;

	public Result<Object> uptPassWord(String id, String newPassword) throws Exception;

}
