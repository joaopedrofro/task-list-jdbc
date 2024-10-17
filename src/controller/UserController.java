package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UserDao;
import services.AuthenticationService;
import view.LoginView;
import view.MainView;

public class UserController {

	private Connection conn;
	private Scanner scan;

	public UserController(Connection conn, Scanner scan) {
		this.conn = conn;
		this.scan = scan;
	}

	public void mainMenu() {
		MainView mainView = new MainView(scan);
		
		boolean running = true;
		
		while (running) {
			int opt = mainView.menuMainView();
			
			switch (opt) {
			case 1:
				userLogin();
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
		LoginView view = new LoginView(scan);

		UserDao userDao = DaoFactory.getUserDao(conn);

		AuthenticationService auth = new AuthenticationService(userDao);

		while (true) {
			String[] data = view.showLoginView();

			if (data.length > 0) {
				if (auth.authenticate(data[0], data[1])) {
					view.sucessfullyLoginView();
					break;
				} else {
					view.invalidCredentialsView();
				}
			} else {
				break;
			}
		}
	}

}
