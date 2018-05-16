package com.xinhai.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class qiniu {
	// 构造一个带指定Zone对象的配置类
	static Configuration cfg = new Configuration(Zone.zone0());
	// ...其他参数参考类注释
	static UploadManager uploadManager = new UploadManager(cfg);
	// ...生成上传凭证，然后准备上传
	static String accessKey = "M5v-NC-OHhgyv0O-ORD2uEvwjsT1VuOiYbQUvery";
	static String secretKey = "vIsElpWC6G0fw3gX1shFm0f1L6GMPvTeTw5Wfz-O";
	// static String bucket = "x-xinhai-com";

	private static String bucket = "test";
	// 默认不指定key的情况下，以文件内容的hash值作为文件名
	static String key = null;

	public static void main(String[] args) {
		uploadImg();

	}

	public static void uploadImg() {
		// 如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "D:\\phn_news_type_test.jpg";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
			Response response = uploadManager.put(localFilePath, "phn_news_type_test", upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
	}
}
