package com.usernet.product.entity;

import java.sql.Timestamp;



/**
 * 管理员操作记录
 * 
 * @author Administrator
 */
public class AdminLog implements java.io.Serializable {
	
	private static final long serialVersionUID = -957390425393619359L;

	private Integer id;
	private String username; // 用户名即登录名
	private String actions; // 操作说明
	private String ip; // 登录IP
	private Timestamp time;// 操作时间
	
	public AdminLog(String username,String actions,String ip,Timestamp time){		
		this.username = username;
		this.actions = actions;
		this.ip = ip;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}