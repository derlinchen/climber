package com.climber.bean;

import java.util.ArrayList;
import java.util.List;

public class SysMenu extends BaseBean {

	private String id;

	private String pid;

	private String text;

	private boolean leaf;

	private String cls;

	private String module;

	private String url;

	private boolean expanded;

	private List<SysMenu> children;

	private int action;

	public SysMenu() {
		this.id = "";
		this.pid = "";
		this.text = "";
		this.leaf = false;
		this.cls = "";
		this.module = "";
		this.url = "";
		this.expanded = false;
		this.children = new ArrayList<SysMenu>();
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
