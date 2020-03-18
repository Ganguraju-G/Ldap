package com.example.demo.service;

import java.util.List;

import com.example.demo.entry.Group;
import com.example.demo.view.UserView;

public interface UserService {
	public String createUser(UserView user,Group g);
	public List<String> getUsernames();

}
