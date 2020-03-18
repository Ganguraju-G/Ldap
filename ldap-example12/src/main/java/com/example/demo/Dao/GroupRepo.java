package com.example.demo.Dao;

import java.util.List;

import com.example.demo.entry.Group;
import com.example.demo.entry.Users;
import com.example.demo.entry.Users;

public interface GroupRepo  {
	public String createUser(Group group);
	
	public List<String> getGroupNames();
	
	public String getUserGroup();
	public Group userGroup(String cn);
	public Users getUsers(String cn);
	String resetPassword(String fullname, String password);

	public void deleteMemberFromGroup(Users users, Group group);
}
