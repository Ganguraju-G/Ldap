package com.example.demo.Dao;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

import com.example.demo.entry.Group;
import com.example.demo.entry.Users;
@Repository 
public class GroupRepository implements GroupRepo,BaseLdapNameAware  {
	
	private final static LdapName ADMIN_USER = LdapUtils.newLdapName("ou=groups,o=testOrg,dc=example,dc=com");
	@Autowired
	private LdapTemplate ldapTemplate;
    private LdapName baseLdapPath;
    
    @Override
	public void setBaseLdapPath(LdapName baseLdapPath) {
		// TODO Auto-generated method stub
		this.baseLdapPath=baseLdapPath;
	}
    
	@Override
	public String createUser(Group group) {
		group.addMember(LdapUtils.prepend(ADMIN_USER, baseLdapPath));
		ldapTemplate.create(group);
		return "new User created"+" "+group.getName();
	
}
	
	@Override
	public List<String> getGroupNames() {
		// TODO Auto-generated method stub
		 LdapQuery query = query().attributes("cn")

	                .where("objectclass").is("groupOfNames");
		System.out.println("get group names");
		return ldapTemplate.search(query, new AttributesMapper<String>() {

            @Override

            public String mapFromAttributes(Attributes attributes) throws NamingException, javax.naming.NamingException {

                return (String) attributes.get("cn").get();

            }

        });
		
	}
	@Override
	public String getUserGroup() {
		// TODO Auto-generated method stub
		 LdapQuery query = query().where("cn").is("testGroup1");
		 Group g=ldapTemplate.findOne(query, Group.class);
		System.out.println("gets group name"+g);
		 
	
		return null;
	}
	
	@Override
	public Group userGroup(String cn) {
		// TODO Auto-generated method stub
		 LdapQuery query = query().where("cn").is(cn);

			Group g=ldapTemplate.findOne(query, Group.class);
			System.out.println("group is"+g);

		return g;
	}
	@Override
	public String resetPassword(String fullname,String password) {
		// TODO Auto-generated method stub
		 LdapQuery query = query().where("cn").is(fullname);
			//Users g=ldapTemplate.findOne(query, Users.class);
			//g.setPassword(password);
			System.out.println("users updated is"+ldapTemplate.findOne(query, Users.class));
			//ldapTemplate.update(g);
			
		return "password reseted";
	}
	@Override
	public Users getUsers(String cn) {
		 LdapQuery query = query().where("cn").is("venkata");
		return null;
	}

	@Override
	public void deleteMemberFromGroup(Users pp, Group group) {
		System.out.println("before deleting : "+group);
		ArrayList<Name>g=group.removeMember(pp);
		group.setMembers(g);
		System.out.println("after  deleting : "+group);
		ldapTemplate.update(group);
	}
	
	
	
}
