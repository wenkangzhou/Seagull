package com.usernet.product.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.usernet.product.dao.ChannelDao;
import com.usernet.product.dao.FeedBackDao;
import com.usernet.product.dao.MusicDao;
import com.usernet.product.dao.PhotoDao;
import com.usernet.product.dao.SuiDao;
import com.usernet.product.dao.TaoDao;
import com.usernet.product.dao.UploadDao;
import com.usernet.product.entity.Channel;
import com.usernet.product.utils.BeanFactory;

public class BaseAction extends DispatchAction {

	protected ChannelDao channelDao = (ChannelDao) BeanFactory.getBean("channelDao");
	protected FeedBackDao feedbackDao = (FeedBackDao) BeanFactory.getBean("feedbackDao");
	protected PhotoDao photoDao = (PhotoDao) BeanFactory.getBean("photoDao");
	protected MusicDao musicDao = (MusicDao) BeanFactory.getBean("musicDao");
	protected TaoDao taoDao = (TaoDao) BeanFactory.getBean("taoDao");
	protected SuiDao suiDao = (SuiDao) BeanFactory.getBean("suiDao");
	protected UploadDao uploadDao = (UploadDao) BeanFactory.getBean("uploadDao");
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		HttpSession session = request.getSession();
		Channel admin = (Channel) session.getAttribute("admin");
		String uri = request.getRequestURL().toString();
		boolean a = !uri.substring(uri.lastIndexOf("/"), uri.length()).equals("/admin.do");//不是登陆
		if(admin == null  && a){
			request.setAttribute("error", "登陆超时 请重新登陆!");
			return mapping.findForward("login"); 
		}
		return super.execute(mapping, form, request, response);
	}
	public Channel getCurrentChannelSession(HttpServletRequest request){
		return (Channel) request.getSession().getAttribute("admin");
	}
	
}