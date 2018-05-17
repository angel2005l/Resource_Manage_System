package com.xinhai.entity;

public class ProductType {

	private int id;
	private int fid;
	private String type_name;
	private String type_ico;
	private int status;
	private int sort;

	public ProductType() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_ico() {
		return type_ico;
	}

	public void setType_ico(String type_ico) {
		this.type_ico = type_ico;
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
		return "ProductType [id=" + id + ", fid=" + fid + ", type_name=" + type_name + ", type_ico=" + type_ico
				+ ", status=" + status + ", sort=" + sort + "]";
	}

}
