package com.xinhai.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.xinhai.base.Constant;
import com.xinhai.entity.Product;
import com.xinhai.entity.ProductImg;
import com.xinhai.entity.ProductType;
import com.xinhai.service.IProductService;
import com.xinhai.service.impl.ProductServiceImpl;
import com.xinhai.util.DateUtil;
import com.xinhai.util.IOUtil;
import com.xinhai.util.Page;
import com.xinhai.util.QniuUtil;
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
			// 2
			insProductType(req, resp);
			break;
		case "product_type_sel":
			// 1
			selProductType(req, resp);
			break;
		case "product_type_sel_id":
			// 2
			selProductTypeById(req, resp);
			break;
		case "product_type_upt":
			// 2
			uptProductType(req, resp);
			break;
		case "product_type_del":
			// 2
			delProductType(req, resp);
			break;
		case "product_type_id_typeName":
			// 2
			selProductTypeIdAndTypeName(req, resp);
			break;
		case "product_ins":
			// 2
			insProduct(req, resp);
			break;
		case "product_sel":
			// 1
			selProduct(req, resp);
			break;
		case "product_sel_id":
			// 2
			selProductById(req, resp);
			break;
		case "product_upt":
			// 2
			uptProduct(req, resp);
			break;
		case "product_del":
			// 2
			delProduct(req, resp);
			break;
		case "product_id_productName":
			// 2
			selProductIdAndProductName(req, resp);
			break;
		case "product_img_ins":
			//2
			insProductImg(req, resp);
			break;
		case "product_img_sel":
			//1
			selProductImg(req, resp);
			break;
		// case "product_img_sel_pId":
		// break;
		case "product_img_sel_id":
			selProductImgById(req, resp);
			break;
		case "product_img_upt":
			uptProductImg(req, resp);
			break;
		case "product_img_del":
			//2
			delProductImg(req, resp);
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
		// String typeIco = request.getParameter("type_ico");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String json = "";

		try {
			ProductType data = new ProductType();
			data.setFid(StrUtil.isBlank(fid) ? 0 : Integer.parseInt(fid));
			data.setType_name(typeName);
			// data.setType_ico(typeIco);
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
			Result<Page<ProductType>> selProductType = service.selProductType("20");
			System.err.println(selProductType);
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
			Result<List<Map<String, Object>>> selProductTypeIdAndTypeName = service.selProductTypeIdAndTypeName(id);
			request.setAttribute("select", selProductTypeIdAndTypeName);
		} catch (Exception e) {
			request.setAttribute("data", new Result<>(Result.ERROR_6000, "查询特定的产品信息异常"));
			log.error("查询特定的产品信息异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/productType/editLayer.jsp").forward(request, response);
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
			Result<Page<Product>> selProduct = service.selProduct("10");
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
		System.err.println(id);
		try {
			Result<Product> selProductById = StrUtil.isBlank(id) ? new Result<Product>(Result.ERROR_4000, "参数错误")
					: service.selProductById(id);
			Result<List<Map<String, Object>>> selProductIdAndProductName = service.selProductIdAndProductName();
			request.setAttribute("select", selProductIdAndProductName);
			request.setAttribute("data", selProductById);
		} catch (Exception e) {
			request.setAttribute("data", new Result<>(Result.ERROR_6000, "查询特定的产品信息异常"));
			log.error("查询特定的产品信息异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/product/editLayer.jsp").forward(request, response);
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
			Result<List<Map<String, Object>>> selProductIdAndProductNameNoBound = service.selProductIdAndProductName();
			json = JSON.toJSONString(selProductIdAndProductNameNoBound);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "查询所有产品的键值对异常"));
			log.error("查询所有产品的键值对异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 
	 * @Title: insProductImg
	 * @Description: 添加产品图片
	 * @param request
	 * @param response
	 * @author: MR.H
	 * @return: void
	 * @throws IOException
	 *
	 */
	@SuppressWarnings("unchecked")
	private void insProductImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = "";
		String prefixProductImg = Constant.PREFIXPRODUCTIMG;
		try {
			Map<String, Object> multipartData = IOUtil.getMultipartData2Bean(request, ProductImg.class);// 获得文件流和普通表单JavaBean集合
			List<InputStream> streams = (List<InputStream>) multipartData.get("stream");
			ProductImg data = (ProductImg) multipartData.get("formField");
			for (InputStream inputStream : streams) {
				DefaultPutRet uploadImg;
				uploadImg = QniuUtil.uploadImg(inputStream,
						prefixProductImg + DateUtil.curDateYMDHMSSForService());
				ProductImg tempData = IOUtil.deepClone(data);
				tempData.setImg_url(uploadImg.key);
				System.err.println(tempData);
				Result<Object> result = service.insProductImg(tempData);
				json = JSON.toJSONString(result);
				if (result.getCode() != 0) {
					break;
				}
			}
		} catch (QiniuException e) {
			Response errorResp = e.response;
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加产品图片（七牛图片上传）异常"));
			log.error("添加产品图片（七牛图片上传）失败,失败原因:【" + errorResp.getInfo() + "】");
		} catch (IOException e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加产品图片（文件解析）异常"));
			log.error("添加产品图片（文件解析）异常,异常原因:【" + e.toString() + "】");
		} catch (FileUploadException e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加产品图片（文件上传）异常"));
			log.error("添加产品图片（文件上传）异常,异常原因:【" + e.toString() + "】");
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "添加产品图片异常"));
			log.error("添加产品图片异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 查询全部产品图片信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月18日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void selProductImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Result<Page<ProductImg>> selProductImg = service.selProductImg("10");
			request.setAttribute("data", selProductImg);
		} catch (Exception e) {
			log.error("查询全部的产品图片异常,异常原因:【" + e.toString() + "】");
		}
		System.err.println("dasd");
		request.getRequestDispatcher("view/productImg/index.jsp").forward(request, response);
	}

	// 废弃
	// private void selProductImgByPid(HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// }
	/**
	 * 查询特定的产品图片
	 * 
	 * @author 黄官易
	 * @date 2018年5月18日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selProductImgById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Result<ProductImg> selProductImgById = StrUtil.isBlank(id) ? new Result<>(Result.ERROR_4000, "参数错误")
					: service.selProductImgById(id);
			request.setAttribute("data", selProductImgById);
		} catch (Exception e) {
			request.setAttribute("data", new Result<>(Result.ERROR_6000, "查询特定的产品图片异常"));
			log.error("查询特定的产品图像异常,异常原因:【" + e.toString() + "】");
		}
		request.getRequestDispatcher("view/productImg/editLayer.jsp").forward(request, response);
	}

	/**
	 * 修改产品图片信息
	 * 
	 * @author 黄官易
	 * @date 2018年5月18日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void uptProductImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pid = request.getParameter("pid");
		String title = request.getParameter("title");
		String isMain = request.getParameter("is_main");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String json = "";

		try {
			ProductImg data = new ProductImg();
			data.setId(Integer.parseInt(id));
			data.setPid(Integer.parseInt(pid));
			data.setTitle(title);
			data.setIs_main(Integer.parseInt(isMain));
			data.setStatus(Integer.parseInt(status));
			data.setSort(Integer.parseInt(sort));

			Result<Object> uptProductImg = service.uptProductImg(data);
			json = JSON.toJSONString(uptProductImg);
		} catch (NumberFormatException e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_4000, "参数错误"));
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "修改产品图片信息异常"));
			log.error("修改产品图片信息异常,异常原因:【" + e.toString() + "】");
		}
		returnData(json, response);
	}

	/**
	 * 删除产品图片
	 * 
	 * @author 黄官易
	 * @date 2018年5月18日
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delProductImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String json = "";
		try {
			Result<Object> delProductImg = StrUtil.isBlank(id) ? new Result<>(Result.ERROR_4000, "参数错误")
					: service.delProductImg(id);
			json = JSON.toJSONString(delProductImg);
		} catch (Exception e) {
			json = JSON.toJSONString(new Result<>(Result.ERROR_6000, "删除产品图片异常"));
			log.error("删除产品图片异常,异常原因:【" + e.toString() + "】");
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
