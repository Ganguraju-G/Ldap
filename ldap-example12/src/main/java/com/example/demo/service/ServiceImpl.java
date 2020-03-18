package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.Dao.Repo;
import com.example.demo.entry.Users;
import com.example.demo.view.PersonVo;



@Component
public class ServiceImpl implements Service {

	@Autowired
	private Repo repo;
	
	@Autowired
	private  LdapTemplate ldapTemplate;
	

	public int pwdFailureLimit;
	private static final Object None = null;


	
	@Override
	@Transactional	
	public String resetPassword(PersonVo pvo) {

		Users p = new Users();
		p.setFullname(pvo.getFullname());
		p.setUserpassword(pvo.getUserpassword());
		return repo.resetPassword(p,pvo.getCurrentpassword());
		
	}

	@Override
	public String unlockUser(PersonVo pvo) {
		
		System.out.println("this is unlock user service class");
		Users p = new Users();
		p.setFullname(pvo.getFullname());
		p.setUserpassword(pvo.getUserpassword());
		return repo.unlockUser(p);
	}

	
	@Override
	@Transactional	
	public ResponseEntity<Object> auth(String uid, String password) {
		System.out.println("this is auth service class");
		return 	repo.auth(uid, password);

	}

	

	@Override
	@Transactional
	public String deleteUser(PersonVo pvo) {
		// TODO Auto-generated method stub
		Users p = new Users();
		p.setFullname(pvo.getFullname());
		return repo.deleteUser(p);
	}

	
	

	
	
	
	

}
