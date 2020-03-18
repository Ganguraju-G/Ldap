package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entry.Users;
import com.example.demo.view.PersonVo;

public interface Service 
{
	String  resetPassword(PersonVo p);
	
	String unlockUser(PersonVo p);
	
	public ResponseEntity<Object>  auth(String uid,String password);

	String deleteUser(PersonVo p);


}
