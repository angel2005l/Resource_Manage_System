package com.xinhai.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xinhai.entity.Article;
import com.xinhai.entity.ArticleType;
import com.xinhai.service.INewsService;
import com.xinhai.service.impl.NewsServiceImpl;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;

public class NewsController extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(NewsController.class);
	private INewsService service = new NewsServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mod = req.getParameter("method");
		switch (mod) {
		// 新闻类型部分
		case "news_type_ins":
			addNewsType(req, resp);
			break;
		case "news_type_sel":
			selNewsType(req, resp);
			break;
		case "news_type_sel_id":
			selNewsTypeById(req, resp);
			break;
		case "news_type_sel_id_typeName":
			selNewsTypeIdAndTypeName(req, resp);
			break;
		case "news_type_upt":
			uptNewsType(req, resp);
			break;
		case "news_type_del":
			delNewsType(req, resp);
			break;
		case "news_ins":
			addNews(req, resp);
			break;
		case "news_sel":
			selNews(req, resp);
			break;
		case "news_sel_id":
			selNewsById(req, resp);
			break;
		case "news_upt":
			uptNews(req, resp);
			break;
		case "news_del":
			delNews(req, resp);
			break;
		default:
			returnData(JSON.toJSONString(new Result<>(Result.ERROR_6100, "无此接口信息")), resp);
			break;
		}
	}

	/*
	 * 新闻分类业务方法
	 * 
	 * 幸甚至哉，歌以咏志
	 */

	/**
	 * 查询全部的新闻分类
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selNewsType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Result<List<ArticleType>> selNewsTypes = service.selNewsTypes();
			request.setAttribute("result", selNewsTypes);
		} catch (Exception e) {
			log.error("查询新闻分类异常,异常原因：【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/newsType/index.jsp").forward(request, response);
	}

	/**
	 * 添加新闻分类
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addNewsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fid = request.getParameter("fid");
		String sort = request.getParameter("sort");
		String status = request.getParameter("status");
		String typeName = request.getParameter("type_name");
		String json = "";

		ArticleType data = new ArticleType();
		data.setFid(StrUtil.isBlank(fid) ? 0 : Integer.parseInt(fid));
		data.setSort(StrUtil.isBlank(sort) ? 0 : Integer.parseInt(sort));
		data.setStatus(StrUtil.isBlank(sort) ? 1 : Integer.parseInt(status));
		data.setType_name(typeName);
		try {
			Result<Object> insNewsType = service.insNewsType(data);
			json = JSON.toJSONString(insNewsType);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<Object>(Result.ERROR_6000, "添加新闻分类异常"));
			log.error("添加新闻分类异常,异常原因：【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 根据id获得特定的新闻分类
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selNewsTypeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Article> selNewsById = service.selNewsById(id);
			json = JSON.toJSONString(selNewsById);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "查询特定的新闻分类异常"));
			log.error("查询特定的新闻分类异常,异常原因：【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 查询除id以外的所有新闻信息键值对
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selNewsTypeIdAndTypeName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<List<Map<String, Object>>> selNewsTypeIdAndTypeName = service.selNewsTypeIdAndTypeName(id);
			json = JSON.toJSONString(selNewsTypeIdAndTypeName);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<List<Map<String, Object>>>(Result.ERROR_6000, "查询新闻分类键值对异常"));
			log.error("查询新闻分类键值对异常,异常原因【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 修改新闻分类信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void uptNewsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String fid = request.getParameter("fid");
		String sort = request.getParameter("sort");
		String status = request.getParameter("status");
		String typeName = request.getParameter("type_name");
		String json = "";

		ArticleType data = new ArticleType();
		try {
			data.setId(Integer.parseInt(id));
			data.setFid(Integer.parseInt(fid));
			data.setType_name(typeName);
			data.setStatus(Integer.parseInt(status));
			data.setSort(Integer.parseInt(sort));

			Result<Object> uptNewsType = service.uptNewsType(data);
			json = JSON.toJSONString(uptNewsType);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "修改新闻分类异常"));
			log.error("修改新闻分类异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 删除新闻分类
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delNewsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Object> delNewsType = service.delNewsType(id);
			json = JSON.toJSONString(delNewsType);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "删除新闻分类"));
			log.error(",异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/*
	 * 新闻内容业务部分
	 * 
	 * 幸甚至哉，歌以咏志
	 */
	/**
	 * 添加新闻
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String tid = request.getParameter("tid");
		String content = request.getParameter("content");
		String mainContent = request.getParameter("main_content");
		String status = request.getParameter("status");
		String json = "";

		Article data = new Article();
		data.setTitle(title);
		data.setTid(StrUtil.isBlank(tid) ? 0 : Integer.parseInt(tid));
		data.setMain_content(mainContent);
		data.setContent(content);
		data.setManager_id(session.getAttribute("userId").toString());
		data.setStatus(StrUtil.isBlank(status) ? 1 : Integer.parseInt(status));

		try {
			Result<Object> insNews = service.insNews(data);
			json = JSON.toJSONString(insNews);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加新闻异常"));
			log.error("添加新闻异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 查询新闻信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Result<List<Article>> selNews = service.selNews();
			request.setAttribute("data", selNews);
		} catch (Exception e) {
			log.error("查询新闻信息异常,:异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/news/index.jsp").forward(request, response);
	}

	/**
	 * 查询特定新闻信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selNewsById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Article> selNewsById = service.selNewsById(id);
			json = JSON.toJSONString(selNewsById);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "查询特定新闻信息异常"));
			log.error("查询特定新闻信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 修改新闻信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void uptNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title");
		String tid = request.getParameter("tid");
		String content = request.getParameter("content");
		String mainContent = request.getParameter("main_content");
		String status = request.getParameter("status");
		String json = "";

		Article data = new Article();
		data.setTitle(title);
		data.setTid(Integer.parseInt(tid));
		data.setMain_content(mainContent);
		data.setContent(content);
		data.setStatus(Integer.parseInt(status));
		try {
			Result<Object> uptNews = service.uptNews(data);
			json = JSON.toJSONString(uptNews);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "修改新闻信息异常"));
			log.error("修改新闻信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 删除新闻信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Object> delNews = service.delNews(id);
			json = JSON.toJSONString(delNews);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "删除新闻信息异常"));
			log.error("删除新闻信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 统一返回json格式对象
	 * 
	 * @author 黄官易
	 * @date 2018年5月15日
	 * @version 1.0
	 * @param json
	 * @param response
	 * @throws IOException
	 */
	private void returnData(String json, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(json);
	}

}
