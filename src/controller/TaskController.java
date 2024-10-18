package controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entities.Task;
import model.entities.User;
import view.TaskView;

public class TaskController extends GenericController {

	private TaskDao taskDao;
	private User user;

	public TaskController(User user) {
		taskDao = DaoFactory.getTaskDao(conn);
		this.user = user;
	}

	public void displayUserTasksMenu() {
		TaskView taskView = new TaskView(user.getName());

		boolean running = true;

		while (running) {
			int option = 0;

			try {
				taskView.displayUserTasks(
						user.getTasks().stream().sorted(Comparator.comparing(Task::getDone)).toList());
				option = taskView.getTaskMenuOption();
			} catch (NumberFormatException e) {
				TaskView.showInfoMessage("Invalid option");
				continue;
			}

			switch (option) {
			case 1:
				String taskTitle = taskView.getTitleForNewTask();
				
				Task newTask = new Task(0, taskTitle, new Date(), false, user.getId());
				taskDao.add(newTask);
				user.getTasks().add(newTask);
				
				TaskView.showInfoMessage("Task created");
				break;
			case 2:
				List<Task> undoneTasks = user.getTasks().stream().filter(t -> t.getDone() == false).toList();
				Task taskToDone = taskView.getTaskToPerformAction(undoneTasks, "Complete Task");
				
				if (taskToDone != null) {
					taskToDone.setDone(true);
					taskDao.update(taskToDone);
				}
				break;
			case 3:
				List<Task> doneTasks = user.getTasks().stream().filter(t -> t.getDone()).toList();
				Task taskToUndone = taskView.getTaskToPerformAction(doneTasks, "Undone Task");
				
				if (taskToUndone != null) {
					taskToUndone.setDone(false);
					taskDao.update(taskToUndone);
				}
				break;
			case 4:
				Task taskToDelete = taskView.getTaskToPerformAction(user.getTasks(), "Delete Task");
				
				if (taskToDelete != null) {
					user.getTasks().remove(taskToDelete);
					taskDao.delete(taskToDelete);
				}
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
