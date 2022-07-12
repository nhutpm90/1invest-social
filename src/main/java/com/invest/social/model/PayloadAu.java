package com.invest.social.model;

public class PayloadAu {
	private String guid;
	private String first_name;
	private String last_name;
	private String fullname;
	private String username;
	private String email;
	private String birthdate;
	private String gender;
	private boolean cover_url;
	private String language;
	private icon icon;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isCover_url() {
		return cover_url;
	}
	public void setCover_url(boolean cover_url) {
		this.cover_url = cover_url;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public icon getIcon() {
		return icon;
	}
	public void setIcon(icon icon) {
		this.icon = icon;
	}
}
