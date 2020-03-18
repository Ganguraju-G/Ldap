package com.example.demo.Dao;

import com.example.demo.entity.UserBean;

public interface TokenDao {

	public void addToken(UserBean utoken);

	public UserBean getToken(String user);

	public void deleateToken(UserBean usertoken);

	public void updateToken(String token1, String user);
}
