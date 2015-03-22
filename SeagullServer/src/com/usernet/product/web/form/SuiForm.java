package com.usernet.product.web.form;

import com.usernet.product.comm.BaseForm;

public class SuiForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String pageNo;
	private String pageSize;
	private Integer id;
	private String time;
	private String vol;
	private String coversrc;
	private String websrc;
	private String etc;
	private String type;
	private String typesrc;
	private String title;
	private String operator;
	private String startDate;
	private String endDate;
	
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVol() {
		return vol;
	}
	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getCoversrc() {
		return coversrc;
	}
	public void setCoversrc(String coversrc) {
		this.coversrc = coversrc;
	}
	public String getWebsrc() {
		return websrc;
	}
	public void setWebsrc(String websrc) {
		this.websrc = websrc;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypesrc() {
		return typesrc;
	}
	public void setTypesrc(String typesrc) {
		this.typesrc = typesrc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
