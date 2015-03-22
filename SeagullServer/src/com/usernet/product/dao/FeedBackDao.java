package com.usernet.product.dao;

import com.opensymphony.oscache.util.StringUtil;
import com.usernet.product.comm.BaseDao;
import com.usernet.product.entity.FeedBack;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;


public class FeedBackDao extends BaseDao {

	/**
	 * 反馈列表
	 * @param page
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageResult<FeedBack> getPage(Page page,String startDate, String endDate) {
		String hql = "from FeedBack where 1=1 ";
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