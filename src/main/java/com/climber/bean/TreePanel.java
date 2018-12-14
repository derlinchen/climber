package com.climber.bean;

import java.util.List;

public class TreePanel {

	private String id;

	private boolean expanded;

	private List<SysMenu> children;

	public TreePanel() {
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

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

}
