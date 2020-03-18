package com.example.demo.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.LdapExample1Application;
import com.example.demo.entry.Group;
import com.example.demo.service.GroupRepositoryService;
import com.example.demo.service.Service;
import com.example.demo.service.UserRepositoryService;
import com.example.demo.view.MessageResponseView;
import com.example.demo.view.PersonVo;
import com.example.demo.view.Response;
import com.example.demo.view.UserView;
import com.ldap.ldapcurd.ExceptionHandle.NotFoundException;

@RestController
@RequestMapping("/user")
public class UserRestController {
	private static Logger logger = LogManager.getLogger(LdapExample1Application.class);

	
	@Autowired
	private Service service;
	
	@GetMapping("/u")
	public String create() {
		return "working";
	}
//	@Autowired
//	private GroupRepoAdditional group;
	@Autowired
	private GroupRepositoryService groupRepo;
	@Autowired
	private UserRepositoryService userRepo;
	
//	@Autowired
//	public UserRestController(UserRepositoryService userRepo,GroupRepositoryService groupRepo,GroupRepoAdditional group) {
//		this.groupRepo=groupRepo;
//		this.userRepo=userRepo;
//		this.group=group;
//	}
	@PostMapping(path="/user", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> createUser(@RequestBody UserView user,@RequestParam(required=false, defaultValue="NewUser Creation") String Content )
	{
		System.out.println("this is controller "+user);
		String status;
		Response r=new Response();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Credentials", "true");
	    responseHeaders.set("Response-type", 
	  	      "application/json");
		try {
		Group userGroup = groupRepo.userGroup(user.getGroup()); 
		
		status= userRepo.createUser(user, userGroup);
		  responseHeaders.set("status", 
			      status);
		logger.info(status);
		if(status.length()>=0) {
			logger.error("user not created");
			
		r.setMessage(status);
		r.setStatus(HttpStatus.OK.value());
		r.setTimestamp(System.currentTimeMillis());
		r.setContent(String.format(Content));
		}
		}
		catch (NotFoundException e) {
			new NotFoundException("group is not Nound");
			
		}
		 return ResponseEntity.ok()
			      .headers(responseHeaders)
			      .body(r); 		 
			}
	
	@PutMapping("/unlock")
	public MessageResponseView unlockUser(@RequestBody PersonVo p)
	{
		MessageResponseView msg = new MessageResponseView();
		msg.setMessage(service.unlockUser(p)); 
		msg.setTimeStamp(System.currentTimeMillis());
		return msg;
	}
	
	
}
