package com.example.demo.view;
public class MessageResponseView
{
	private long timeStamp;
	private String message;
	private Object response;
	
	public MessageResponseView(long timeStamp, String message, Object response) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.response = response;
	}


	public MessageResponseView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}


	@Override
	public String toString() {
		return "MessageResponseView [timeStamp=" + timeStamp + ", message=" + message + ", response=" + response + "]";
	}


	
	
	
	
	
}
