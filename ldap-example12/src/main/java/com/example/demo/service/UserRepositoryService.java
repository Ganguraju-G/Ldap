package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.UserRepository;
import com.example.demo.entry.Group;
import com.example.demo.entry.Users;
import com.example.demo.view.UserView;

@Service
public class UserRepositoryService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public String createUser(UserView user, Group g) {
		Users u = new Users(user.getFullname(),user.getLastname(),user.getGivenname(),user.getDescription(),user.getMail(),user.getTelephoneNumber(),user.getGroup());
		u.setUid(user.getFullname());
		userRepository.addToGroup(u, g);
		String status = userRepository.createUser(u);
		return status + " " + "registred";
	}

	@Override
	public List<String> getUsernames() {
		System.out.println("usernames service");
		return userRepository.getUserNames();
	}

}
