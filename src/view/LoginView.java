package view;

import java.util.Scanner;

public class LoginView {

	private Scanner scan;
	
	public LoginView(Scanner scan) {
		this.scan = scan;
	}

	public String[] showLoginView() {
		MainView.topView();

		System.out.print("\nUsername: ");
		String username = scan.nextLine();

		if (username.equals("exit")) {
			return new String[] {};
		}
		
		System.out.print("Password: ");
		String password = scan.nextLine();
		
		return new String[] { username, password };
	}

	public void invalidCredentialsView() {
		System.out.println("\nIncorrect credentials! Press Enter to continue...");
		scan.nextLine();
	}

	public void sucessfullyLoginView() {
		System.out.println("\nLogin successfully!");
	}
}
