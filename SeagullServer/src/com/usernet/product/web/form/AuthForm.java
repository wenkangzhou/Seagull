package com.usernet.product.web.form;

import com.usernet.product.comm.BaseForm;
import com.usernet.product.entity.Auth;

public class AuthForm extends BaseForm{
	private static final long serialVersionUID = -3969508460010068656L;
	
	private Auth auth = new Auth();

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Auth getAuth() {
		return auth;
	}
}
