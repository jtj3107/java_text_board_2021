package com.jtj.exam.app.interseptor;

import com.jtj.exam.app.Rq;

public class NeedLogoutInterceptor implements Interceptor {

	@Override
	public boolean run(Rq rq) {
		if (rq.isLogined() == false) {
			return true;
		}
		
		switch(rq.getActionPath()){
		case "/usr/member/login":
		case "/usr/member/join":
		case "/usr/member/findLoginId":
		case "/usr/member/findLoginPw":
			System.out.println("이미 로그인 되었습니다.");
			return false;
		}
		return true;
	}

}
