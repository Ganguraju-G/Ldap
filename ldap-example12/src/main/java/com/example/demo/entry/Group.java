package com.example.demo.entry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

@Entry(objectClasses = {"top", "groupOfNames"},base = "cn=groups")
public final class Group {
    private static final String base_dn = "dc=example,dc=com";

    @Id
    private Name dn;
    @Attribute(name="cn")
    @DnAttribute("cn")
    private String name;
    @Attribute(name="member")
    private ArrayList<Name> members=new ArrayList<Name>();
    public Group() {	}
	
	public Group(String name, ArrayList memb) {
		super();
		this.name = name;
		this.members = memb;
		Name dn = LdapNameBuilder.newInstance(base_dn)
                .add("ou", "groups")
                .add("cn", name)
                .build();
        this.dn=dn;
	}

	public Name getDn() {
		return dn;
	}
	public void setDn(Name dn) {
		this.dn = dn;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Name> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Name> members) {
		this.members = members;
	}
	public static String getBaseDn() {
		return base_dn;
	}
    
	
	
    public void addMember(Name user) {
    	this.members.add(user);
    }
    public ArrayList<Name> removeMember(Users user) {	
    	for(int i=0;i<members.size();i++) {
    		if(members.get(i).equals(user.getDn())){
    			members.remove(i);
    		}
    	}
    	return this.members;
    }
    
    
    @Override
	public String toString() {
		return "Group [dn=" + dn + ", name=" + name + ", members=" + members + "]";
	}
}
