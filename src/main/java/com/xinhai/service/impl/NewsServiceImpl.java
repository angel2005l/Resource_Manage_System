package com.xinhai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.xinhai.entity.Article;
import com.xinhai.entity.ArticleType;
import com.xinhai.service.INewsService;
import com.xinhai.util.HttpClientUtil;

public class NewsServiceImpl implements INewsService {

	@Override
	public int insNewsType(ArticleType data) throws Exception {
		// 配置文件
		// 获得该httpUr的地址
		String url = "";
		// 请求数据

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));
		// params.add(new BasicNameValuePair(name, value));

		String resultJson = HttpClientUtil.getPostDefault(url, params);
		

		return 0;
	}

	@Override
	public List<ArticleType> selNewsTypes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selNewsTypeIdAndTypeName(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleType selNewsTypeById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int uptNewsType(ArticleType at) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delNewsType(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insNews(Article data) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Article> selNews() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article selNewsById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int uptNews(Article at) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delNews(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
