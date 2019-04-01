package com.aems.test;

public class Node {
	private String id;
	private String pid;
	private double value;
	private boolean leaf;
	public Node(String id, String pid) {
		this.id = id;
		this.pid = pid;
		this.leaf = false;
	}
	public Node(String id, String pid, double value) {
		this.id = id;
		this.pid = pid;
		this.value = value;
		this.leaf = false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
}
