package com.usernet.product.web.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.usernet.product.comm.BaseAction;
import com.usernet.product.entity.Upload;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;
import com.usernet.product.web.form.UploadForm;

public class UploadAction extends BaseAction {

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
		UploadForm mForm = (UploadForm) form;
		PageResult<Upload> result = null;
		Page page = new Page();
		// 分页信息
		String pageNo = mForm.getPageNo();
		String pageSize = mForm.getPageSize();
		if (pageSize != null)
			page.setEveryPage(Integer.parseInt(pageSize.trim()));
		if (pageNo != null)
			page.setCurrentPage(Integer.parseInt(pageNo.trim()));
		result = uploadDao.getPage(page, mForm.getStartDate(),mForm.getEndDate());
		request.setAttribute("form", mForm);
		request.setAttribute("list", result.getContent());
		request.setAttribute("page", result.getPage());
		request.setAttribute("size", result.getSize());
		return mapping.findForward("list");
	}
}
