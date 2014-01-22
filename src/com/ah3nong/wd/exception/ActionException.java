package com.ah3nong.wd.exception;

public class ActionException extends CommonException{
	// 构造参数
	public ActionException(String errcode) {
		super(errcode);
	}

	public ActionException(String errcode, String s) {
		super(errcode, s);
	}

	public ActionException(String errcode, Throwable t) {
		super(errcode, t);
	}

	public ActionException(String errcode, String s, Throwable t) {
		super(errcode, s, t);
	}
}
