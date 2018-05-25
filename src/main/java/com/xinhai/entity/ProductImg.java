package com.xinhai.entity;

import java.io.Serializable;

public class ProductImg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int pid;
	private String img_url;
	private String title;
	private int is_main;
	private int img_type;
	private int status;
	private int sort;

	public ProductImg() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIs_main() {
		return is_main;
	}

	public void setIs_main(int is_main) {
		this.is_main = is_main;
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

	public int getImg_type() {
		return img_type;
	}

	public void setImg_type(int img_type) {
		this.img_type = img_type;
	}

	@Override
	public String toString() {
		return "ProductImg [id=" + id + ", pid=" + pid + ", img_url=" + img_url + ", title=" + title + ", is_main="
				+ is_main + ", img_type=" + img_type + ", status=" + status + ", sort=" + sort + "]";
	}

}
