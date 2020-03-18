package com.example.demo.Dao;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.directory.Attributes;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserBean;
import com.example.demo.entry.Group;
import com.example.demo.entry.Users;
@Repository
public class UserRepository implements UserRepo{
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Repo repo;
	
	@Override
	public String createUser(Users user) {
		System.out.println("this is user"+user);
		repo.emailSender(user);
		ldapTemplate.create(user);
		Session session = entityManager.unwrap(Session.class);
		UserBean ub = new UserBean();
		ub.setFullName(user.getFullname());
		ub.setStatus("Registered");
		ub.setPwdAttemptsLeft(5);
		session.save(ub);
		return "new user"+" "+user.getFullname() + " "+"password was sent to " + user.getMail();
	}
	
	@Override
	public List<String> getUserNames() {
		// TODO Auto-generated method stub
		System.out.println("getting usernames");
		 LdapQuery query = query().attributes("cn")

	                .where("objectclass").is("inetOrgPerson");
		 LdapQuery query1 = query().where("cn").is("venkata");
		 System.out.println("user is"+ldapTemplate.findOne(query1, Users.class));
		System.out.println("get group names");
		return ldapTemplate.search(query, new AttributesMapper<String>() {
         @Override

         public String mapFromAttributes(Attributes attributes) throws NamingException, javax.naming.NamingException {

             return (String) attributes.get("cn").get();

         }

     });

		
	}
	@Override
	public String addToGroup(Users u, Group g) {
		// TODO Auto-generated method stub
		g.addMember(u.getDn());
		ldapTemplate.update(g);
		System.out.println("added to group"+g);
		return "add to group";
	}
}
