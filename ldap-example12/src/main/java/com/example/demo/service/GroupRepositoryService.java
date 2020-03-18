package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Dao.GroupRepository;
import com.example.demo.entry.Group;
import com.example.demo.entry.Users;
import com.example.demo.view.GroupView;
import com.example.demo.view.UserView;
@Component
public class GroupRepositoryService implements GropuService{
	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public List<String> getGroupNames() {
		// TODO Auto-generated method stub
		System.out.println("groupnames service");
		return groupRepository.getGroupNames();
	}
	@Override
	public String getGroup() {
		// TODO Auto-generated method stub
		System.out.println("service");
		groupRepository.getUserGroup();
		return null;
	}
	@Override
	public Group userGroup(String cn) {
		// TODO Auto-generated method stub
		System.out.println("service for userGroup");
		return groupRepository.userGroup(cn);
	}
	
	@Override
	public String resetPassword(UserView user) {
		
		System.out.println("service for reset password");
		return groupRepository.resetPassword(user.getFullname(),user.getPassword());
	}
		
}
