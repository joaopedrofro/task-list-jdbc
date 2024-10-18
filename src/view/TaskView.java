package view;

import java.text.SimpleDateFormat;
import java.util.List;

import model.entities.Task;
import model.entities.User;

public class TaskView extends GenericView {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private User user;

	public TaskView(User user) {
		this.user = user;
	}

	public Integer getTaskMenuOptionView() {
		System.out.println("\nOPTIONS:");
		System.out.println("1 - Add task");
		System.out.println("2 - Mark task as done");
		System.out.println("3 - Unmark done task");
		System.out.println("4 - Delete task");
		System.out.println("5 - Return");
		System.out.print("\nChoice: ");

		return Integer.parseInt(scanner.nextLine());
	}

	public void showTaskView() {
		showSystemAndUserInfo(user);

		System.out.println("\nTasks:\n");

		List<Task> userTasks = user.getTasks();

		showTasks(userTasks);
	}

	private void showTasks(List<Task> userTasks) {
		if (userTasks.isEmpty()) {
			System.out.println("No tasks created yet!");
		} else {
			for (int i = 0; i < userTasks.size(); i++) {
				Task t = userTasks.get(i);
				System.out.print((i + 1) + " - ");
				if (t.getDone())
					System.out.printf("[x]");
				else
					System.out.printf("[ ]");
				System.out.printf(" %s [%s]\n", t.getTitle(), sdf.format(t.getMoment()));
			}
		}
	}

	public String addTaskView() {
		showSystemAndUserInfo(user);

		System.out.println("\nAdd task:\n");
		System.out.print("Title: ");
		String title = scanner.nextLine();

		return title;
	}

	public Task doneTaskView() {
		List<Task> undoneTasks = user.getTasks().stream().filter(t -> !t.getDone()).toList();

		boolean running = true;

		while (running) {
			showSystemAndUserInfo(user);
			System.out.println();
			showTasks(undoneTasks);

			System.out.println("\nComplete task:\n");
			System.out.print("\nTask number: ");

			try {
				String opt = scanner.nextLine();

				if (opt.equals("exit")) {
					running = false;
					break;
				} else {
					int taskOption = Integer.parseInt(opt) - 1;
					Task doneTask = undoneTasks.get(taskOption);
					doneTask.setDone(true);
					return doneTask;
				}
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				showInfoMessage("Invalid option");
			}
		}

		return null;
	}

	public void undoneTaskView() {
		showTaskView();
		System.out.println("\nUndone task:\n");
		System.out.print("\nTask number:");

		try {
			int taskOption = Integer.parseInt(scanner.nextLine()) - 1;
			user.getTasks().get(taskOption).setDone(true);
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			showInfoMessage("Invalid option");
		}
	}
}
