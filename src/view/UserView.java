package view;

import java.util.Scanner;

public class UserView {

	private Scanner scan;

	public UserView(Scanner scan) {
		this.scan = scan;
	}

	public String[] showUserLoginView() {
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

	public String[] showUserRegisterView() {
		MainView.topView();

		System.out.println("\nRegister new user");

		System.out.print("\nName: ");
		String name = scan.nextLine();

		System.out.print("Username: ");
		String username = scan.nextLine();

		System.out.print("Password: ");
		String password = scan.nextLine();

		return new String[] { name, username, password };
	}

	public void invalidCredentialsView() {
		System.out.println("\nIncorrect credentials! Press Enter to continue...");
		scan.nextLine();
	}

	public void successfullyLoginView() {
		System.out.println("\nLogin successfully! Press Enter to continue...");
		scan.nextLine();
	}
	
	public void successfullyRegisterView() {
		System.out.println("\nRegistred successfully! Press Enter to continue...");
		scan.nextLine();
	}
	
	public void userAlreadyExistsView() {
		System.out.println("\nUsername already in use! Press Enter to continue...");
		scan.nextLine();
	}
}
