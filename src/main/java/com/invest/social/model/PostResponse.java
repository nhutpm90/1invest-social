package com.invest.social.model;

public class PostResponse extends ResponseWrapper {
	private PayloadPost payload;
	public PayloadPost getPayload() {
		return payload;
	}

	public void setPayload(PayloadPost payload) {
		this.payload = payload;
	}
}
