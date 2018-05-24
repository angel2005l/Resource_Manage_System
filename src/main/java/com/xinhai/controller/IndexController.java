package com.xinhai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xinhai.entity.User;
import com.xinhai.service.IUserService;
import com.xinhai.service.impl.UserServiceImpl;
import com.xinhai.util.Result;

public class IndexController extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(IndexController.class);

	private IUserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mod = req.getParameter("method");
		switch (mod) {
		case "user_login":
			login(req, resp);
			break;
		case "user_password_upt":
			uptUserPassword(req, resp);
			break;
		default:
			returnData(JSON.toJSONString(new Result<>(Result.ERROR_6100, "无此接口信息")), resp);
			break;
		}
	}

	/**
	 * 登录
	 * 
	 * @author 黄官易
	 * @date 2018年5月18日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String account = request.getParameter("user_name");
		String password = request.getParameter("passwd");
		String json = "";
		try {
			Result<User> login = service.login(account, password);
			if (login.getCode() == 0) {
				User user = login.getData();
				HttpSession session = request.getSession();
				session.setAttribute("user_name", user.getUser_name());
				session.setAttribute("type", user.getType());
			}
			json = JSON.toJSONString(login);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "管理系统登录异常"));
			log.error("管理系统登录异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 更新用户密码
	 * 
	 * @author 黄官易
	 * @date 2018年5月18日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void uptUserPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String newPassword = request.getParameter("password");
		String json = "";
		try {
			Result<Object> uptPassWord = service.uptPassWord(id, newPassword);
			json = JSON.toJSONString(uptPassWord);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "更新用户密码异常"));
			log.error("更新用户密码异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	// public void addPeople(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// req.setAttribute("maxPeople", "当前最大排队数" + (++maxWaitNum));
	// req.getRequestDispatcher("showMax.jsp").forward(req, resp);
	// }
	//
	// public void nextPeople(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// String msg = "";
	// String winNo = req.getParameter("winNo");
	// if (maxWaitNum < (maxGoNum + 1)) {
	// msg = "等待中";
	// } else {
	// ++maxGoNum;
	// msg = "请" + maxGoNum + "到" + winNo + "窗口排队";
	// }
	// req.setAttribute("winNo", winNo);
	// req.setAttribute("winPeo", msg);
	// req.getRequestDispatcher("showGo.jsp").forward(req, resp);
	// }

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
