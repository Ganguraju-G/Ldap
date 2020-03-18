package com.example.demo.Dao;

import org.springframework.http.ResponseEntity;

import com.example.demo.entry.Users;
import com.example.demo.view.PersonVo;

public interface RepoAdditional {

	String  resetPassword(Users p,String currentpassword);
	public String unlockUser(Users pvo);
	public ResponseEntity<Object>  auth(String uid,String password);
	String emailSender(Users user);
	String deleteUser(Users p);


}
