package com.jtj.exam.app.controller;

import com.jtj.exam.app.Rq;

public class UsrSystemController extends Controller{
	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/article/write")) {
			actionExit(rq);
		}
	}

	private void actionExit(Rq rq) {
		System.out.println("프로그램을 종료합니다.");
	}
 
}
