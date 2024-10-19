package view;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import view.exceptions.InvalidInputData;

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

	public Map<String, String> getUserData() throws InvalidInputData, NoSuchElementException {
		showSystemInfo();

		Map<String, String> userData = new HashMap<String, String>();

		try {
			System.out.println("\nCADASTRAR NOVO USUÁRIO");

			userData.put("name", getUserInput("\nNome completo"));
			userData.put("username", getUserInput("Usuário"));
			userData.put("password", getUserPasswordInput("Senha"));

			for (String data : userData.values()) {
				if (data.isEmpty() || data.isBlank()) {
					throw new InvalidInputData("Os dados não podem ser vazios ou conter somente espaços em branco");
				}
			}
		} catch (NoSuchElementException e) {
			throw e;
		}

		return userData;
	}

}
