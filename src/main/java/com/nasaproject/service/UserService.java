package com.nasaproject.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nasaproject.bean.User;
import com.nasaproject.connection.JDBCDatabase;

/**
 *	A User Service which handles the (Database)-Bean connection.
 * @author Kami Hassanzadeh
 *
 */
public class UserService implements IUserService {

	private JDBCDatabase databaseConnection;
	private PreparedStatement pstmt;
	
	public UserService() {
		this.databaseConnection = new JDBCDatabase();
	}

	@Override
	public User findById(int id) {
//		for(User user: userList) {
//			if(user.getId() == id) {
//				return user;
//			}
//		}
		return null;
	}

	@Override
	public User findByUsername(User user) {
		
		User entity = new User();
		try {
			setPreparedStatement("SELECT * FROM users WHERE username='"+ user.getUsername() +"'AND password='"+user.getPassword()+"';");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				entity.setId(rs.getInt("user_id"));
				entity.setUsername(rs.getString("username"));
				entity.setPassword(rs.getString("password"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return entity;
	}

	@Override
	public void addUser(User user) {

		try {
			String query = " INSERT INTO users (username, password) values (?, ?)";
			setPreparedStatement(query);
			pstmt.setString (1, user.getUsername());
			pstmt.setString (2, user.getPassword());
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			setPreparedStatement("SELECT * FROM users");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return userList;
	}

	public PreparedStatement setPreparedStatement(String input) {
		try {
			pstmt = databaseConnection.getConnection()
					.prepareStatement(input);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;	
	}
}
