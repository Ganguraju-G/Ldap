package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.TokenService;
import com.example.demo.view.Response1;


@RestController
public class ApplicationController {

	@Autowired
    private TokenService tservice;
	

	
	@PostMapping("/loginToken")
	public Response1 login(@RequestBody User user) {
		
		Response1 tokenview = tservice.checkAndGenToken(user);

	   return tokenview;

	}
}


