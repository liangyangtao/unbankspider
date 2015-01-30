package com.unbank.duplicate.entity;

import java.util.Set;

public class SimBean {
	private int id;
	private String title;
	private Set<String> text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<String> getText() {
		return text;
	}
	public void setText(Set<String> text) {
		this.text = text;
	}
	

}
