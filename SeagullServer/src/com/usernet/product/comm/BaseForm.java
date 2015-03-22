package com.usernet.product.comm;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionForm;

import com.usernet.product.utils.DateConvert;

public class BaseForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	static {
		// 注册日期
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
		ConvertUtils.register(new DateConvert(), java.sql.Timestamp.class);
		ConvertUtils.register(new DateConvert(), java.sql.Date.class);
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
