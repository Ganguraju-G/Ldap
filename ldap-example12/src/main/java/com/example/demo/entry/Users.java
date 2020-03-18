package com.example.demo.entry;

import java.util.Arrays;
import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

@Entry(objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top" })
public class Users {
	@Id
	private Name dn;
	@Attribute(name = "cn")
	private String fullname;
	@Attribute(name = "sn")
	private String lastname;
	@Attribute(name = "givenname")
	private String givenname;
	@Attribute(name = "description")
	private String description;
	@Attribute(name = "mail")
	private String mail;
	@Attribute(name = "uid")
	private String uid;
	@Attribute(name = "telephoneNumber")
	private long telephoneNumber;
	@Attribute(name = "userpassword")
	private String userpassword;
	@Attribute(name = "pwdAccountLockedTime")
	private String pwdAccountLockedTime;
	@Attribute(name = "ou")
	private String group;

	private static final String base_dn = "ou=users,o=testOrg,dc=example,dc=com";

	public String getPwdAccountLockedTime() {
		return pwdAccountLockedTime;
	}

	public void setPwdAccountLockedTime(String pwdAccountLockedTime) {
		this.pwdAccountLockedTime = pwdAccountLockedTime;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	

	public Users(String fullname, String lastname, String givenname, String description, String mail,
			long telephoneNumber, String group) {
		super();
		
		dn = LdapNameBuilder.newInstance(base_dn).add("cn", fullname).build();
		this.fullname = fullname;
		this.lastname = lastname;
		this.givenname = givenname;
		this.description = description;
		this.mail = mail;
		this.telephoneNumber = telephoneNumber;
		this.group = group;
	}

	public Users(Name dn, String fullname, String lastname, String givenname, String description, String mail,
			String uid, long telephoneNumber, String userpassword, String pwdAccountLockedTime, String group) {
		super();
	//	dn = LdapNameBuilder.newInstance(base_dn).add("cn", fullname).build();
		this.dn = dn;
		this.fullname = fullname;
		this.lastname = lastname;
		this.givenname = givenname;
		this.description = description;
		this.mail = mail;
		this.uid = uid;
		this.telephoneNumber = telephoneNumber;
		this.userpassword = userpassword;
		this.pwdAccountLockedTime = pwdAccountLockedTime;
		this.group = group;
	}

	@Override
	public String toString() {
		return "Person [dn=" + dn + ", fullname=" + fullname + ", lastname=" + lastname + ", givenname=" + givenname
				+ ", description=" + description + ", mail=" + mail + ", uid=" + uid + ", telephoneNumber="
				+ telephoneNumber + ", userpassword=" + userpassword + ", pwdAccountLockedTime=" + pwdAccountLockedTime
				+ ", group=" + group + "]";
	}

}
