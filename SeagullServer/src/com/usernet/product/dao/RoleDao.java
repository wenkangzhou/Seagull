package com.usernet.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.usernet.product.comm.BaseDao;
import com.usernet.product.comm.HibernateSessionFactory;
import com.usernet.product.entity.Auth;
import com.usernet.product.entity.Role;

public class RoleDao extends BaseDao  {
	
	@SuppressWarnings("unchecked")
	public List<Role> getAllRole(){
		String hql = "FROM Role where deleted=0";
		List<Role> list = this.getAllByHql(hql);
		return list;
	}
	
	public List<Auth> getAuthById(Integer[] auth_id){
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Criteria c = session.createCriteria(Auth.class);
		c.add(Restrictions.in("id", auth_id));
		@SuppressWarnings("unchecked")
		List<Auth> list = c.list();
		session.close();
		return list;
	}
}
