package main;

import java.sql.Connection;

import db.Database;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		Database.closeConnection();
	}

}
