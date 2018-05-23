package com.xinhai.entity;

import java.sql.Timestamp;

public class ArticleImg {
	private int id;
	private int aid;
	private String title;
	private String url;
	private Timestamp add_time;
	private int status;

	/**
	 * 
	 */
	public ArticleImg() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return "ArticleImg [id=" + id + ", aid=" + aid + ", title=" + title + ", url=" + url + ", add_time=" + add_time
				+ ", status=" + status + "]";
	}

}
