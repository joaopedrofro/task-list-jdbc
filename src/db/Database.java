package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			Properties props = getProperties("src/db.properties");
			String url = props.getProperty("url");
			
			try {
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		} else {
			return conn;
		}
	}

	private static Properties getProperties(String filename) {
		Properties props = new Properties();

		try {
			props.load(new FileInputStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props;
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
