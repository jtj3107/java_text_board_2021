package com.jtj.exam.app.interseptor;

import com.jtj.exam.app.Rq;

public interface Interceptor {
	boolean run(Rq rq);
}
