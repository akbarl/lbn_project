package com.lbn.domain;

public enum UserType {
	USER(1, "ROLE_USER"),
	ADMIN(2, "ROLE_ADMIN"),
	SUPER_ADMIN(3, "ROLE_SADMIN");
	

	private int userTypeId;
	private String role;

	private UserType(int userTypeId, String role) {
		this.userTypeId = userTypeId;
		this.role = role;
	}
	
	public int getUserTypeId() {
		return userTypeId;
	}
	
	public String getRole() {
		return role;
	}
}
