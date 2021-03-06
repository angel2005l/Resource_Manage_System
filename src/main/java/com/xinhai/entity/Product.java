package com.xinhai.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {

	private int id;
	private int tid;
	private String buy_id;
	private String product_name;
	private BigDecimal original_price;
	private BigDecimal price;
	private String item_no;
	private String info;
	private String remark;
	private Timestamp add_time;
	private int status;
	private int sort;

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(String buy_id) {
		this.buy_id = buy_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public BigDecimal getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(BigDecimal original_price) {
		this.original_price = original_price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", tid=" + tid + ", buy_id=" + buy_id + ", product_name=" + product_name
				+ ", original_price=" + original_price + ", price=" + price + ", item_no=" + item_no + ", info=" + info
				+ ", remark=" + remark + ", add_time=" + add_time + ", status=" + status + ", sort=" + sort + "]";
	}

}
