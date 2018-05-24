package com.xinhai.entity;

import java.sql.Timestamp;

public class Article {

	private int id;
	private String title;
	private int tid;
	private String content;
	private String main_content;
	private String manager_id;
	private int status;
	private Timestamp add_time;

	public Article() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMain_content() {
		return main_content;
	}

	public void setMain_content(String main_content) {
		this.main_content = main_content;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", tid=" + tid + ", content=" + content + ", main_content="
				+ main_content + ", manager_id=" + manager_id + ", status=" + status + ", add_time=" + add_time + "]";
	}

}
