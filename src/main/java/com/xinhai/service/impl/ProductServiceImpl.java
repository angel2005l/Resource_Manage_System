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
import com.xinhai.entity.Product;
import com.xinhai.entity.ProductImg;
import com.xinhai.entity.ProductType;
import com.xinhai.service.IProductService;
import com.xinhai.util.DateUtil;
import com.xinhai.util.HttpClientUtil;
import com.xinhai.util.Page;
import com.xinhai.util.Result;

public class ProductServiceImpl extends BaseResult implements IProductService {
	private ResourceBundle rb = ResourceBundle.getBundle("daoApi");

	@Override
	public Result<Object> insProductType(ProductType data) throws Exception {
		String url = rb.getString("product_type_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "insertProductType"));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		// params.add(new BasicNameValuePair("type_ico", data.getType_ico()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加产品分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加产品分类失败" : jb.getString("msg"));
	}

	@Override
	public Result<Page<ProductType>> selProductType(int showCount) throws Exception {
		String url = rb.getString("product_type_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "selectProductType"));
		params.add(new BasicNameValuePair("page", "1"));
		params.add(new BasicNameValuePair("pageSize", showCount + ""));
		// params.add(new BasicNameValuePair("", ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		// data 有隔层
		JSONObject dataJb = JSON.parseObject(jb.getString("data"));
		return rtnPageWithCount(jb.getIntValue("code"), jb.getString("msg"), showCount,
				dataJb.getIntValue("totalResult"), JSON.parseArray(dataJb.getString("data"), ProductType.class));
	}

	@Override
	public Result<ProductType> selProductTypeById(String id) throws Exception {
		String url = rb.getString("product_type_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "getProductTypeById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), ProductType.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptProductType(ProductType data) throws Exception {
		String url = rb.getString("product_type_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "updateProductTypeById"));
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("type_ico", data.getType_ico()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("修改产品分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "修改产品分类失败" : jb.getString("msg"));
	}

	@Override
	public Result<Object> delProductType(String id) throws Exception {
		String url = rb.getString("product_type_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "deleteProductTypeById"));
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("删除产品分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "删除产品分类失败" : jb.getString("msg"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<List<Map<String, Object>>> selProductTypeIdAndTypeName(String id) throws Exception {
		String url = rb.getString("product_type_id_typeName");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parameter", "besidesProductTypeById"));
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
	public Result<Object> insProduct(Product data) throws Exception {
		String url = rb.getString("product_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tid", data.getTid() + ""));
		params.add(new BasicNameValuePair("product_name", data.getProduct_name()));
		params.add(new BasicNameValuePair("original_price", data.getOriginal_price().toString()));
		params.add(new BasicNameValuePair("price", data.getPrice().toString()));
		params.add(new BasicNameValuePair("item_no", data.getItem_no()));
		params.add(new BasicNameValuePair("info", data.getInfo()));
		params.add(new BasicNameValuePair("remark", data.getRemark()));
		params.add(new BasicNameValuePair("add_time", DateUtil.curDateYMDHMS()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加产品信息成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加产品信息失败" : jb.getString("msg"));
	}

	@Override
	public Result<List<Product>> selProduct() throws Exception {
		String url = rb.getString("product_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), Product.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Product> selProductById(String id) throws Exception {
		String url = rb.getString("product_sel_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), Product.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptProduct(Product data) throws Exception {
		String url = rb.getString("product_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("tid", data.getTid() + ""));
		params.add(new BasicNameValuePair("product_name", data.getProduct_name()));
		params.add(new BasicNameValuePair("original_price", data.getOriginal_price().toString()));
		params.add(new BasicNameValuePair("price", data.getPrice().toString()));
		params.add(new BasicNameValuePair("item_no", data.getItem_no()));
		params.add(new BasicNameValuePair("info", data.getInfo()));
		params.add(new BasicNameValuePair("remark", data.getRemark()));
		params.add(new BasicNameValuePair("add_time", DateUtil.curDateYMDHMS()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("修改产品信息成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "修改产品信息失败" : jb.getString("msg"));
	}

	@Override
	public Result<Object> delProduct(String id) throws Exception {
		String url = rb.getString("product_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("删除产品信息成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "删除产品信息失败" : jb.getString("msg"));
	}

	@Override
	public Result<Object> insProductImg(ProductImg data) throws Exception {
		String url = rb.getString("product_img_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pid", data.getPid() + ""));
		params.add(new BasicNameValuePair("img_url", data.getImg_url()));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("is_main", data.getIs_main() + ""));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加产品图片成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加产品图片失败" : jb.getString("msg"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<List<Map<String, Object>>> selProductIdAndProductName() throws Exception {
		String url = rb.getString("product_id_productName");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
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
	public Result<List<ProductImg>> selProductImg() throws Exception {
		String url = rb.getString("product_img_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("", ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), ProductImg.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<List<ProductImg>> selProductionImgByProId(String proId) throws Exception {
		String url = rb.getString("product_img_sel_pId");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pid", proId));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), ProductImg.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<ProductImg> selProductImgById(String id) throws Exception {
		String url = rb.getString("product_img_id");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseObject(jb.getString("data"), ProductImg.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Object> uptProductImg(ProductImg data) throws Exception {
		String url = rb.getString("product_img_upt");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("pid", data.getPid() + ""));
		params.add(new BasicNameValuePair("img_url", data.getImg_url()));
		params.add(new BasicNameValuePair("title", data.getTitle()));
		params.add(new BasicNameValuePair("is_main", data.getIs_main() + ""));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("修改产品图片成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "修改产品图片失败" : jb.getString("msg"));
	}

	@Override
	public Result<Object> delProductImg(String id) throws Exception {
		String url = rb.getString("product_img_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("删除产品图片成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "删除产品图片失败" : jb.getString("msg"));
	}

}
