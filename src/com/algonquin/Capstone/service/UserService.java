package com.algonquin.Capstone.service;

import com.algonquin.Capstone.beans.User;
import com.algonquin.Capstone.dao.UserDao;

public class UserService {

	private UserDao userDao;

	public UserService() {
		// TODO Auto-generated constructor stub
		userDao = new UserDao();
	}

	public boolean authenticate(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public boolean registerUser(User user) {

		user.setAccountVerified(true);

		boolean success = userDao.createUser(user);

		// Return the success status of user creation
		return success;
	}

}
