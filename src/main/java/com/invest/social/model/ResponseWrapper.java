package com.invest.social.model;

public class ResponseWrapper {
	private String merchant;
	private String url;
	private String time_token;
	private String code;
	private String message;
	
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime_token() {
		return time_token;
	}
	public void setTime_token(String time_token) {
		this.time_token = time_token;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
