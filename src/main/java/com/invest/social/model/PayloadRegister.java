package com.invest.social.model;

public class PayloadRegister {
	private String guid;
	private String username;
	private String fullname;
	private icon icon;
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public icon getIcon() {
		return icon;
	}
	public void setIcon(icon icon) {
		this.icon = icon;
	}
}
