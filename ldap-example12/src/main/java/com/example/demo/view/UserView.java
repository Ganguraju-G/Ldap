package com.example.demo.view;

public class UserView {
	private String fullname;
	private String lastname;
	private String givenname;
	private String description;
	private String mail;
	private String group;
	private String password;
	private long telephoneNumber;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserView() {
	}

	public UserView(String fullname, String lastname, String givenname, String description, String mail, String group,
			String password, long telephoneNumber) {
		super();
		this.fullname = fullname;
		this.lastname = lastname;
		this.givenname = givenname;
		this.description = description;
		this.mail = mail;
		this.group = group;
		this.password = password;
		this.telephoneNumber = telephoneNumber;
	}

	public UserView(String fullname, String lastname, String givenname, String description, String mail, String group,
			String password) {
		super();
		this.fullname = fullname;
		this.lastname = lastname;
		this.givenname = givenname;
		this.description = description;
		this.mail = mail;
		this.group = group;
		this.password = password;
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

	public long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "UserView [fullname=" + fullname + ", lastname=" + lastname + ", givenname=" + givenname
				+ ", description=" + description + ", mail=" + mail + ", group=" + group + "]";
	}

}
