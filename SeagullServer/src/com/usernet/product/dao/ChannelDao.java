package com.usernet.product.dao;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.usernet.product.comm.BaseDao;
import com.usernet.product.entity.Channel;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;


public class ChannelDao extends BaseDao {
	/*
	 * return "adminId"：登录成功，"-1"：登录失败
	 */
	@SuppressWarnings("rawtypes")
	public int login(String loginName, String password) {
		Session session = getSession();
		String hql = "from Channel where channel=:channel and status = 0";
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setString("channel", loginName);
			List list = query.list();
			tr.commit();
			Channel admin = null;
			if (list.size() > 0)
				admin = (Channel) list.get(0);
			if (admin == null || !admin.getPassword().equals(password))
				return -1;
			return admin.getId();
		} catch (HibernateException e) {
			if (tr != null)
				tr.rollback();
			throw e;
		}
	}

	/**
	 * 检查登录名是否存在 
	 * @param id
	 * @param loginName
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public Channel checkChannel(Integer id, String loginName) {
		Session session = getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			String hql = "from Channel where channel=:channel";
			if (id != null && id.intValue() != 0)
				hql += " and id !=" + id;
			Query query = session.createQuery(hql);
			query.setString("channel", loginName);
			List<Channel> list = query.list();
			tr.commit();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			throw e;
		}
	}


	@SuppressWarnings("unchecked")
	public Integer saveChannel(Channel channel) {
		Session session = getSession();
		Transaction tr = null;
		try {
			int id = 0;
			tr = session.beginTransaction();
			String hql = "from Channel where type=0 and channel=:channel";
			Query query = session.createQuery(hql);
			query.setString("channel", channel.getChannel());
			List<Channel> list = query.list();
			if (!list.isEmpty())
				return list.get(0).getId();
			id = Integer.parseInt(String.valueOf(session.save(channel)));
			tr.commit();
			return id;
		} catch (Exception ex) {
			if (tr != null)
				tr.rollback();
			ex.printStackTrace();
			return 0;
		}
	}

	/**
	 * 用户列表
	 * @param page
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageResult<Channel> getPage(Page page, String code) {
		String hql = "from Channel where  status = 0 ";
		if (StringUtils.isNotEmpty(code)) {
			hql += " and (channel like '%" + code + "%' or name like '%" + code+ "%')";
		}
		hql += " order by time desc";
		return super.getAllByPageAndHql(page, hql);
	}
}