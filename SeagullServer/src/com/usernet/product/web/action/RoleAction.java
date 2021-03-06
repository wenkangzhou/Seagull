package com.usernet.product.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.usernet.product.comm.BaseAction;
import com.usernet.product.dao.AuthDao;
import com.usernet.product.dao.RoleDao;
import com.usernet.product.entity.Auth;
import com.usernet.product.entity.Role;
import com.usernet.product.web.form.RoleForm;

public class RoleAction extends BaseAction{
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleForm authForm = (RoleForm) form;
		Role role = authForm.getRole();
		RoleDao roleDao = new RoleDao();
		roleDao.save(role);
		return mapping.findForward("other");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleForm authForm = (RoleForm) form;
		Role role = authForm.getRole();
		Integer id = authForm.getId();	
		RoleDao roleDao = new RoleDao();
		Role roleDB = (Role) roleDao.getById(Role.class, id);
		roleDB.setRole_name(role.getRole_name());
		roleDao.modify(roleDB);
		return mapping.findForward("other");
	}

	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleDao roleDao = new RoleDao();
		List<Role> list = roleDao.getAllRole();
		request.setAttribute("list", list);
		return mapping.findForward("list");
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("add");
	}
	
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleForm roleForm = (RoleForm) form;
		Integer id = roleForm.getId();
		RoleDao roleDao = new RoleDao();
		Role roleDB = (Role) roleDao.getById(Role.class, id);
		request.setAttribute("item", roleDB);
		return mapping.findForward("edit");
	}
	
	public ActionForward toAuthorize(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleForm roleForm = (RoleForm) form;
		Integer id = roleForm.getId();
		RoleDao roleDao = new RoleDao();
		Role roleDB = (Role) roleDao.getById(Role.class, id);
		AuthDao authDao = new AuthDao();
		List<Auth> listAuth = authDao.getAllAuth();
		request.setAttribute("role", roleDB);
		request.setAttribute("listAuth", listAuth);
		return mapping.findForward("authorize");
	}
	
	public ActionForward authorize(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleForm roleForm = (RoleForm) form;
		Integer id = roleForm.getId();
		RoleDao roleDao = new RoleDao();
		Role roleDB = (Role) roleDao.getById(Role.class, id);
		List<Auth> authList = roleDao.getAuthById(roleForm.getAuth_id());
		Set<Auth> authSet = new HashSet<Auth>(authList);
		roleDB.setAuth(authSet);
		roleDB.setIsAuthorize(1);
		roleDao.modify(roleDB);
		return mapping.findForward("other");
	}
}
