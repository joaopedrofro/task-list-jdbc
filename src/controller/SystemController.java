package controller;

import java.sql.Connection;
import java.util.Scanner;

import view.SystemView;
import view.util.InfoMessage;

public class SystemController {

	private Connection conn;
	private Scanner scan;

	public SystemController(Connection conn, Scanner scan) {
		this.conn = conn;
		this.scan = scan;
	}

	public void init() {
		SystemView systemView = new SystemView(scan);
		UserController userCtrl = new UserController(conn, scan);

		boolean running = true;

		while (running) {
			int opt = 0;

			try {
				opt = systemView.showSystemView();
			} catch (NumberFormatException e) {
				InfoMessage.showInfoMessage("Invalid option", scan);
				continue;
			}

			switch (opt) {
			case 1:
				userCtrl.userLogin();
				break;
			case 2:
				userCtrl.userRegister();
				break;
			case 3:
				running = false;
				break;
			default:
				InfoMessage.showInfoMessage("Invalid option", scan);
			}
		}
	}
}
