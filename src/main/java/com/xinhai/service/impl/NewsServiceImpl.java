package com.xinhai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.entity.Article;
import com.xinhai.entity.ArticleType;
import com.xinhai.service.INewsService;
import com.xinhai.util.DateUtil;
import com.xinhai.util.HttpClientUtil;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;

import base.BaseResult;

public class NewsServiceImpl extends BaseResult implements INewsService {
	private ResourceBundle rb = ResourceBundle.getBundle("daoApi.properties");

	@Override
	public Result<Object> insNewsType(ArticleType data) throws Exception {
		// 获得该httpUr的地址
		String url = rb.getString("news_type_ins");
		// 传参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加新闻分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加新闻失败" : jb.getString("msg"));
	}

	@Override
	public Result<List<ArticleType>> selNewsTypes() throws Exception {
		String url = rb.getString("news_type_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", value));

		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), ArticleType.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<List<Map<String, Object>>> selNewsTypeIdAndTypeName(String id) throws Exception {
		String url = rb.getString("news_type_sel_id_typeName");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		if (0 == jb.getIntValue("code")) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			for (Object object : JSON.parseArray(jb.getString("data"))) {
				Map<String, Object> ret = (Map<String, Object>) object;
				list.add(ret);
			}
			return rtnSuccessResult("", list);
		}
		return rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<ArticleType> selNewsTypeById(String id) throws Exception {
		if (StrUtil.isBlank(id) || StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_type_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), ArticleType.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptNewsType(ArticleType data) throws Exception {
		String url = rb.getString("news_type_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("更新新闻类型成功")
				: rtnFailResult(jb.getIntValue("code"), "更新新闻类型失败,失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> delNewsType(String id) throws Exception {
		if (StrUtil.isBlank(id) || StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_type_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("删除新闻类型成功")
				: rtnFailResult(jb.getIntValue("code"), "删除新闻类型失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> insNews(Article data) throws Exception {
		String url = rb.getString("news_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("tid", data.getTid() + ""));
		params.add(new BasicNameValuePair("content", data.getContent()));
		params.add(new BasicNameValuePair("main_content", data.getMain_content()));
		params.add(new BasicNameValuePair("manager_id", data.getManager_id()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("add_timer", DateUtil.curDateYMDHMS()));

		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");

		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加新闻成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加新闻失败" : jb.getString("msg"));
	}

	@Override
	public Result<List<Article>> selNews() throws Exception {
		String url = rb.getString("news_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", value));

		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), Article.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Article> selNewsById(String id) throws Exception {
		if (StrUtil.isBlank(id) || StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), Article.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptNews(Article data) throws Exception {
		String url = rb.getString("news_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("tid", data.getTid() + ""));
		params.add(new BasicNameValuePair("content", data.getContent()));
		params.add(new BasicNameValuePair("main_content", data.getMain_content()));
//		params.add(new BasicNameValuePair("manager_id", data.getManager_id()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("修改新闻成功")
				: rtnFailResult(Result.ERROR_401, "修改新闻失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> delNews(String id) throws Exception {
		if (StrUtil.isBlank(id) || StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("删除新闻成功")
				: rtnFailResult(jb.getIntValue("code"), "删除新闻失败，失败原因【" + jb.getString("msg") + "】");
	}

}
