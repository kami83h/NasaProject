package com.nasaproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Configures the Database
 * @author Kami
 *
 */
public class JDBCDatabase {

	private  Connection connection = null;

	public JDBCDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/company";

			this.connection = DriverManager.getConnection(url,"root","");
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.connection;
	}
}
