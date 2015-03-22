package com.usernet.product.tag;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


import com.usernet.product.entity.Auth;
import com.usernet.product.entity.Channel;
import com.usernet.product.entity.Role;

public class CheckAuthTag extends TagSupport{
	private static final long serialVersionUID = 1L;

	protected String auth_code;

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	@Override
	public int doStartTag() throws JspException {
		Channel channel = (Channel) this.pageContext.getSession().getAttribute("admin");
		Role role = channel.getRole();
		if(role==null||StringUtils.isEmpty(auth_code)){
			return 0;
		}
		if(checkRoleAuth(role, auth_code)){
			return EVAL_BODY_INCLUDE;
		}
		return 0;
	}
	
	protected boolean checkRoleAuth(Role role,String auth_code){
		Set<Auth> list =  (Set<Auth>) role.getAuth();
		if(list==null) return false;
		for(Auth a:list){
			if(auth_code.equals(a.getAuth_code())){
				return true;
			}
		}
		return false;
	}
}
