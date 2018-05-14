/**
 * 吉尔斯·德·莱斯
 */
package com.xinhai.entity;

public class ArticleType {

	private int id;
	private int fid;
	private String type_name;
	private int status;
	private int sort;

	public ArticleType() {
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
		return "ArticleType [id=" + id + ", fid=" + fid + ", type_name=" + type_name + ", status=" + status + ", sort="
				+ sort + "]";
	}

}
