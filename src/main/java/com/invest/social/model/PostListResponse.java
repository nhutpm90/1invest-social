package com.invest.social.model;


public class PostListResponse extends ResponseWrapper {
	private PostList payload;

	public PostList getPayload() {
		return payload;
	}

	public void setPayload(PostList payload) {
		this.payload = payload;
	}
}
