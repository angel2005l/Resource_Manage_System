package com.xinhai.service;

import java.util.List;
import java.util.Map;

import com.xinhai.entity.Product;
import com.xinhai.entity.ProductImg;
import com.xinhai.entity.ProductType;
import com.xinhai.util.Page;
import com.xinhai.util.Result;

public interface IProductService {

	// 产品分类管理
	public Result<Object> insProductType(ProductType data) throws Exception;

	public Result<Page<ProductType>> selProductType(String showCount,String page,ProductType data) throws Exception;

	public Result<ProductType> selProductTypeById(String id) throws Exception;

	public Result<Object> uptProductType(ProductType data) throws Exception;

	public Result<Object> delProductType(String id) throws Exception;

	public Result<List<Map<String, Object>>> selProductTypeIdAndTypeName(String id) throws Exception;

	// 产品管理

	public Result<Object> insProduct(Product data) throws Exception;

	public Result<Page<Product>> selProduct(String showCount,String page,Product data) throws Exception;

	public Result<Product> selProductById(String id) throws Exception;

	public Result<Object> uptProduct(Product data) throws Exception;

	public Result<Object> delProduct(String id) throws Exception;

	public Result<List<Map<String, Object>>> selProductIdAndProductName() throws Exception;

	// 产品图片管理
	public Result<Object> insProductImg(ProductImg data) throws Exception;

	public  Result<Page<ProductImg>> selProductImg(String showCount,String page,ProductImg data) throws Exception;

	public Result<List<ProductImg>> selProductionImgByProId(String proId) throws Exception;

	public Result<ProductImg> selProductImgById(String id) throws Exception;

	public Result<Object> uptProductImg(ProductImg data) throws Exception;

	public Result<Object> delProductImg(String id) throws Exception;

}
