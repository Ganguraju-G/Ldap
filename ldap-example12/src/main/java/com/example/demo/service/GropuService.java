package com.example.demo.service;

import java.util.List;

import com.example.demo.entry.Group;
import com.example.demo.view.GroupView;
import com.example.demo.view.UserView;

public interface GropuService {
	public List<String> getGroupNames() ;
	public String getGroup();
	public Group userGroup(String cn);
	public String resetPassword(UserView user);

}
