package com.xinhai.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xinhai.entity.Product;
import com.xinhai.entity.ProductType;
import com.xinhai.service.IProductService;
import com.xinhai.service.impl.ProductServiceImpl;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;

public class ProductController extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	private IProductService service = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mod = req.getParameter("method");
		switch (mod) {
		case "product_type_ins":
			insProductType(req, resp);
			break;
		case "product_type_sel":
			selProductType(req, resp);
			break;
		case "product_type_sel_id":
			selProductTypeById(req, resp);
			break;
		case "product_type_upt":
			uptProductType(req, resp);
			break;
		case "product_type_del":
			delProductType(req, resp);
			break;
		case "product_type_id_typeName":
			selProductTypeIdAndTypeName(req, resp);
			break;
		case "product_ins":
			break;
		case "product_sel":
			break;
		case "product_sel_id":
			break;
		case "product_upt":
			break;
		case "product_del":
			break;
		case "product_img_ins":
			break;
		case "product_img_sel":
			break;
		case "product_img_sel_pId":
			break;
		case "product_img_id":
			break;
		case "product_img_upt":
			break;
		case "product_img_del":
			break;
		default:
			returnData(JSON.toJSONString(new Result<>(Result.ERROR_6100, "无此接口信息")), resp);
			break;
		}

	}

	/*
	 * 产品分类管理
	 * 
	 * 幸甚至哉，歌以咏志
	 */
	/**
	 * 添加产品分类
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void insProductType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fid = request.getParameter("fid");
		String typeName = request.getParameter("type_name");
		String typeIco = request.getParameter("type_ico");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String json = "";

		ProductType data = new ProductType();

		data.setFid(StrUtil.isBlank(fid) ? 0 : Integer.parseInt(fid));
		data.setType_name(typeName);
		data.setType_ico(typeIco);
		data.setStatus(StrUtil.isBlank(status) ? 1 : Integer.parseInt(status));
		data.setSort(StrUtil.isBlank(sort) ? 0 : Integer.parseInt(sort));

		try {
			Result<Object> insProductType = service.insProductType(data);
			json = JSON.toJSONString(insProductType);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加产品分类异常"));
			log.error("添加产品分类异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 查询产品分类信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selProductType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Result<List<ProductType>> selProductType = service.selProductType();
			request.setAttribute("data", selProductType);
		} catch (Exception e) {
			log.error("查询产品分类信息异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/productType/index.jsp").forward(request, response);
	}

	/**
	 * 查询特定的产品信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void selProductTypeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Result<ProductType> selProductTypeById = StrUtil.isBlank(id)
					? new Result<ProductType>(Result.ERROR_4000, "参数错误")
					: service.selProductTypeById(id);
			request.setAttribute("data", selProductTypeById);

		} catch (Exception e) {
			log.error("查询特定的产品信息异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/productType/editLayout.jsp").forward(request, response);
	}

	/**
	 * 修改产品分类信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void uptProductType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String fid = request.getParameter("fid");
		String typeName = request.getParameter("type_name");
		String typeIco = request.getParameter("type_ico");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String json = "";
		try {
			ProductType data = new ProductType();
			data.setId(Integer.parseInt(id));
			data.setFid(Integer.parseInt(fid));
			data.setType_name(typeName);
			data.setType_ico(typeIco);
			data.setStatus(Integer.parseInt(status));
			data.setSort(Integer.parseInt(sort));
			Result<Object> uptProductType = service.uptProductType(data);
			json = JSON.toJSONString(uptProductType);
		} catch (NumberFormatException e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_4000, "参数错误"));
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "修改产品分类信息异常"));
			log.error("修改产品分类信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 删除产品分类
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delProductType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Object> result = StrUtil.isBlank(id) ? new Result<Object>(Result.ERROR_4000, "参数错误")
					: service.delProductType(id);
			json = JSON.toJSONString(result);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "删除产品分类信息异常"));
			log.error("删除产品分类信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 查询产品分类键值对
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selProductTypeIdAndTypeName(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<List<Map<String, Object>>> selProductTypeIdAndTypeName = service.selProductTypeIdAndTypeName(id);
			json = JSON.toJSONString(selProductTypeIdAndTypeName);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "查询产品分类键值对异常"));
			log.error("查询产品分类键值对异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	private void insProduct(HttpServletRequest request, HttpServletResponse response) {
		String tid = request.getParameter("tid");
		String productName = request.getParameter("product_name");
		String originalPrice = request.getParameter("original_price");
		String price = request.getParameter("price");
		String itemNo = request.getParameter("item_no");
		String info = request.getParameter("info");
		String remark = request.getParameter("remark");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		
		Product data = new Product()
		
		
		
		
		
		
		service.insProduct(data);

	}

	private void selProduct(HttpServletRequest request, HttpServletResponse response) {

	}

	private void selProductById(HttpServletRequest request, HttpServletResponse response) {

	}

	private void uptProduct(HttpServletRequest request, HttpServletResponse response) {

	}

	private void delProduct(HttpServletRequest request, HttpServletResponse response) {

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
