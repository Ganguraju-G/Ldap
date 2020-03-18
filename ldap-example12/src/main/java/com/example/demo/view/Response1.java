package com.example.demo.view;

public class Response1
{

	private boolean authentication;
	private int pwdFailureLimit;
	private String Group;	
	private String token;
	private String status;
	
	
	
	public String getGroup() {
		return Group;
	}
	public void setGroup(String group) {
		Group = group;
	}
	public boolean getAuthentication() {
		return authentication;
	}
	public void setAuthentication(boolean authentication) {
		this.authentication = authentication;
	}
	public int getPwdFailureLimit() {
		return pwdFailureLimit;
	}
	public void setPwdFailureLimit(int pwdFailureLimit) {
		this.pwdFailureLimit = pwdFailureLimit;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Response1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Response1(boolean authentication, int pwdFailureLimit, String group, String token) {
		super();
		this.authentication = authentication;
		this.pwdFailureLimit = pwdFailureLimit;
		Group = group;
		this.token = token;
	}
	@Override
	public String toString() {
		return "Response1 [authentication=" + authentication + ", pwdFailureLimit=" + pwdFailureLimit + ", Group="
				+ Group + ", token=" + token + ", status=" + status + "]";
	}
	

	
	
}
