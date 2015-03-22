package com.usernet.product.entity;

import java.sql.Timestamp;

public class FeedBack implements java.io.Serializable {
	private static final long serialVersionUID = 4516014800563330612L;

	private Integer id;
	private String msgcontent;
	private String contact;
	private Timestamp time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}