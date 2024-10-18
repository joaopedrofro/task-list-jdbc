package controller;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entities.Task;
import model.entities.User;
import view.TaskView;

public class TaskController extends GenericController {

	private TaskDao taskDao;
	
	public TaskController() {
		taskDao = DaoFactory.getTaskDao(conn);
	}

	public void taskList(User user) {
		TaskView taskView = new TaskView(user);

		boolean running = true;

		while (running) {
			int opt = 0;

			try {
				taskView.showTaskView();
				opt = taskView.getTaskMenuOptionView();
			} catch (NumberFormatException e) {
				TaskView.showInfoMessage("Invalid option");
				continue;
			}

			switch (opt) {
			case 1:
				String taskTitle = taskView.addTaskView();

				Task newTask = new Task(0, taskTitle, new Date(), false, user.getId());
				taskDao.add(newTask);

				user.getTasks().add(newTask);

				TaskView.showInfoMessage("Task created");
				break;
			case 2:
				Task doneTask = taskView.doneTaskView();
				taskDao.update(doneTask);
				break;
			case 5:
				running = false;
				break;
			default:
				TaskView.showInfoMessage("Invalid option");
			}
		}
	}

}
