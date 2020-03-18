package com.example.demo.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.Repo;
import com.example.demo.Dao.TokenDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserBean;
import com.example.demo.filter.JwtUtil;
import com.example.demo.view.Response1;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDao dao;
	
	@Autowired
	private Repo repo;
	
	@Autowired
	private JwtUtil jwtTokenUtil;


	@Override
	@Transactional
	public Response1 checkAndGenToken(User user) {
		
		ResponseEntity<Object> responseObj = repo.auth(user.getUsername(), user.getPassword());
		System.out.println("this is a flag"+responseObj.getBody());
		Response1 response = (Response1) responseObj.getBody();
		System.out.println("this is response "+response.getAuthentication());
		boolean flag = response.getAuthentication();
		
		if(flag){
			
			UserBean usertoken =	dao.getToken(user.getUsername());
			
			if(usertoken==null) {
				final String token = jwtTokenUtil.generateToken(user);
				
				UserBean utoken = new UserBean();
				utoken.setToken(token);
		         utoken.setFullName(user.getUsername());
		         dao.addToken(utoken);
		         
		         return response;
		         
			}
			else {
				
				
				String token = usertoken.getToken();
			
				
				try {
					
					
					if(!jwtTokenUtil.isTokenExpired(token)) {
						
						response.setToken(token);
//						tokenview.setToken(usertoken.getToken());
//						tokenview.setUser(usertoken.getFullName());
						
						return response;
					}
			
					
				}
				catch(Exception e) {
					
					
					final String token1 = jwtTokenUtil.generateToken(user);
					
					dao.updateToken(token1,user.getUsername());
					response.setToken(token1);
//			         tokenview.setToken(token1);
//			         tokenview.setUser(user.getUsername());
			         return response;
			         
					
				}
			}
	
		}
		
		else {
			return response;
			//throw new RuntimeException("Login Credentials are wrong!");
		}
		return response;

	}
	

	@Override
	@Transactional
	public UserBean getToken(String username) {

		return dao.getToken(username);
	}


//	@Override
//	@Transactional
//	public UserDetails getToken(String username) throws UsernameNotFoundException {
//		
//		 UserToken ut = dao.getToken(username);
//		 return new User("","",
//				 new ArrayList<>()));
//	}

	



}
