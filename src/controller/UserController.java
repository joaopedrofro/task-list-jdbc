package controller;

import java.util.Base64;
import java.util.Map;
import java.util.NoSuchElementException;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.exceptions.DatabaseQueryException;
import model.entities.User;
import services.AuthenticationService;
import services.PasswordHashingService;
import view.UserView;

public class UserController extends GenericController {

	private UserDao userDao;

	public UserController() {
		userDao = DaoFactory.getUserDao(conn);
	}

	public void userLogin() {
		UserView userView = new UserView();
		AuthenticationService auth = new AuthenticationService(userDao);

		while (true) {
			Map<String, String> userCredentials = userView.getUserCredentials();

			if (userCredentials.get("username").isEmpty()) {
				break;
			}

			try {
				if (auth.authenticate(userCredentials.get("username"), userCredentials.get("password"))) {
					TaskController taskController = new TaskController(auth.getUserAuthenticated());
					taskController.displayUserTasksMenu();
					auth.logoutUserAuthenticated();
					break;
				} else {
					UserView.showInfoMessage("Usuário ou senha inválidos");
				}
			} catch (DatabaseQueryException e) {
				UserView.showInfoMessage("Erro ao tentar autenticar o usuário: " + e.getLocalizedMessage());
			}
		}
	}

	public void userRegister() throws NoSuchElementException {
		UserView userView = new UserView();

		while (true) {
			Map<String, String> userData = userView.getUserData();

			if (userData.values().stream().allMatch(v -> v.isEmpty())) {
				break;
			}

			if (userData.values().stream().anyMatch(v -> v.isEmpty() || v.isBlank())) {
				UserView.showInfoMessage("Os dados não podem ser vazios ou em branco");
				continue;
			}

			try {
				if (userDao.getUserByName(userData.get("username")).isPresent()) {
					UserView.showInfoMessage("Nome de usuário já em uso. Por favor, escolha outro");
				} else {
					byte[] salt = PasswordHashingService.getSalt();
					String hashPassword = PasswordHashingService.hashPassword(userData.get("password"), salt);
					userDao.add(new User(0, userData.get("name"), userData.get("username"), hashPassword,
							Base64.getEncoder().encodeToString(salt)));
					UserView.showInfoMessage("Registro feito");
					break;
				}
			} catch (DatabaseQueryException e) {
				UserView.showInfoMessage("Error ao tentar registrar usuário: " + e.getMessage());
			}
		}
	}

}
