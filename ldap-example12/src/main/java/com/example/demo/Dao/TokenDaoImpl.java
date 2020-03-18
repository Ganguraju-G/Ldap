package com.example.demo.Dao;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserBean;

@Repository
public class TokenDaoImpl implements TokenDao {


	@Autowired
	EntityManager em;
	
	@Override
	public void addToken(UserBean utoken) {
		Session session = em.unwrap(Session.class);
		session.saveOrUpdate(utoken);
		
	}


	@Override
	public UserBean getToken(String user) {
		Session session = em.unwrap(Session.class);
		UserBean ut = session.get(UserBean.class, user);
		return ut;
	}


	@Override
	public void deleateToken(UserBean usertoken) {
		Session session = em.unwrap(Session.class);
		session.delete(usertoken);
		
	}


	@Override
	public void updateToken(String token, String user) {
		Session session = em.unwrap(Session.class);
		Query query = session.createQuery("update UserBean u set u.token=:token where u.fullName=:user");
		query.setParameter("token", token);
		query.setParameter("user", user);
		query.executeUpdate();
		

	}


	

}
