package com.algonquin.Capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algonquin.Capstone.beans.User;

public class UserDao {

	private Connection db;

	public UserDao() {
		// TODO Auto-generated constructor stub
		db = DBConnection.getConnectionToDatabase();
	}

	public boolean createUser(User user) {
		try {
			String query = "INSERT INTO user (UserName, Password, Email, LastName, FirstName, RoleName, AccountVerified) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getFirstName());
			statement.setString(6, user.getRoleName());
			statement.setBoolean(7, user.isAccountVerified());
			int rowsAffected = statement.executeUpdate();
			statement.close();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public User getUserByUsername(String username) {
		try {
			String query = "SELECT * FROM user WHERE UserName = ?";
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int userId = resultSet.getInt("ID");
				String password = resultSet.getString("Password");
				String email = resultSet.getString("Email");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				String roleName = resultSet.getString("RoleName");
				boolean accountVerified = resultSet.getBoolean("AccountVerified");
				User user = new User(userId, username, password, email, firstName, lastName, roleName, accountVerified);
				return user;
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User getUserById(int userId) {
		try {
			String query = "SELECT * FROM user WHERE id = ?";
			PreparedStatement statement = db.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String userName = resultSet.getString("UserName");
				String password = resultSet.getString("Password");
				String email = resultSet.getString("Email");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				String roleName = resultSet.getString("RoleName");
				boolean accountVerified = resultSet.getBoolean("AccountVerified");
				User user = new User(userId, userName, password, email, firstName, lastName, roleName, accountVerified);
				return user;
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			String query = "SELECT * FROM user";
			PreparedStatement statement = db.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int userId = resultSet.getInt("id");
				String userName = resultSet.getString("UserName");
				String password = resultSet.getString("Password");
				String email = resultSet.getString("Email");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				String roleName = resultSet.getString("RoleName");
				boolean accountVerified = resultSet.getBoolean("AccountVerified");
				User user = new User(userId, userName, password, email, firstName, lastName, roleName, accountVerified);
				users.add(user);
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public boolean updateUser(User user) {
		try {
			String query = "UPDATE user SET UserName = ?, Password = ?, Email = ?, LastName = ?, FirstName = ?, "
					+ "RoleName = ?, AccountVerified = ? WHERE id = ?";
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getFirstName());
			statement.setString(6, user.getRoleName());
			statement.setBoolean(7, user.isAccountVerified());
			statement.setInt(8, user.getId());
			int rowsAffected = statement.executeUpdate();
			statement.close();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteUser(int userId) {
		try {
			String query = "DELETE FROM user WHERE id = ?";
			PreparedStatement statement = db.prepareStatement(query);
			statement.setInt(1, userId);
			int rowsAffected = statement.executeUpdate();
			statement.close();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
