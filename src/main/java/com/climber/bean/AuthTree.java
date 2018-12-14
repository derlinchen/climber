package com.climber.bean;

import java.util.List;

public class AuthTree {

	private String id;

	private boolean expanded;

	private List<AuthMenu> children;

	public AuthTree() {
		this.expanded = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<AuthMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AuthMenu> children) {
		this.children = children;
	}

}
