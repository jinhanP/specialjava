package com.market.member;

public class Admin extends Customer { // 관리자
	private String id = "Admin";
	private String password = "Admin1234";

	public Admin(String name, int phone) {
		super(name, phone);

	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

}
