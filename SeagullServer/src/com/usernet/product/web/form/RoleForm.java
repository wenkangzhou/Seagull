package com.usernet.product.web.form;

import com.usernet.product.comm.BaseForm;
import com.usernet.product.entity.Role;

public class RoleForm extends BaseForm{
	private static final long serialVersionUID = -3969508460010068656L;
	
	private Role role = new Role();

	private Integer[] auth_id;
	
	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setAuth_id(Integer[] auth_id) {
		this.auth_id = auth_id;
	}

	public Integer[] getAuth_id() {
		return auth_id;
	}
}
