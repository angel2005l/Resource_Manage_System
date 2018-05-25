package com.xinhai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.base.BaseResult;
import com.xinhai.entity.Article;
import com.xinhai.entity.ArticleImg;
import com.xinhai.entity.ArticleType;
import com.xinhai.service.INewsService;
import com.xinhai.util.DateUtil;
import com.xinhai.util.HttpClientUtil;
import com.xinhai.util.Page;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;

public class NewsServiceImpl extends BaseResult implements INewsService {
	private ResourceBundle rb = ResourceBundle.getBundle("daoApi");

	@Override
	public Result<Object> insNewsType(ArticleType data) throws Exception {
		// 获得该httpUr的地址
		String url = rb.getString("news_type_ins");
		// 传参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "insertArticleType"));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println("json报文：" + resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加新闻分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加新闻分类失败" : jb.getString("msg"));
	}

	@Override
	public Result<Page<ArticleType>> selNewsTypes(String showCount,String page,ArticleType data) throws Exception {
		String url = rb.getString("news_type_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", value));
		params.add(new BasicNameValuePair("parameter", "getArticleTypeByName"));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("page", page));
		params.add(new BasicNameValuePair("pageSize", showCount));
		//System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println(resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		// data 有隔层
		JSONObject dataJb = JSON.parseObject(jb.getString("data"));
		return rtnPageWithCount(jb.getIntValue("code"), jb.getString("msg"), Integer.parseInt(showCount),page,
				dataJb.getIntValue("totalResult"), JSON.parseArray(dataJb.getString("data"), ArticleType.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<List<Map<String, Object>>> selNewsTypeIdAndTypeName(String id) throws Exception {
		String url = rb.getString("news_type_sel_id_typeName");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "selectArticleTypeExceptId"));
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
		if (StrUtil.isBlank(id) || !StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_type_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "getArticleTypeById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println(resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), ArticleType.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptNewsType(ArticleType data) throws Exception {
		String url = rb.getString("news_type_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "updateArticleType"));
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println(resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("更新新闻分类成功")
				: rtnFailResult(jb.getIntValue("code"), "更新新闻分类失败,失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> delNewsType(String id) throws Exception {
		if (StrUtil.isBlank(id) || !StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_type_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "deleteArticleType"));
		params.add(new BasicNameValuePair("id", id));
		//System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("删除新闻分类成功")
				: rtnFailResult(jb.getIntValue("code"), "删除新闻分类失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> insNews(Article data) throws Exception {
		String url = rb.getString("news_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "insertArticle"));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("tid", data.getTid() + ""));
		params.add(new BasicNameValuePair("content", data.getContent()));
		params.add(new BasicNameValuePair("main_content", data.getMain_content()));
		params.add(new BasicNameValuePair("type", data.getType()+""));
		params.add(new BasicNameValuePair("httpurl", data.getHttpurl()));
		params.add(new BasicNameValuePair("manager_id", data.getManager_id()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("add_time", DateUtil.curDateYMDHMS()));
		System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");

		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加新闻成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加新闻失败" : jb.getString("msg"));
	}

	@Override
	public Result<Page<Article>> selNews(String showCount, String page, Article data) throws Exception {
		String url = rb.getString("news_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", value));
		params.add(new BasicNameValuePair("parameter", "selectArticleByTitle"));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("page", page));
		params.add(new BasicNameValuePair("pageSize", showCount));
		//System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		// data 有隔层
		JSONObject dataJb = JSON.parseObject(jb.getString("data"));
		return rtnPageWithCount(jb.getIntValue("code"), jb.getString("msg"), Integer.parseInt(showCount),page,
				dataJb.getIntValue("totalResult"), JSON.parseArray(dataJb.getString("data"), Article.class));
	}

	@Override
	public Result<Article> selNewsById(String id) throws Exception {
		if (StrUtil.isBlank(id) || !StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "selectArticleById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println(resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), Article.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptNews(Article data) throws Exception {
		String url = rb.getString("news_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "updateArticleById"));
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("tid", data.getTid() + ""));
		params.add(new BasicNameValuePair("content", data.getContent()));
		params.add(new BasicNameValuePair("main_content", data.getMain_content()));
		params.add(new BasicNameValuePair("type", data.getType()+""));
		params.add(new BasicNameValuePair("httpurl", data.getHttpurl()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("修改新闻成功")
				: rtnFailResult(Result.ERROR_401, "修改新闻失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> delNews(String id) throws Exception {
		if (StrUtil.isBlank(id) || !StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "deleteArticleById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("删除新闻成功")
				: rtnFailResult(jb.getIntValue("code"), "删除新闻失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<List<Map<String, Object>>> selNewsIdAndName() throws Exception {
		String url = rb.getString("news_sel_id_name");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "selectArticleCodeValue"));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println(resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		if (0 == jb.getIntValue("code")) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (Object object : JSON.parseArray(jb.getString("data"))) {
				@SuppressWarnings("unchecked")
				Map<String, Object> ret = (Map<String, Object>) object;
				list.add(ret);
			}
			return rtnSuccessResult("", list);
		}
		return rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> insNewsImg(ArticleImg data) throws Exception {
		String url = rb.getString("news_img_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "insertArticleImg"));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("aid", data.getAid() + ""));
		params.add(new BasicNameValuePair("url", data.getUrl()));
		params.add(new BasicNameValuePair("add_time", DateUtil.curDateYMDHMS()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		//System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("新增新闻图片成功")
				: rtnFailResult(Result.ERROR_401, "新增新闻图片失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Page<ArticleImg>> selNewsImg(String showCount,String page,ArticleImg data) throws Exception {
		String url = rb.getString("news_img_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", value));
		params.add(new BasicNameValuePair("parameter", "selectArticleImg"));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("page", page));
		params.add(new BasicNameValuePair("pageSize", showCount));
		//System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		//System.err.println(resultJson);
		// data 有隔层
		JSONObject dataJb = JSON.parseObject(jb.getString("data"));
		return rtnPageWithCount(jb.getIntValue("code"), jb.getString("msg"), Integer.parseInt(showCount),page,
				dataJb.getIntValue("totalResult"), JSON.parseArray(dataJb.getString("data"), ArticleImg.class));
	}

	@Override
	public Result<ArticleImg> selNewsImgById(String id) throws Exception {
		if (StrUtil.isBlank(id) || !StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_img_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "getArticleByImgById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), ArticleImg.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptNewsImg(ArticleImg data) throws Exception {
		String url = rb.getString("news_img_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "updateArticleByImg"));
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("aid", data.getAid() + ""));
		// params.add(new BasicNameValuePair("url", data.getUrl()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		//System.err.println(params);
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		//System.err.println(resultJson);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("修改新闻图片成功")
				: rtnFailResult(Result.ERROR_401, "修改新闻图片失败，失败原因【" + jb.getString("msg") + "】");
	}

	@Override
	public Result<Object> delNewsImg(String id) throws Exception {
		if (StrUtil.isBlank(id) || !StrUtil.isPositiveInteger(id)) {
			return rtnFailResult(Result.ERROR_401, "参数为空");
		}
		String url = rb.getString("news_img_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "deleteArticleByImg"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("删除新闻图片成功")
				: rtnFailResult(jb.getIntValue("code"), "删除新闻图片失败，失败原因【" + jb.getString("msg") + "】");
	}

}
