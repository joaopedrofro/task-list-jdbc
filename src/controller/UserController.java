package controller;

import java.util.Map;
import java.util.NoSuchElementException;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import services.AuthenticationService;
import view.UserView;
import view.exceptions.InvalidInputData;

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

			if (auth.authenticate(userCredentials.get("username"), userCredentials.get("password"))) {
				TaskController taskController = new TaskController(auth.getUserAuthenticated());
				taskController.displayUserTasksMenu();
				auth.logoutUserAuthenticated();
				break;
			} else {
				UserView.showInfoMessage("Usuário ou senha inválidos");
			}
		}
	}

	public void userRegister() throws NoSuchElementException {
		UserView userView = new UserView();
		
		boolean running = true;

		while (running) {
			try {
				Map<String, String> userData = userView.getUserData();
				
				if (userData.get("name").isEmpty()) {
					running = false;
					break;
				}
	
				if (userDao.getUserByName(userData.get("username")).isPresent()) {
					UserView.showInfoMessage("Nome de usuário já em uso. Por favor, escolha outro");
				} else {
					userDao.add(new User(0, userData.get("name"), userData.get("username"), userData.get("password")));
					UserView.showInfoMessage("Registro feito");
				}
			} catch (InvalidInputData e) {
				UserView.showInfoMessage(e.getMessage());
			}
		}
	}

}
