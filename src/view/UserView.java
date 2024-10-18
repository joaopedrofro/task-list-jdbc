package view;

import java.util.HashMap;
import java.util.Map;

import view.exceptions.InvalidInputData;

public class UserView extends GenericView {

	public Map<String, String> getUserCredentials() {
		showSystemInfo();
		
		Map<String, String> userCredentials = new HashMap<String, String>();

		System.out.print("\nUsername: ");
		userCredentials.put("username", scanner.nextLine());
		
		System.out.print("Password: ");
		userCredentials.put("password", scanner.nextLine());

		return userCredentials;
	}

	public Map<String, String> getUserData() throws InvalidInputData {
		showSystemInfo();

		Map<String, String> userData = new HashMap<String, String>();

		System.out.println("\nNew user registration:");

		System.out.print("\nFull name: ");
		userData.put("name", scanner.nextLine());

		System.out.print("Username: ");
		userData.put("username", scanner.nextLine());

		System.out.print("Password: ");
		userData.put("password", scanner.nextLine());

		for (String data : userData.values()) {
			if (data.isEmpty() || data.isBlank()) {
				throw new InvalidInputData(
						"You did not provide any of the requested data or only used blank spaces. Please, try again");
			}
		}

		return userData;
	}

}
