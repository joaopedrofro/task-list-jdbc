package controller;

import java.util.Map;

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

			if (auth.authenticate(userCredentials.get("username"), userCredentials.get("password"))) {
				TaskController taskController = new TaskController(auth.getUserAuthenticated());
				taskController.displayUserTasksMenu();
				break;
			} else {
				UserView.showInfoMessage("Invalid credentials");
			}

		}

		auth.logoutUserAuthenticated();
	}

	public void userRegister() {
		UserView userView = new UserView();

		try {
			Map<String, String> userData = userView.getUserData();

			if (userDao.getUserByName(userData.get("username")).isPresent()) {
				UserView.showInfoMessage("Username already in use. Please, choose another");
			} else {
				userDao.add(new User(0, userData.get("name"), userData.get("username"), userData.get("password")));
				UserView.showInfoMessage("Successfully registered");
			}
		} catch (InvalidInputData e) {
			UserView.showInfoMessage(e.getMessage());
		}

	}

}
