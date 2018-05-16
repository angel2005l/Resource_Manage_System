package com.xinhai.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

public class IOUtil {

	/**
	 * 
	 * @Title: getFormField   
	 * @Description: 根据二进制流提交获得普通表单控件的值
	 * @param request
	 * @return
	 * @throws FileUploadException
	 * @throws UnsupportedEncodingException
	 * @author: MR.H
	 * @return: Map<String,String>
	 *
	 */
	public static Map<String, String> getFormField(HttpServletRequest request) throws FileUploadException,
			UnsupportedEncodingException {
		// 实例化文件上传组件对象
		FileItemFactory fif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(fif);

		Map<String, String> map = new HashMap<String, String>();
		// 是否满足二进制表单提交
		if (!ServletFileUpload.isMultipartContent(request)) {
			return map;
		}

		for (FileItem fi : sfu.parseRequest(request)) {
			if (!fi.isFormField()) {
				continue;
			}
			// String key = fi.getFieldName();
			// String value = new
			// String(fi.getString().getBytes("ISO-8859-1"),"UTF-8");
			map.put(fi.getFieldName(), new String(fi.getString().getBytes("ISO-8859-1"), "UTF-8"));
		}
		return map;
	}

	/**
	 * 
	 * @Title: getFormField2Bean   
	 * @Description: 根据二进制流表单提交获得普通表单控件的值并转换JavaBean
	 * @param request
	 * @param clazz
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileUploadException
	 * @author: MR.H
	 * @return: T
	 *
	 */
	public static <T> T getFormField2Bean(HttpServletRequest request, Class<T> clazz)
			throws UnsupportedEncodingException, FileUploadException {
		Map<String, String> resultMap = getFormField(request);
		if (null == resultMap || resultMap.isEmpty()) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(resultMap), clazz);
	}

	/**
	 * 
	 * @Title: getFilePaths   
	 * @Description: 根据二进制流表单提交获得文件上传控件的路径
	 * @param request
	 * @return
	 * @throws FileUploadException
	 * @author: MR.H
	 * @return: List<String>
	 *
	 */
	public static List<String> getFilePaths(HttpServletRequest request) throws FileUploadException {
		// 实例化文件上传组件对象
		FileItemFactory fif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(fif);
		List<String> list = new ArrayList<String>();
		if (!ServletFileUpload.isMultipartContent(request)) {
			return list;
		}
		for (FileItem fi : sfu.parseRequest(request)) {
			if (fi.isFormField()) {
				continue;
			}
			list.add(fi.getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getFileStreams   
	 * @Description: 根据二进制流表单提交获得文件上传控件的输入流对象
	 * @param request
	 * @return
	 * @throws FileNotFoundException
	 * @throws FileUploadException
	 * @author: MR.H
	 * @return: List<InputStream>
	 *
	 */
	public static List<InputStream> getFileStreams(HttpServletRequest request) throws FileNotFoundException,
			FileUploadException {
		// 实例化文件上传组件对象
		FileItemFactory fif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(fif);
		List<InputStream> list = new ArrayList<InputStream>();
		if (!ServletFileUpload.isMultipartContent(request)) {
			return list;
		}
		for (FileItem fi : sfu.parseRequest(request)) {
			if (fi.isFormField()) {
				continue;
			}
			// FileInputStream in = new FileInputStream(new File(fi.getName()));
			list.add(new FileInputStream(new File(fi.getName())));
		}
		return list;
	}
}
