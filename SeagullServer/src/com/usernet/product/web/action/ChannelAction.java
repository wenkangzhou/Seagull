package com.usernet.product.web.action;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.usernet.product.comm.BaseAction;
import com.usernet.product.dao.RoleDao;
import com.usernet.product.entity.Channel;
import com.usernet.product.entity.Role;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;
import com.usernet.product.utils.ProductConfig;
import com.usernet.product.utils.ProductUtils;
import com.usernet.product.web.form.ChannelForm;

public class ChannelAction extends BaseAction {
	/**
	 * 列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChannelForm mForm = (ChannelForm) form;
		PageResult<Channel> result = null;
		Page page = new Page();
		// 分页信息
		String pageNo = mForm.getPageNo();
		String pageSize = mForm.getPageSize();
		if (pageSize != null)
			page.setEveryPage(Integer.parseInt(pageSize.trim()));
		if (pageNo != null)
			page.setCurrentPage(Integer.parseInt(pageNo.trim()));
		result = channelDao.getPage(page, mForm.getCode());
		request.setAttribute("form", mForm);
		request.setAttribute("list", result.getContent());
		request.setAttribute("page", result.getPage());
		request.setAttribute("size", result.getSize());
		return mapping.findForward("list");
	}
	/**
	 * 去编辑页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChannelForm mForm = (ChannelForm) form;
		Channel channel = null;
		if(mForm.getId()!=null)
			channel = (Channel) channelDao.getById(Channel.class,mForm.getId());
		RoleDao roleDao = new RoleDao();
		List<Role> listRole = roleDao.getAllRole();
		request.setAttribute("listRole", listRole);	
		request.setAttribute("channel", channel);
		return mapping.findForward("edit");
	}
	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChannelForm mForm = (ChannelForm) form;
		Channel nowchannel = null;
		Channel channel = channelDao.checkChannel(mForm.getId(),mForm.getCode());
		if (channel != null) {
			if(mForm.getId()!=null)
				 nowchannel = (Channel) channelDao.getById(Channel.class,mForm.getId());
			request.setAttribute("channel", nowchannel);	
			request.setAttribute("errMsg", "用户名已存在");	
			RoleDao roleDao = new RoleDao();
			List<Role> listRole = roleDao.getAllRole();
			request.setAttribute("listRole", listRole);
			return mapping.findForward("edit");
		}
		Channel c = new Channel();
		Integer role_id = mForm.getRole_id();
		Role role = (Role) channelDao.getById(Role.class, role_id);
		c.setRole(role);
		if (mForm.getId() != null && mForm.getId() != 0) {
			if (channel == null) {
				channel = (Channel) channelDao.getById(Channel.class, mForm.getId());
			}
			channel.setChannel(mForm.getCode());
			channel.setName(mForm.getName());
			channel.setRole(role);
			channelDao.modify(channel);
		} else {
			c.setId(null);
			c.setChannel(mForm.getCode());
			c.setName(mForm.getName());
			c.setPassword(ProductUtils.getMD5String(ProductConfig.DEFAULT_PASSWORD));
			c.setTime(new Timestamp(System.currentTimeMillis()));
			c.setStatus(0);
			channelDao.save(c);
		}
		return mapping.findForward("other");
	}

	
	/***
	 * 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		String id = request.getParameter("id");
		Channel channel = (Channel)channelDao.getById(Channel.class, Integer.valueOf(id));
		channel.setStatus(1);
		channelDao.modify(channel);
		return mapping.findForward("other");
	}
	
}
