package com.xinhai.service;

import java.util.List;
import java.util.Map;

import com.xinhai.entity.Article;
import com.xinhai.entity.ArticleType;

public interface INewsService {

	/*
	 * 新闻标题部分
	 */

	// 新增新闻类型
	public int insNewsType(ArticleType data) throws Exception;

	// 查询新闻类型信息
	public List<ArticleType> selNewsTypes() throws Exception;

	// 查询除了特定id以外的所有的 分类名和id
	public List<Map<String, Object>> selNewsTypeIdAndTypeName(String id) throws Exception;

	// 查询特定id的新闻类型
	public ArticleType selNewsTypeById(String id) throws Exception;

	// 修改新闻类型
	public int uptNewsType(ArticleType at) throws Exception;

	// 删除新闻类型
	public int delNewsType(String id) throws Exception;

	/*
	 * 新闻部分
	 */

	// 新增新闻
	public int insNews(Article data) throws Exception;

	// 查询信息
	public List<Article> selNews() throws Exception;

	// 查询特定新闻
	public Article selNewsById(String id) throws Exception;

	// 修改新闻
	public int uptNews(Article at) throws Exception;

	// 删除新闻
	public int delNews(String id) throws Exception;

}
