package com.usernet.product.web.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.usernet.product.comm.BaseAction;
import com.usernet.product.entity.Music;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;
import com.usernet.product.web.form.MusicForm;

public class MusicAction extends BaseAction {

	/**
	 * 列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MusicForm mForm = (MusicForm) form;
		PageResult<Music> result = null;
		Page page = new Page();
		// 分页信息
		String pageNo = mForm.getPageNo();
		String pageSize = mForm.getPageSize();
		if (pageSize != null)
			page.setEveryPage(Integer.parseInt(pageSize.trim()));
		if (pageNo != null)
			page.setCurrentPage(Integer.parseInt(pageNo.trim()));
		result = musicDao.getPage(page, mForm.getStartDate(),mForm.getEndDate(),mForm.getOperator());
		request.setAttribute("form", mForm);
		request.setAttribute("list", result.getContent());
		request.setAttribute("page", result.getPage());
		request.setAttribute("size", result.getSize());
		return mapping.findForward("list");
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
		Music music = (Music)musicDao.getById(Music.class, Integer.valueOf(id));
		musicDao.delete(music);
		return mapping.findForward("other");
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
		String id = request.getParameter("id");
		Music music = (Music)musicDao.getById(Music.class, Integer.valueOf(id));
		request.setAttribute("music", music);
		return mapping.findForward("edit");
	}	
	/**
	 * 编辑
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MusicForm mForm = (MusicForm) form;
		Music music = (Music)musicDao.getById(Music.class, mForm.getId());
		music.setTime(mForm.getTime());
		music.setVol(mForm.getVol());
		music.setCoversrc(mForm.getCoversrc());
		music.setName(mForm.getName());
		music.setAuthor(mForm.getAuthor());
		music.setMusicsrc(mForm.getMusicsrc());
		music.setOperator(mForm.getOperator());
		musicDao.modify(music);
		return mapping.findForward("other");
	}	
	/**
	 * 去新增页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("add");
	}
	/**
	 * 新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MusicForm mForm = (MusicForm) form;
		Music music = new Music();
		music.setTime(mForm.getTime());
		music.setVol(mForm.getVol());
		music.setCoversrc(mForm.getCoversrc());
		music.setName(mForm.getName());
		music.setAuthor(mForm.getAuthor());
		music.setMusicsrc(mForm.getMusicsrc());
		music.setOperator(mForm.getOperator());
		musicDao.save(music);
		return mapping.findForward("other");
	}	
	/**
	 * 检查唯一性
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkUnique(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String time = request.getParameter("time");
		Music music = musicDao.getByTime(time);
		if(music==null){
			response.getWriter().print(0);
		}else{
			response.getWriter().print(1);
		}
		return null;
	}	
	/**
	 * 预览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward Preview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Music music = (Music)musicDao.getById(Music.class, Integer.valueOf(id));
		request.setAttribute("music", music);
		return mapping.findForward("preview");
	}	
}
