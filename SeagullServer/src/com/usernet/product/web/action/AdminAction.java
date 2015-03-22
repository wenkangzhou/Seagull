package com.usernet.product.web.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.usernet.product.comm.BaseAction;
import com.usernet.product.entity.AdminLog;
import com.usernet.product.entity.Channel;
import com.usernet.product.utils.ProductUtils;

public class AdminAction extends BaseAction {

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
		String loginName = request.getParameter("loginName");
		String password = ProductUtils.getMD5String(request.getParameter("password"));
		int ret = channelDao.login(loginName, password);
		response.setCharacterEncoding("UTF-8");
		if (ret == -1) {
			request.setAttribute("error", "用户名或口令不正确");
			return mapping.findForward("login");
		} else {
			Channel admin = (Channel) channelDao.getById(Channel.class, ret);
			request.getSession().setAttribute("admin", admin);
			// 记录用户登陆记录
			AdminLog adminLog = new AdminLog(admin.getChannel(), "登陆系统",request.getRemoteAddr(),new Timestamp(System.currentTimeMillis()));
			channelDao.save(adminLog);
		}
		String remember = request.getParameter("remember");
		Cookie cookieUsername = new Cookie("channel_username", loginName);
		Cookie cookiePassword = new Cookie("channel_password",request.getParameter("password"));
		if (StringUtils.isNotEmpty(remember)) {
			// 设置生命周期为1小时，秒为单位
			cookieUsername.setMaxAge(31536000);
			cookiePassword.setMaxAge(31536000);
			response.addCookie(cookieUsername);
			response.addCookie(cookiePassword);
		} else {
			cookieUsername.setMaxAge(0);
			cookiePassword.setMaxAge(0);
			response.addCookie(cookieUsername);
			response.addCookie(cookiePassword);
		}
		return mapping.findForward("success");
	}

	/***
	 * 退出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().removeAttribute("admin");
		return mapping.findForward("login");
	}

	public ActionForward toPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return mapping.findForward("password");
	}

	/** 修改密码 */
	public ActionForward doPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("password1");
		Channel admin = (Channel) request.getSession().getAttribute("admin");
		if (!admin.getPassword().equals(ProductUtils.getMD5String(password1))) {
			request.setAttribute("msg", "原密码不正确");
			request.setAttribute("return", "admin.do?method=toPassword");
			return mapping.findForward("password");
		} else {
			admin.setPassword(ProductUtils.getMD5String(password2));
			if (channelDao.modify(admin)) {
				request.getSession().setAttribute("admin", admin);
				request.setAttribute("msg", "修改成功");
				return mapping.findForward("password");
			} else {
				request.setAttribute("msg", "修改失败");
				return mapping.findForward("password");
			}
		}
	}

}
