package com.xinhai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.entity.Product;
import com.xinhai.entity.ProductImg;
import com.xinhai.entity.ProductType;
import com.xinhai.service.IProductService;
import com.xinhai.util.HttpClientUtil;
import com.xinhai.util.Result;

import base.BaseResult;

public class ProductServiceImpl extends BaseResult implements IProductService {
	private ResourceBundle rb = ResourceBundle.getBundle("daoApi");

	@Override
	public Result<Object> insProductType(ProductType data) throws Exception {
		String url = rb.getString("product_type_ins");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("type_ico", data.getType_ico()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJosn = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJosn);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("添加产品分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "添加产品分类失败" : jb.getString("msg"));
	}

	@Override
	public Result<List<ProductType>> selProductType() throws Exception {
		String url = rb.getString("product_type_sel");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("", ""));

		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), ProductType.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<ProductType> selProductTypeById(String id) throws Exception {
		String url = rb.getString("product_type_sel_id");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
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
		params.add(new BasicNameValuePair("id", data.getId() + ""));
		params.add(new BasicNameValuePair("fid", data.getFid() + ""));
		params.add(new BasicNameValuePair("type_name", data.getType_name()));
		params.add(new BasicNameValuePair("type_ico", data.getType_ico()));
		params.add(new BasicNameValuePair("status", data.getStatus() + ""));
		params.add(new BasicNameValuePair("sort", data.getSort() + ""));
		String resultJosn = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJosn);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("修改产品分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "修改产品分类失败" : jb.getString("msg"));
	}

	@Override
	public Result<Object> delProductType(String id) throws Exception {
		String url = rb.getString("product_type_del");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String resultJsom = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJsom);
		int code = jb.getIntValue("code");
		return 0 == code && jb.getIntValue("data") > 0 ? rtnSuccessResult("删除产品分类成功")
				: rtnFailResult(code == 0 ? Result.ERROR_401 : code, code == 0 ? "删除产品分类失败" : jb.getString("msg"));
	}

	@Override
	public Result<Object> insProduct(Product data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<Product>> selProduct() throws Exception {
		String url = rb.getString("product_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("", ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code") ? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), Product.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<Product> selProductById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Object> uptProduct(Product data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Object> delProduct(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Object> insProductImg(ProductImg data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<ProductImg>> selProductImg() throws Exception {
		String url = rb.getString("product_img_sel");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("", ""));
		String resultJson = HttpClientUtil.getPostDefault(url, params);
		JSONObject jb = JSON.parseObject(resultJson);
		return 0 == jb.getIntValue("code")
				? rtnSuccessResult("", JSON.parseArray(jb.getString("data"), ProductImg.class))
				: rtnFailResult(jb.getIntValue("code"), jb.getString("msg"));
	}

	@Override
	public Result<List<ProductImg>> selProductionImgByProId(String proId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ProductImg> selProductImgById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Object> uptProductImg(ProductImg data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Object> delProductImg(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
