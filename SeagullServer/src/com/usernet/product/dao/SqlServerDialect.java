package com.usernet.product.dao;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

public class SqlServerDialect extends SQLServerDialect {

	 public SqlServerDialect() {   
		 
	        super();   
	        registerHibernateType(1, "string");   
	        registerHibernateType(-9,"string");   
	        registerHibernateType(-16, "string");   
	        registerHibernateType(3, "double");
	        
	        registerHibernateType(Types.CHAR,Hibernate.STRING.getName());   
	        registerHibernateType(Types.NVARCHAR,Hibernate.STRING.getName());   
	        registerHibernateType(Types.LONGNVARCHAR, Hibernate.STRING.getName());   
	        registerHibernateType(Types.DECIMAL, Hibernate.DOUBLE.getName());
	    }   
	
}
