package com.usernet.product.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.usernet.product.comm.BaseAction;
import com.usernet.product.dao.AuthDao;
import com.usernet.product.entity.Auth;
import com.usernet.product.web.form.AuthForm;

public class AuthAction extends BaseAction{
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AuthForm authForm = (AuthForm) form;
		Auth auth = authForm.getAuth();
		AuthDao authDao = new AuthDao();
		authDao.save(auth);
		return mapping.findForward("other");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AuthForm authForm = (AuthForm) form;
		Auth auth = authForm.getAuth();
		Integer id = authForm.getId();
		AuthDao authDao = new AuthDao();
		Auth authDB = (Auth) authDao.getById(Auth.class, id);
		authDB.setAuth_code(auth.getAuth_code());
		authDB.setAuth_name(auth.getAuth_name());
		authDao.modify(authDB);
		return mapping.findForward("other");
	}
	
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AuthDao authDao = new AuthDao();
		List<Auth> list = authDao.getAllAuth();
		request.setAttribute("list", list);
		return mapping.findForward("list");
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("add");
	}
	
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AuthForm authForm = (AuthForm) form;
		Integer id = authForm.getId();
		AuthDao authDao = new AuthDao();
		Auth authDB = (Auth) authDao.getById(Auth.class, id);
		request.setAttribute("item", authDB);
		return mapping.findForward("edit");
	}
}
