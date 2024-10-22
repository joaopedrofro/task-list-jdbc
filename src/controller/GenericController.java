package controller;

import java.sql.Connection;

import db.Database;
import db.exceptions.DatabaseConnectionException;

public abstract class GenericController {

	protected static Connection conn;
	
	public GenericController() {
		try {
			conn = Database.getConnection();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
	}
	
}
