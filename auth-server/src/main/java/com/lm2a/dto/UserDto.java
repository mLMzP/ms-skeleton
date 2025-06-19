package com.lm2a.dto;

public class UserDto {

	private int id;
	private String username;
	private String password;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
