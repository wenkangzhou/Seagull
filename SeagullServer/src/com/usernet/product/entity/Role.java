package com.usernet.product.entity;

import java.util.Set;

public class Role implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String role_name;
	
	private Integer deleted = 0;
	
	private Integer status = 0;

	private Integer isAuthorize = 0;
	
	private Set<Auth> auth;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Auth> getAuth() {
		return auth;
	}

	public void setAuth(Set<Auth> auth) {
		this.auth = auth;
	}

	public Integer getIsAuthorize() {
		return isAuthorize;
	}

	public void setIsAuthorize(Integer isAuthorize) {
		this.isAuthorize = isAuthorize;
	}
}
