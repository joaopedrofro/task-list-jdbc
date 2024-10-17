package view;

import java.util.Scanner;

import view.util.InfoMessage;

public class UserView {

	private Scanner scan;

	public UserView(Scanner scan) {
		this.scan = scan;
	}

	public String[] showUserLoginView() {
		InfoMessage.showSystemInfo();

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
		InfoMessage.showSystemInfo();

		System.out.println("\nRegister new user");

		System.out.print("\nName: ");
		String name = scan.nextLine();

		System.out.print("Username: ");
		String username = scan.nextLine();

		System.out.print("Password: ");
		String password = scan.nextLine();

		return new String[] { name, username, password };
	}
	
}
