package com.example.demo.Dao;

import java.util.List;

import com.example.demo.entry.Group;
import com.example.demo.entry.Users;

public interface UserRepo {
	public String createUser(Users group);
	public List<String> getUserNames();
	public String addToGroup(Users u,Group g);
	
}
