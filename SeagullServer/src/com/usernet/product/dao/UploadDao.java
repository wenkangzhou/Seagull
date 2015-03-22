package com.usernet.product.dao;

import com.opensymphony.oscache.util.StringUtil;
import com.usernet.product.comm.BaseDao;
import com.usernet.product.entity.Upload;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;


public class UploadDao extends BaseDao {

	/**
	 * 投稿列表
	 * @param page
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageResult<Upload> getPage(Page page,String startDate, String endDate) {
		String hql = "from Upload where 1=1 ";
		if(!StringUtil.isEmpty(startDate)){
			hql += " and time>='"+startDate+"'";
		}
		if(!StringUtil.isEmpty(endDate)){
			hql += " and time<='"+endDate+"'";
		}
		hql += " order by time desc";
		return super.getAllByPageAndHql(page, hql);
	}
}