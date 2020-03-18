package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserBean;
import com.example.demo.view.Response1;

public interface TokenService {

	public Response1 checkAndGenToken(User user);

	public UserBean getToken(String username);

	

}
