package com.ah3nong.wd.exception;

public class ServiceException extends CommonException{
	// 构造参数
	public ServiceException(String errcode) {
		super(errcode);
	}

	public ServiceException(String errcode, String s) {
		super(errcode, s);
	}

	public ServiceException(String errcode, Throwable t) {
		super(errcode, t);
	}

	public ServiceException(String errcode, String s, Throwable t) {
		super(errcode, s, t);
	}
}
