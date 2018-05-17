package com.xinhai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mod = req.getParameter("method");
		switch (mod) {
		default:
			break;
		}
	}

	// 登陆
	private void login() {
		// 跳转主页
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

}
