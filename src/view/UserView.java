package view;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class UserView extends GenericView {

	public Map<String, String> getUserCredentials() throws NoSuchElementException {
		showSystemInfo();

		Map<String, String> userCredentials = new HashMap<String, String>();

		try {
			userCredentials.put("username", getUserInput("\nUsuário"));
			userCredentials.put("password", getUserPasswordInput("Senha"));
		} catch (NoSuchElementException e) {
			throw e;
		}

		return userCredentials;
	}

	public Map<String, String> getUserData() {
		showSystemInfo();

		Map<String, String> userData = new HashMap<String, String>();

		System.out.println("\nCADASTRAR NOVO USUÁRIO");
		userData.put("name", getUserInput("\nNome completo"));
		userData.put("username", getUserInput("Usuário"));
		userData.put("password", getUserPasswordInput("Senha"));

		return userData;
	}

}
