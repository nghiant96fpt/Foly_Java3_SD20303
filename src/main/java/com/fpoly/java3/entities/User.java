package com.fpoly.java3.entities;

import java.util.Date;

public class User {
	private String email;
	private String password;
	private String name;
	private String phone;
	private boolean gender;
	private Date birthDay;
	private int role;

	public User() {
	}

	public User(String email, String password, String name, String phone, boolean gender, Date birthDay, int role) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birthDay = birthDay;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
