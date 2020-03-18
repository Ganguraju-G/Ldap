package com.example.demo.view;

import java.util.List;

public class GroupView {
	   private String name;
	   private List<UserView> members;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UserView> getMembers() {
		return members;
	}
	public void setMembers(List<UserView> members) {
		this.members = members;
	}
	@Override
	public String toString() {
		return "GroupView [name=" + name + ", members=" + members + "]";
	}
}
