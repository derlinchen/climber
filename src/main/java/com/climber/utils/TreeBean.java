package com.climber.utils;

public class TreeBean {
	private int id;
	private String name;
	private int parentId;
	private int level;
	private boolean leaf;
	
	public TreeBean(int id, String name, int parentId, int level) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", level=" + level + "]";
	}
}
