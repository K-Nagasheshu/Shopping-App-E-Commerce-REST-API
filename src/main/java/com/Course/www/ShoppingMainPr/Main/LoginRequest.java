package com.Course.www.ShoppingMainPr.Main;

public class LoginRequest {
	
	private String name;
	private String password;
	
	public LoginRequest() {
		super();
	}

	public LoginRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [name=" + name + ", password=" + password + "]";
	}
}
