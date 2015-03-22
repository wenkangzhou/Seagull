package com.usernet.product.entity;


public class Tao implements java.io.Serializable {
	private static final long serialVersionUID = 4516014800563330612L;

	private Integer id;
	private String time;
	private String vol;
	private String coversrc;
	private String websrc;
	private String etc;
	private String operator;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}