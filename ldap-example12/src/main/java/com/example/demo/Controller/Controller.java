package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entry.Users;
import com.example.demo.service.Service;
import com.example.demo.view.MessageResponseView;
import com.example.demo.view.PersonVo;
import com.example.demo.view.UserView;


@RestController
@RequestMapping("/ldap")
public class Controller {
	
	@Autowired
	private Service service;
	
	

	@PutMapping("/reset")
	public MessageResponseView resetPassword(@RequestBody PersonVo p)
	{
		System.out.println("this is object"+p);
		MessageResponseView msg = new MessageResponseView();
		msg.setMessage(service.resetPassword(p));
		msg.setTimeStamp(System.currentTimeMillis());
		return msg;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Object> auth(@RequestBody Users user) {
			System.out.println("authentating....."+user);
			System.out.println("controller"+user.getFullname()+ user.getUserpassword());
		return service.auth(user.getFullname(), user.getUserpassword());	 	
	}
	
		

	
	@DeleteMapping("/deleteUser")
	private MessageResponseView deleteUser(@RequestBody PersonVo p)
	{
		MessageResponseView msg = new MessageResponseView();
		msg.setMessage(service.deleteUser(p));
		msg.setTimeStamp(System.currentTimeMillis());
		return msg;
	}
}
