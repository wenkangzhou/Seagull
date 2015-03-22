package com.usernet.product.dao;

import java.util.List;

import com.usernet.product.comm.BaseDao;
import com.usernet.product.entity.Auth;

public class AuthDao extends BaseDao  {
	
	@SuppressWarnings("unchecked")
	public List<Auth> getAllAuth(){
		String hql = "FROM Auth where deleted=0 order by id desc";
		List<Auth> list = this.getAllByHql(hql);
		return list;
	}
}
