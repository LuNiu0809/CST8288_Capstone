package com.algonquin.Capstone.beans;

/*
 *`ID` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  `FirstName` VARCHAR(45) NULL,
  `RoleName` VARCHAR(45) NULL,
  `AccountVerified` TINYINT NULL,
 * */

public class User {
	
	private int id;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String roleName;
	
	private boolean accountVerified;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	public User(int id, String userName, String password, String email, String firstName, String lastName,
			String roleName, boolean accountVerified) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleName = roleName;
		this.accountVerified = accountVerified;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isAccountVerified() {
		return accountVerified;
	}

	public void setAccountVerified(boolean accountVerified) {
		this.accountVerified = accountVerified;
	}
	
	

}
