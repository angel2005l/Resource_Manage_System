package com.xinhai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.base.BaseResult;
import com.xinhai.entity.User;
import com.xinhai.service.IUserService;
import com.xinhai.util.HttpClientUtil;
import com.xinhai.util.MD5Util;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;


public class UserServiceImpl extends BaseResult implements IUserService {
	private ResourceBundle rb = ResourceBundle.getBundle("daoApi");

	@Override
	public Result<User> login(String account, String password) throws Exception {
		String url = rb.getString("user_login");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "selectManagerByName"));
		params.add(new BasicNameValuePair("user_name", account));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		if (jb.getIntValue("code") != 0) {
			return rtnFailResult(Result.ERROR_4100, "用户不存在/"+jb.getString("msg"));
		} else {
			User userObj = JSON.parseObject(jb.getString("data"), User.class);
			if (userObj.getPasswd().equals(getCiphertext(password))) {
				return rtnSuccessResult("登陆成功", userObj);
			} else {
				return rtnFailResult(Result.ERROR_4100, "密码不正确");
			}
		}
	}

	@Override
	public Result<Object> uptPassWord(String id, String newPassword) throws Exception {
		String url = rb.getString("user_sel_id_password");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "selectManagerById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		//System.err.println(resultJson);
		if (jb.getIntValue("code") != 0) {
			return rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
		} else {
			String newCipherPassword = getCiphertext(newPassword);
			User userObj = JSON.parseObject(jb.getString("data"), User.class);
			if (!userObj.getPasswd().equals(newCipherPassword)) {
				String uptUrl = rb.getString("user_password_upt");
				params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("parameter", "updateManagerPasswd"));
				params.add(new BasicNameValuePair("id", id));
				params.add(new BasicNameValuePair("passwd", newCipherPassword));
				resultJson = HttpClientUtil.getPostDefault(uptUrl, params);
				return rtnSuccessResult("密码修改成功");
			}
			return rtnFailResult(Result.ERROR_4100, "新密码与旧密码相同,修改密码失败");
		}
	}

	private String getCiphertext(String str) {
		return StrUtil.isBlank(str) ? "" : MD5Util.md5(MD5Util.md5(str));
	}

}
