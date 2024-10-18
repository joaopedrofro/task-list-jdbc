package controller;

import java.sql.Connection;

import db.Database;

public abstract class GenericController {

	protected static Connection conn;
	
	public GenericController() {
		conn = Database.getConnection();
	}
	
}
