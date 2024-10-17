package controller;

import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entities.Task;
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
		TaskView taskView = new TaskView(scan, user);

		boolean running = true;

		while (running) {
			int opt = 0;

			try {
				taskView.showTaskView();
				opt = taskView.getTaskMenuOptionView();
			} catch (NumberFormatException e) {
				InfoMessage.showInfoMessage("Invalid option", scan);
				continue;
			}

			switch (opt) {
			case 1:
				String taskTitle = taskView.addTaskView();

				Task newTask = new Task(0, taskTitle, new Date(), false, user.getId());
				taskDao.add(newTask);

				user.getTasks().add(newTask);

				InfoMessage.showInfoMessage("Task created", scan);
				break;
			case 2:
				Task doneTask = taskView.doneTaskView();
				taskDao.update(doneTask);
				break;
			case 5:
				running = false;
				break;
			default:
				InfoMessage.showInfoMessage("Invalid option", scan);
			}
		}
	}

}
