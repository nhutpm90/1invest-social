package com.invest.social.model;

public class RegisterResponse extends ResponseWrapper {
	private PayloadRegister payload;

	public PayloadRegister getPayload() {
		return payload;
	}

	public void setPayload(PayloadRegister payload) {
		this.payload = payload;
	}
}
