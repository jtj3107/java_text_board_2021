package com.jtj.exam.app.dao;

import lombok.Data;

@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	
	public Article(int id, String regDate, String updateDate, String title, String body) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}

}
