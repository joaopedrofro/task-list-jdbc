package view.exceptions;

public class InvalidInputData extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidInputData() {
		super();
	}
	
	public InvalidInputData(String msg) {
		super(msg);
	}
	
}
