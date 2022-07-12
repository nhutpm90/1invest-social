package com.invest.social.model;


public class UserAuthenticate extends ResponseWrapper {
	private PayloadAu payload;
	private String api_key_token;
	
	public String getApi_key_token() {
		return api_key_token;
	}

	public void setApi_key_token(String api_key_token) {
		this.api_key_token = api_key_token;
	}

	public PayloadAu getPayload() {
		return payload;
	}

	public void setPayload(PayloadAu payload) {
		this.payload = payload;
	}
}
