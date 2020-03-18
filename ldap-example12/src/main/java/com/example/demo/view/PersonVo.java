package com.example.demo.view;

import java.util.List;

import org.springframework.ldap.odm.annotations.Attribute;

public class PersonVo
{


	private String dn;
	
	private String fullname;
	
	private String lastname;
	
	private String givenname;
	
	private String description;
	
	private String mail;
	
	private String uid;
	
	private String currentpassword;
	
	private String userpassword;
	
	private String pwdAccountLockedTime;
	
	private String group;
	
	private String randomPassword;
	
	
	
	
	public String getRandomPassword() {
		return randomPassword;
	}

	public void setRandomPassword(String randomPassword) {
		this.randomPassword = randomPassword;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	

	
	public String getCurrentpassword() {
		return currentpassword;
	}

	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}

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

	public long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	private long telephoneNumber;

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
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

	@Override
	public String toString() {
		return "PersonVo [dn=" + dn + ", fullname=" + fullname + ", lastname=" + lastname + ", givenname=" + givenname
				+ ", description=" + description + ", mail=" + mail + ", uid=" + uid + ", currentpassword="
				+ currentpassword + ", userpassword=" + userpassword + ", pwdAccountLockedTime=" + pwdAccountLockedTime
				+ ", group=" + group + ", randomPassword=" + randomPassword + ", telephoneNumber=" + telephoneNumber
				+ "]";
	}

	
	
	

}
