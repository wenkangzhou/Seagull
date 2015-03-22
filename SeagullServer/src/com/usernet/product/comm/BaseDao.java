package com.usernet.product.comm;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.usernet.product.utils.Page;
import com.usernet.product.utils.PageResult;
import com.usernet.product.utils.PageUtils;

public class BaseDao {
	/**
	 * 根据HQL查询所有
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getAllByHql(String hql) {
		Session session = getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Query query = session.createQuery(hql);
			List list = query.list();
			tr.commit();
			return list;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
		}
		return null;
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 */
	public int save(Object entity) {
		Session session = getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			int id = Integer.parseInt(String.valueOf(session.save(entity)));
			tr.commit();
			return id;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 插入一条数据
	 * @param sql
	 * @return
	 */
	public boolean save(String sql) {
		 Session session = null;
		Transaction tr = null;
		try {
			session = HibernateSessionFactory.getSession(); 
			tr = session.beginTransaction();
			Statement state = session.connection().createStatement();
			state.executeUpdate(sql);
			tr.commit(); 
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
		e.printStackTrace();
		} 
		return false;
	}
	/**
	 * 批量保存
	 * 
	 * @param entity[]
	 */
	public int[] save(Object[] entitys) {
		Session session = getSession();
		Transaction tr = null;
		try {
			int[] ids = new int[entitys.length];
			tr = session.beginTransaction();
			for (int i = 0; i < entitys.length; i++)
				ids[i] = Integer.parseInt(String.valueOf(session.save(entitys[i])));
			tr.commit();
			return ids;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * deleteByHql
	 * 
	 * @param hql
	 */
	public boolean deleteByHql(String hql) {
		Session session = getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tr.commit();
			return true;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	public boolean modify(Object entity) {
		Session session = getSession();
		
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			session.saveOrUpdate(entity);
			tr.commit();
			return true;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public boolean delete(Object entity) {
		Session session = getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			session.delete(entity);
			tr.commit();
			return true;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * getById
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object getById(@SuppressWarnings("rawtypes") Class clazz, int id) {
		Session session = getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Object object = session.get(clazz, id);
			tr.commit();
			return object;
		} catch (HibernateException e) {
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页方法
	 * 
	 * @param page
	 * @param hql
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageResult getAllByPageAndHql(Page page, String hql) {
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			if (hql == null || hql.equals("")) {
				return null;
			}
			String temp = "";
			if (hql.toUpperCase().indexOf("SELECT") != -1) {
				int i = hql.toUpperCase().indexOf("FROM");
				temp = "Select count(*) " + hql.substring(i, hql.length());
			} else {
				temp = "Select count(*) " + hql;
			}
			int j = temp.toUpperCase().lastIndexOf("ORDER BY");
			if (j != -1) {
				temp = temp.substring(0, j).trim();
			}
			int totalRecords = 0;
			Query query = session.createQuery(temp);
			Object num = query.uniqueResult();
			if (num instanceof Number) {
				totalRecords = ((Number) num).intValue();
			}
			page = PageUtils.createPage(page, totalRecords);

			query = session.createQuery(hql);
			
			query.setFirstResult(page.getBeginIndex());
			query.setMaxResults(page.getEveryPage());
			List content = query.list();
			if (content.size() == 0) {
				content = null;
			}
			tr.commit();
			return new PageResult(page, content);
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 分页方法
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageResult getAllByPageAndSql(Page page, String sql) {
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			if (sql == null || sql.equals("")) {
				return null;
			}
			String temp = "";
			if (sql.toUpperCase().indexOf("SELECT") != -1) {
				int i = sql.toUpperCase().indexOf(" FROM ");
				temp = "Select count(1) " + sql.substring(i, sql.length());
			} else {
				temp = "Select count(1) " + sql;
			}
			int j = temp.toUpperCase().lastIndexOf("ORDER ");
			if (j != -1) {
				temp = temp.substring(0, j).trim();
			}
			int totalRecords = 0;

			Query query = session.createSQLQuery(temp);
			Object num = query.uniqueResult();
			if (num instanceof Number) {
				totalRecords = ((Number) num).intValue();
			}
			page = PageUtils.createPage(page, totalRecords);

			query = session.createSQLQuery(sql);
			query.setFirstResult(page.getBeginIndex());
			query.setMaxResults(page.getEveryPage());
			List content = query.list();
			if (content.size() == 0) {
				content = null;
			}
			tr.commit();
			return new PageResult(page, content);
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 无分页查询方法
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getAllByPageAndSql(String sql) {
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			if (sql == null || sql.equals("")) {
				return null;
			}
			String temp = "";
			if (sql.toUpperCase().indexOf("SELECT") != -1) {
				int i = sql.toUpperCase().indexOf("FROM");
				temp = "Select count(*) " + sql.substring(i, sql.length());
			} else {
				temp = "Select count(*) " + sql;
			}
			int j = temp.toUpperCase().lastIndexOf("ORDER");
			if (j != -1) {
				temp = temp.substring(0, j).trim();
			}
			Query query = session.createSQLQuery(temp);
			query = session.createSQLQuery(sql);
			List content = query.list();
			if (content.size() == 0) {
				content = null;
			}
			tr.commit();
			return content;
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult getAllByPageAndHql(Page page, String hql,Map<String, Timestamp> map) {
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			if (hql == null || hql.equals("")) {
				return null;
			}
			String temp = "";
			if (hql.toUpperCase().indexOf("SELECT") != -1) {
				int i = hql.toUpperCase().indexOf("FROM");
				temp = "Select count(*) " + hql.substring(i, hql.length());
			} else {
				temp = "Select count(*) " + hql;
			}

			int j = temp.toUpperCase().lastIndexOf("ORDER");
			if (j != -1) {
				temp = temp.substring(0, j).trim();
			}
			int totalRecords = 0;
			Query query = session.createQuery(temp);
			Query queryList = session.createQuery(hql);

			if (map != null) {
				for (String key : map.keySet()) {
					Timestamp value = map.get(key);
					query.setTimestamp(key, value);
					queryList.setTimestamp(key, value);
				}
			}
			Object num = query.uniqueResult();
			if (num instanceof Number) {
				totalRecords = ((Number) num).intValue();
			}
			page = PageUtils.createPage(page, totalRecords);

			queryList.setFirstResult(page.getBeginIndex());
			queryList.setMaxResults(page.getEveryPage());
			List content = queryList.list();
			if (content.size() == 0) {
				content = null;
			}
			tr.commit();
			return new PageResult(page, content);
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageResult getAllByPageAndSql(Page page, String sql,Map<String, Timestamp> map) {
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			if (sql == null || sql.equals("")) {
				return null;
			}
			String temp = "";
			if (sql.toUpperCase().indexOf("SELECT") != -1) {
				int i = sql.toUpperCase().indexOf("FROM");
				temp = "Select count(*) " + sql.substring(i, sql.length());
			} else {
				temp = "Select count(*) " + sql;
			}

			int j = temp.toUpperCase().lastIndexOf("ORDER");
			if (j != -1) {
				temp = temp.substring(0, j).trim();
			}
			int totalRecords = 0;
			Query query = session.createSQLQuery(temp);
			Query queryList = session.createSQLQuery(sql);

			if (map != null) {
				for (String key : map.keySet()) {
					Timestamp value = map.get(key);
					query.setTimestamp(key, value);
					queryList.setTimestamp(key, value);
				}
			}
			Object num = query.uniqueResult();
			if (num instanceof Number) {
				totalRecords = ((Number) num).intValue();
			}
			page = PageUtils.createPage(page, totalRecords);

			queryList.setFirstResult(page.getBeginIndex());
			queryList.setMaxResults(page.getEveryPage());
			List content = queryList.list();
			if (content.size() == 0) {
				content = null;
			}
			tr.commit();
			return new PageResult(page, content);
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult getAllByPageAndSql(Page page, String sql,Class clazz) {
		Transaction tr = null;
		Session session = getSession();
		try {
			tr = session.beginTransaction();
			if (sql == null || sql.equals("")) {
				return null;
			}
			String temp = "";
			if (sql.toUpperCase().indexOf("SELECT") != -1) {
				int i = sql.toUpperCase().indexOf("FROM");
				temp = "Select count(*) " + sql.substring(i, sql.length());
			} else {
				temp = "Select count(*) " + sql;
			}

			int j = temp.toUpperCase().lastIndexOf("ORDER");
			if (j != -1) {
				temp = temp.substring(0, j).trim();
			}
			int totalRecords = 0;
			Query query = session.createSQLQuery(temp);
			Query queryList = session.createSQLQuery(sql).addEntity(clazz);

			Object num = query.uniqueResult();
			if (num instanceof Number) {
				totalRecords = ((Number) num).intValue();
			}
			page = PageUtils.createPage(page, totalRecords);

			queryList.setFirstResult(page.getBeginIndex());
			queryList.setMaxResults(page.getEveryPage());
			List content = queryList.list();
			if (content.size() == 0) {
				content = null;
			}
			tr.commit();
			return new PageResult(page, content);
		} catch (Exception ex) {
			tr.rollback();
			ex.printStackTrace();
			return null;
		}
	}
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

}