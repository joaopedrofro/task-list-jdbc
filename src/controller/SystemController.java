package controller;

import view.GenericView;
import view.SystemView;

public class SystemController extends GenericView {

	public void run() {
		SystemView systemView = new SystemView();
		UserController userController = new UserController();

		boolean running = true;

		while (running) {
			int option = 0;

			try {
				option = systemView.getOptionFromMenu();
			} catch (NumberFormatException e) {
				SystemView.showInfoMessage("Invalid option");
				continue;
			}

			switch (option) {
			case 1:
				userController.userLogin();
				break;
			case 2:
				userController.userRegister();
				break;
			case 3:
				running = false;
				break;
			default:
				SystemView.showInfoMessage("Invalid option");
			}
		}
	}
}
