package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.exceptions.DatabaseConnectionException;

public class Database {

	private static Connection conn = null;

	public static Connection getConnection() throws DatabaseConnectionException {
		if (conn == null) {
			String userHomePath = System.getProperty("user.home");
			String url = "jdbc:sqlite:" + userHomePath + File.separator + "taskapp.db";
			
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
				throw new DatabaseConnectionException("Error trying to connect to the database", e);
			}
			
			return conn;
		} else {
			return conn;
		}
	}
	
	public static void closeConnection() throws DatabaseConnectionException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error trying to close to the database connection", e);
		}
	}

}
