package view;

public class UserView extends GenericView {

	public String[] showUserLoginView() {
		showSystemInfo();

		System.out.print("\nUsername: ");
		String username = scanner.nextLine();

		if (username.equals("exit")) {
			return new String[] {};
		}

		System.out.print("Password: ");
		String password = scanner.nextLine();

		return new String[] { username, password };
	}

	public String[] showUserRegisterView() {
		showSystemInfo();

		System.out.println("\nRegister new user");

		System.out.print("\nName: ");
		String name = scanner.nextLine();

		System.out.print("Username: ");
		String username = scanner.nextLine();

		System.out.print("Password: ");
		String password = scanner.nextLine();

		return new String[] { name, username, password };
	}
	
}
