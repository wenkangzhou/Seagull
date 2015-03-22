package com.usernet.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.oscache.util.StringUtil;
import com.usernet.product.comm.BaseDao;
import com.usernet.product.entity.Photo;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;


public class PhotoDao extends BaseDao {

	/**
	 * 图列表
	 * @param page
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageResult<Photo> getPage(Page page, String startDate, String endDate, String operator) {
		String hql = "from Photo where 1=1 ";
		if(!StringUtil.isEmpty(startDate)){
			hql += " and time>='"+startDate+"'";
		}
		if(!StringUtil.isEmpty(endDate)){
			hql += " and time<='"+endDate+"'";
		}
		if(!StringUtil.isEmpty(operator)){
			hql += " and operator like '%"+operator+"%'";
		}
		hql += " order by time desc";
		return super.getAllByPageAndHql(page, hql);
	}
	/**
	 * 根据日期取数据
	 * @param page
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getData(String nowdate, String beforedate) {
		String hql = "from Photo where time>='"+beforedate+"' and time<='"+nowdate+"'";
		hql += " order by time desc";
		return getAllByHql(hql);
	}
	/**
	 * 检查唯一性
	 * @param time
	 * @return
	 */
	public Photo getByTime(String time){
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			String hql=" from Photo where time = '"+time+"'";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			Photo result = (Photo) query.uniqueResult();
			tr.commit();
			return result;
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}
}