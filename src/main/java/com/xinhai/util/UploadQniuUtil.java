package com.xinhai.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class UploadQniuUtil {

	// 获得机房区域

	// 获得UploadManger
	//
	// Configuration cfg = new Configuration(Zone.zone0());
	// //...其他参数参考类注释
	// UploadManager uploadManager = new UploadManager(cfg);
	// //...生成上传凭证，然后准备上传
	// String accessKey = "your access key";
	// String secretKey = "your secret key";
	// String bucket = "your bucket name";

	private String accessKey; // 七牛公钥
	private String secretKey; // 七牛秘钥
	private String bucket; // 存储目录
	private Zone zone;// 区域
	private Configuration qncfg; // 区域配置对象
	private UploadManager uploadManager;// 上传管理对象
	private Auth auth;// 授权对象

	// 初始化配置信息
	// 可以衍生为单例模式 （需要解决如何检测配置文件有无变化从而初始化配置信息）
	private UploadQniuUtil() {
		ResourceBundle rb = ResourceBundle.getBundle("qnUploadConfig"); // 获取七牛配置文件
		this.accessKey = rb.getString("accessKey").trim();
		this.secretKey = rb.getString("secretKey").trim();
		this.bucket = rb.getString("bucket");
		switch (rb.getString("zone")) {
		case "0":
			this.zone = Zone.zone0();
			break;
		case "1":
			this.zone = Zone.zone1();
			break;
		case "2":
			this.zone = Zone.zone2();
			break;
		case "NA0":
			this.zone = Zone.zoneNa0();
			break;
		default:
			// 默认华东
			this.zone = Zone.zone0();
			break;
		}
		this.qncfg = new Configuration(this.zone);
		this.uploadManager = new UploadManager(this.qncfg);
		this.auth = Auth.create(this.accessKey, this.secretKey);
	}

	/**
	 * 绝对根路径上传文件
	 * 
	 * @author 黄官易
	 * @date 2018年5月16日
	 * @version 1.0
	 * @param filePath
	 * @param isDefaultFileName
	 * @return
	 * @throws QiniuException
	 */
	public static DefaultPutRet uploadImg(String filePath, boolean isDefaultFileName) throws QiniuException {
		String key = isDefaultFileName ? null : new File(filePath.trim()).getName();// 根据需要是否获得文件名称
		UploadQniuUtil util = new UploadQniuUtil();// 初始化参数
		String upToken = util.auth.uploadToken(util.bucket);
		Response response = util.uploadManager.put(filePath, key, upToken);
		return new Gson().fromJson(response.bodyString(), DefaultPutRet.class);// 获得返回对象
	}

	/**
	 * 通过流进行文件上传
	 * 
	 * @author 黄官易
	 * @date 2018年5月16日
	 * @version 1.0
	 * @param inputStream
	 * @param fileName
	 * @return
	 * @throws QiniuException
	 */
	public static DefaultPutRet uploadImg(InputStream inputStream, String fileName) throws QiniuException {
		UploadQniuUtil util = new UploadQniuUtil();// 初始化参数
		String token = util.auth.uploadToken(util.bucket);
		Response response = util.uploadManager.put(inputStream, fileName, token, null, null);
		return new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
	}

}