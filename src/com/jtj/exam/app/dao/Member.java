package com.jtj.exam.app.dao;

import lombok.Data;

@Data
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickName;
	
	public Member(int id, String regDate, String updateDate, String loginId, String loginPw, String name,
			String nickName) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.nickName = nickName;
	}
	
}
