package view;

import java.text.SimpleDateFormat;
import java.util.List;

import model.entities.Task;

public class TaskView extends GenericView {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String userName;

	public TaskView(String userName) {
		this.userName = userName;
	}

	public Integer getTaskMenuOption() {
		System.out.println("\nOPTIONS:");
		System.out.println("1 - Add task");
		System.out.println("2 - Mark task as done");
		System.out.println("3 - Unmark done task");
		System.out.println("4 - Delete task");
		System.out.println("5 - Return");
		System.out.print("\nEnter option: ");

		return Integer.parseInt(scanner.nextLine());
	}

	public void displayUserTasks(List<Task> userTasks) {
		showSystemAndUserInfo(userName);

		System.out.println("\nTasks:\n");

		if (userTasks.isEmpty()) {
			System.out.println("\nNo tasks to show!\n");
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

	public String getTitleForNewTask() {
		showSystemAndUserInfo(userName);

		System.out.println("\nAdd task:\n");
		System.out.print("Title: ");
		String title = scanner.nextLine();

		return title;
	}
	
	public Task getTaskToPerformAction(List<Task> userTasks, String menuMessage) {
		boolean running = true;

		while (running) {
			showSystemAndUserInfo(userName);
			System.out.println();
			displayUserTasks(userTasks);

			System.out.println("\n" + menuMessage);
			System.out.print("\nTask number: ");

			try {
				String opt = scanner.nextLine();

				if (opt.equals("exit")) {
					running = false;
					break;
				} else {
					int taskOption = Integer.parseInt(opt) - 1;
					Task task = userTasks.get(taskOption);
					return task;
				}
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				showInfoMessage("Invalid option");
			}
		}

		return null;
	}

}
