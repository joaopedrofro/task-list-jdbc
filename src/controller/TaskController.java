package controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.dao.exceptions.DatabaseQueryException;
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
				TaskView.showInfoMessage("Opção inválida");
				continue;
			}

			switch (option) {
			case 1:
				addTask(taskView.getTitleForNewTask());
				break;
			case 2:
				List<Task> undoneTasks = user.getTasks().stream().filter(t -> t.getDone() == false).toList();

				if (undoneTasks.isEmpty()) {
					TaskView.showInfoMessage("Não há tarefas para concluir");
				} else {
					Task taskToDone = taskView.getTaskToPerformAction(undoneTasks, "COMPLETAR TAREFA");
					doneTask(taskToDone);
				}

				break;
			case 3:
				List<Task> doneTasks = user.getTasks().stream().filter(t -> t.getDone()).toList();

				if (doneTasks.isEmpty()) {
					TaskView.showInfoMessage("Não há tarefas para desmarcar");
				} else {
					Task taskToUndone = taskView.getTaskToPerformAction(doneTasks, "DESMARCAR TAREFA");
					undoneTask(taskToUndone);
				}

				break;
			case 4:
				List<Task> tasksToDelete = user.getTasks();

				if (tasksToDelete.isEmpty()) {
					TaskView.showInfoMessage("Não há tarefas para excluir");
				} else {
					Task taskToDelete = taskView.getTaskToPerformAction(tasksToDelete, "DELETAR TAREFA");
					deleteTask(taskToDelete);
				}

				break;
			case 5:
				running = false;
				break;
			default:
				TaskView.showInfoMessage("Opção inválida");
			}
		}
	}

	private void addTask(String taskTitle) {
		Task newTask = new Task(0, taskTitle, new Date(), false, user.getId());
		try {
			taskDao.add(newTask);
			user.getTasks().add(newTask);
		} catch (DatabaseQueryException e) {
			TaskView.showInfoMessage("Erro ao tentar criar nova tarefa: " + e.getMessage());
		}
	}

	private void doneTask(Task taskToDone) {
		if (taskToDone != null) {
			try {
				taskToDone.setDone(true);
				taskDao.update(taskToDone);
			} catch (DatabaseQueryException e) {
				taskToDone.setDone(false);
				TaskView.showInfoMessage("Erro ao tentar concluir a tarefa: " + e.getMessage());
			}

		}
	}

	private void undoneTask(Task taskToUndone) {
		if (taskToUndone != null) {
			try {
				taskToUndone.setDone(false);
				taskDao.update(taskToUndone);
			} catch (DatabaseQueryException e) {
				taskToUndone.setDone(true);
				TaskView.showInfoMessage("Erro ao tentar desmarcar a tarefa: " + e.getMessage());
			}
		}
	}

	private void deleteTask(Task taskToDelete) {
		if (taskToDelete != null) {
			try {
				taskDao.delete(taskToDelete);
				user.getTasks().remove(taskToDelete);
			} catch (DatabaseQueryException e) {
				TaskView.showInfoMessage("Erro ao tentar excluir a tarefa: " + e.getMessage());
			}
		}
	}
}
