package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import services.AuthenticationService;
import view.UserView;
import view.util.InfoMessage;

public class UserController {

	private static UserDao userDao;
	private Connection conn;
	private Scanner scan;

	public UserController(Connection conn, Scanner scan) {
		this.conn = conn;
		userDao = DaoFactory.getUserDao(conn);
		this.scan = scan;
	}

	public void userLogin() {
		UserView view = new UserView(scan);
		TaskController taskCtrl = new TaskController(conn, scan);

		AuthenticationService auth = new AuthenticationService(userDao);

		while (true) {
			String[] data = view.showUserLoginView();

			if (data.length > 0) {
				if (auth.authenticate(data[0], data[1])) {
					taskCtrl.taskList(auth.getUserAuthenticated());
					break;
				} else {
					InfoMessage.showInfoMessage("Invalid credentials", scan);
				}
			} else {
				break;
			}
		}
		
		auth.logoutUserAuthenticated();
	}

	public void userRegister() {
		UserView view = new UserView(scan);

		String[] data = view.showUserRegisterView();

		if (data.length > 0) {
			if (userDao.getUserByName(data[1]).isPresent()) {
				InfoMessage.showInfoMessage("User already exists", scan);
			} else {
				userDao.add(new User(0, data[0], data[1], data[2]));
				InfoMessage.showInfoMessage("Register successfully", scan);
			}
		}
	}

}
