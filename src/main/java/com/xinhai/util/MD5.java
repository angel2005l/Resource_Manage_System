package com.xinhai.util;

import java.security.MessageDigest;
/**
 * 
 * @Title: MD5.java
 * @Description: MD5加密工具类
 * @Copyright: Copyright (c) 2018
 * @Company: 新海集团
 * @author 胡冬冬
 * @date 2018年4月17日
 * @version 1.0.0
 */
public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(md5("31119@qq.com"+"123456"));
		System.out.println(md5("123456"));
	}
}
