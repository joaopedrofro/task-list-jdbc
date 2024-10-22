package model.dao.exceptions;

public class DatabaseQueryException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseQueryException(String message) {
		super(message);
	}

	public DatabaseQueryException(String message, Exception error) {
		super(message, error);
	}

}
