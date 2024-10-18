package controller;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import services.AuthenticationService;
import view.UserView;

public class UserController extends GenericController {

	private UserDao userDao;

	public UserController() {
		userDao = DaoFactory.getUserDao(conn);
	}

	public void userLogin() {
		UserView view = new UserView();
		TaskController taskCtrl = new TaskController();

		AuthenticationService auth = new AuthenticationService(userDao);

		while (true) {
			String[] data = view.showUserLoginView();

			if (data.length > 0) {
				if (auth.authenticate(data[0], data[1])) {
					taskCtrl.taskList(auth.getUserAuthenticated());
					break;
				} else {
					UserView.showInfoMessage("Invalid credentials");
				}
			} else {
				break;
			}
		}
		
		auth.logoutUserAuthenticated();
	}

	public void userRegister() {
		UserView view = new UserView();

		String[] data = view.showUserRegisterView();

		if (data.length > 0) {
			if (userDao.getUserByName(data[1]).isPresent()) {
				UserView.showInfoMessage("User already exists");
			} else {
				userDao.add(new User(0, data[0], data[1], data[2]));
				UserView.showInfoMessage("Register successfully");
			}
		}
	}

}
