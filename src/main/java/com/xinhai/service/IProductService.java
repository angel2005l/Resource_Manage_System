package com.xinhai.service;

import java.util.List;

import com.xinhai.entity.Product;
import com.xinhai.entity.ProductImg;
import com.xinhai.entity.ProductType;
import com.xinhai.util.Result;

public interface IProductService {

	// 产品分类管理
	public Result<Object> insProductType(ProductType data) throws Exception;

	public Result<List<ProductType>> selProductType() throws Exception;

	public Result<ProductType> selProductTypeById(String id) throws Exception;

	public Result<Object> uptProductType(ProductType data) throws Exception;

	public Result<Object> delProductType(String id) throws Exception;

	// 产品管理

	public Result<Object> insProduct(Product data) throws Exception;

	public Result<List<Product>> selProduct() throws Exception;

	public Result<Product> selProductById(String id) throws Exception;

	public Result<Object> uptProduct(Product data) throws Exception;

	public Result<Object> delProduct(String id) throws Exception;

	// 产品图片管理
	public Result<Object> insProductImg(ProductImg data) throws Exception;

	public Result<List<ProductImg>> selProductImg() throws Exception;

	public Result<List<ProductImg>> selProductionImgByProId(String proId) throws Exception;

	public Result<ProductImg> selProductImgById(String id) throws Exception;

	public Result<Object> uptProductImg(ProductImg data) throws Exception;

	public Result<Object> delProductImg(String id) throws Exception;

}
