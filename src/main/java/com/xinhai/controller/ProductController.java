package com.xinhai.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
			insProduct(req, resp);
			break;
		case "product_sel":
			selProduct(req, resp);
			break;
		case "product_sel_id":
			selProductById(req, resp);
			break;
		case "product_upt":
			uptProduct(req, resp);
			break;
		case "product_del":
			delProduct(req, resp);
			break;
		case "product_id_productName":
			selProductIdAndProductName(req, resp);
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

		try {
			ProductType data = new ProductType();
			data.setFid(StrUtil.isBlank(fid) ? 0 : Integer.parseInt(fid));
			data.setType_name(typeName);
			data.setType_ico(typeIco);
			data.setStatus(StrUtil.isBlank(status) ? 1 : Integer.parseInt(status));
			data.setSort(StrUtil.isBlank(sort) ? 0 : Integer.parseInt(sort));
			Result<Object> insProductType = service.insProductType(data);
			json = JSON.toJSONString(insProductType);
		} catch (NumberFormatException e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_4000, "参数错误"));
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
			request.setAttribute("data", new Result<>(Result.ERROR_6000, "查询特定的产品信息异常"));
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

	/**
	 * 添加产品信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void insProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		String productName = request.getParameter("product_name");
		String originalPrice = request.getParameter("original_price");
		String price = request.getParameter("price");
		String itemNo = request.getParameter("item_no");
		String info = request.getParameter("info");
		String remark = request.getParameter("remark");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String json = "";
		try {
			Product data = new Product();
			data.setTid(StrUtil.isBlank(tid) ? 0 : Integer.parseInt(tid));
			data.setProduct_name(productName);
			data.setOriginal_price(StrUtil.isBlank(originalPrice) ? BigDecimal.ZERO : new BigDecimal(originalPrice));
			data.setPrice(StrUtil.isBlank(price) ? BigDecimal.ZERO : new BigDecimal(price));
			data.setItem_no(itemNo);
			data.setInfo(info);
			data.setRemark(remark);
			data.setStatus(StrUtil.isBlank(status) ? 0 : Integer.parseInt(status));
			data.setSort(StrUtil.isBlank(sort) ? 0 : Integer.parseInt(sort));

			Result<Object> insProduct = service.insProduct(data);
			json = JSON.toJSONString(insProduct);
			// } catch (QiniuException e) {
			// Response errorReq = e.response;
			// errorReq.getInfo();
			// json = JSON.toJSONString(new Result<>(Result.ERROR_6000,
			// "产品图片上传失败"));
			// log.error("添加产品信息（七牛图片上传）失败,失败原因:【" + errorReq.getInfo() + "】");

		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加产品信息异常"));
			log.error("添加产品信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 查询全部的产品信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void selProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Result<List<Product>> selProduct = service.selProduct();
			request.setAttribute("data", selProduct);
		} catch (Exception e) {
			log.error("查询全部的产品信息异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/product/index.jsp").forward(request, response);
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
	private void selProductById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Result<Product> selProductById = StrUtil.isBlank(id) ? new Result<Product>(Result.ERROR_4000, "参数错误")
					: service.selProductById(id);
			request.setAttribute("data", selProductById);
		} catch (Exception e) {
			request.setAttribute("data", new Result<>(Result.ERROR_6000, "查询特定的产品信息异常"));
			log.error("查询特定的产品信息异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/product/edit.jsp").forward(request, response);
	}

	/**
	 * 修改产品信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void uptProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String tid = request.getParameter("tid");
		String productName = request.getParameter("product_name");
		String originalPrice = request.getParameter("original_price");
		String price = request.getParameter("price");
		String itemNo = request.getParameter("item_no");
		String info = request.getParameter("info");
		String remark = request.getParameter("remark");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String json = "";

		try {
			Product data = new Product();
			data.setId(Integer.parseInt(id));
			data.setTid(Integer.parseInt(tid));
			data.setProduct_name(productName);
			data.setOriginal_price(new BigDecimal(originalPrice));
			data.setPrice(new BigDecimal(price));
			data.setItem_no(itemNo);
			data.setInfo(info);
			data.setRemark(remark);
			data.setStatus(Integer.parseInt(status));
			data.setSort(Integer.parseInt(sort));
			Result<Object> uptProduct = service.uptProduct(data);
			json = JSON.toJSONString(uptProduct);
		} catch (NumberFormatException e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_4000, "参数错误"));
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "修改产品信息异常"));
			log.error("修改产品信息异常,异常信息:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 删除产品信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月17日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Object> delProduct = StrUtil.isBlank(id) ? new Result<>(Result.ERROR_4000, "参数错误")
					: service.delProduct(id);
			json = JSON.toJSONString(delProduct);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "删除产品信息异常"));
			log.error("删除产品信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 
	 * @Title: selProductIdAndProductName
	 * @Description: 查询所有产品的id_productName 键值对
	 * @param request
	 * @param response
	 * @author: MR.H
	 * @return: void
	 * @throws IOException 
	 *
	 */
	private void selProductIdAndProductName(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = "";
		try {
			Result<List<Map<String, Object>>> selProductIdAndProductNameNoBound = service
					.selProductIdAndProductName();
			json = JSON.toJSONString(selProductIdAndProductNameNoBound);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "查询所有产品的键值对异常"));
			log.error("查询所有产品的键值对异常,异常原因:【" + e.toString() + "】");
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
