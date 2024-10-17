package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entities.User;
import view.TaskView;
import view.util.InfoMessage;

public class TaskController {
	private static TaskDao taskDao;
	private Scanner scan;

	public TaskController(Connection conn, Scanner scan) {
		taskDao = DaoFactory.getTaskDao(conn);
		this.scan = scan;
	}
	
	public void taskList(User user) {
		TaskView taskView = new TaskView(scan);
		
		boolean running = true;

		while (running) {
			int opt = 0;

			try {
				taskView.showTaskView(user);
				opt = taskView.getTaskMenuOptionView();
			} catch (NumberFormatException e) {
				InfoMessage.showInfoMessage("Invalid option", scan);
				continue;
			}

			switch (opt) {
			case 1:
				
				break;
			case 4:
				running = false;
				break;
			default:
				InfoMessage.showInfoMessage("Invalid option", scan);
			}
		}
	}

}
