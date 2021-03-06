package com.xinhai.service;

import java.util.List;
import java.util.Map;

import com.xinhai.entity.Article;
import com.xinhai.entity.ArticleImg;
import com.xinhai.entity.ArticleType;
import com.xinhai.util.Page;
import com.xinhai.util.Result;

public interface INewsService {

	/*
	 * 新闻标题部分
	 */

	// 新增新闻类型
	public Result<Object> insNewsType(ArticleType data) throws Exception;

	// 查询新闻类型信息
	public Result<Page<ArticleType>> selNewsTypes(String showCount,String page,ArticleType data) throws Exception;

	// 查询除了特定id以外的所有的 分类名和id
	public Result<List<Map<String, Object>>> selNewsTypeIdAndTypeName(String id) throws Exception;

	// 查询特定id的新闻类型
	public Result<ArticleType> selNewsTypeById(String id) throws Exception;

	// 修改新闻类型
	public Result<Object> uptNewsType(ArticleType data) throws Exception;

	// 删除新闻类型
	public Result<Object> delNewsType(String id) throws Exception;

	/*
	 * 新闻部分
	 */

	// 新增新闻
	public Result<Object> insNews(Article data) throws Exception;

	// 查询信息
	public Result<Page<Article>> selNews(String showCount,String page,Article data) throws Exception;

	// 查询特定新闻
	public Result<Article> selNewsById(String id) throws Exception;

	// 修改新闻
	public Result<Object> uptNews(Article data) throws Exception;

	// 删除新闻
	public Result<Object> delNews(String id) throws Exception;

	public Result<List<Map<String, Object>>> selNewsIdAndName() throws Exception;

	public Result<Object> insNewsImg(ArticleImg data) throws Exception;

	public Result<Page<ArticleImg>> selNewsImg(String showCount,String page,ArticleImg data) throws Exception;

	public Result<ArticleImg> selNewsImgById(String id) throws Exception;

	public Result<Object> uptNewsImg(ArticleImg data) throws Exception;

	public Result<Object> delNewsImg(String id) throws Exception;
}