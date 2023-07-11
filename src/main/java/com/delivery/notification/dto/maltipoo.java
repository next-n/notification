package com.delivery.notification.dto;

public class maltipoo {
private String[] roleList;
private String content;
public maltipoo(String[] roleList, String content) {
	super();
	this.roleList = roleList;
	this.content = content;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String[] getRoleList() {
	return roleList;
}

public maltipoo(String[] roleList) {
	super();
	this.roleList = roleList;
}

public void setRoleList(String[] roleList) {
	this.roleList = roleList;
}
}
