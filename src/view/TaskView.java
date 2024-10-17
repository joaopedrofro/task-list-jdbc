package view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.entities.Task;
import model.entities.User;
import view.util.InfoMessage;

public class TaskView {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Scanner scan;

	public TaskView(Scanner scan) {
		this.scan = scan;
	}

	public Integer getTaskMenuOptionView() {
		System.out.println("\nOPTIONS:");
		System.out.println("1 - Add task");
		System.out.println("2 - Mark task as done");
		System.out.println("3 - Unmark done task");
		System.out.println("4 - Return");
		System.out.print("\nChoice: ");

		return Integer.parseInt(scan.nextLine());
	}

	public void showTaskView(User user) {
		InfoMessage.showSystemInfo();
		InfoMessage.showUserInfo(user);

		System.out.println("\nTasks:\n");

		List<Task> userTasks = user.getTasks();

		if (userTasks.isEmpty()) {
			System.out.println("No tasks created yet!");
		} else {
			for (Task t : userTasks) {
				if (t.getDone()) {
					System.out.printf("[x] - %s [%s]\n", t.getTitle(), sdf.format(t.getMoment()));
				} else {
					System.out.printf("[ ] - %s [%s]\n", t.getTitle(), sdf.format(t.getMoment()));
				}
			}
		}
	}
	
	public void addTaskView() {
		
	}
}
