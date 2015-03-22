package com.usernet.product.entity;



public class Music implements java.io.Serializable {
	private static final long serialVersionUID = 4516014800563330612L;

	private Integer id;
	private String time;
	private String vol;
	private String coversrc;
	private String name;
	private String author;
	private String musicsrc;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getCoversrc() {
		return coversrc;
	}
	public void setCoversrc(String coversrc) {
		this.coversrc = coversrc;
	}
	public String getMusicsrc() {
		return musicsrc;
	}
	public void setMusicsrc(String musicsrc) {
		this.musicsrc = musicsrc;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}