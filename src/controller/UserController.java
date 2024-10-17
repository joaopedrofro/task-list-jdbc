package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import services.AuthenticationService;
import view.UserView;
import view.MainView;

public class UserController {

	private static UserDao userDao;
	private Scanner scan;

	public UserController(Connection conn, Scanner scan) {
		userDao = DaoFactory.getUserDao(conn);
		this.scan = scan;
	}

	public void mainMenu() {
		MainView mainView = new MainView(scan);

		boolean running = true;

		while (running) {
			int opt = 0;

			try {
				opt = mainView.menuMainView();
			} catch (NumberFormatException e) {
				mainView.invalidChoice();
				continue;
			}

			switch (opt) {
			case 1:
				userLogin();
				break;
			case 2:
				userRegister();
				break;
			case 3:
				running = false;
				break;
			default:
				mainView.invalidChoice();
			}
		}
	}

	public void userLogin() {
		UserView view = new UserView(scan);

		AuthenticationService auth = new AuthenticationService(userDao);

		while (true) {
			String[] data = view.showUserLoginView();

			if (data.length > 0) {
				if (auth.authenticate(data[0], data[1])) {
					view.successfullyLoginView();
					break;
				} else {
					view.invalidCredentialsView();
				}
			} else {
				break;
			}
		}
	}

	public void userRegister() {
		UserView view = new UserView(scan);

		String[] data = view.showUserRegisterView();

		if (data.length > 0) {
			if (userDao.getUserByName(data[1]).isPresent()) {
				view.userAlreadyExistsView();
			} else {
				userDao.add(new User(0, data[0], data[1], data[2]));
				view.successfullyRegisterView();
			}
		}
	}

}
