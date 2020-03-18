package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_detail")
public class UserBean {

	@Id
	@Column(name="cn")
	private String fullName;

	@Column(name="pwdAttemptsLeft")
	private int pwdAttemptsLeft;
	
	@Column(name="token")
	private String token;
	
	@Column(name="status")
	private String status; 
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPwdAttemptsLeft() {
		return pwdAttemptsLeft;
	}

	public void setPwdAttemptsLeft(int pwdAttemptsLeft) {
		this.pwdAttemptsLeft = pwdAttemptsLeft;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public UserBean(String fullName, int pwdAttemptsLeft, String token, String status) {
		super();
		this.fullName = fullName;
		this.pwdAttemptsLeft = pwdAttemptsLeft;
		this.token = token;
		this.status = status;	
	}

	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserBean [fullName=" + fullName + ", pwdAttemptsLeft=" + pwdAttemptsLeft + ", token=" + token
				+ ", status=" + status +  "]";
	}

	

	
	
}
