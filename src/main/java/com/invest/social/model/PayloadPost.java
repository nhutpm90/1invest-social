package com.invest.social.model;

import java.util.List;

public class PayloadPost {
	private Post post;
	private List<String> friends;
	private String text;
	private String location;
	private String image;
	private User user;
	private User posted_user;
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getText() {
		return text;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getPosted_user() {
		return posted_user;
	}
	public void setPosted_user(User posted_user) {
		this.posted_user = posted_user;
	}
}
