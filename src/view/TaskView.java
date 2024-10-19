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
		System.out.println("\nMENU DE TAREFAS\n");
		System.out.println("1 - Adicionar tarefa");
		System.out.println("2 - Concluir tarefa");
		System.out.println("3 - Desmarcar tarefa concluída");
		System.out.println("4 - Excluir tarefa");
		System.out.println("5 - Voltar");

		return Integer.parseInt(getUserInput("\nOPÇÃO"));
	}

	public void displayUserTasks(List<Task> userTasks) {
		showSystemAndUserInfo(userName);

		System.out.println("\nTAREFAS:");

		if (userTasks.isEmpty()) {
			System.out.println("Sem tarefas para mostrar aqui!");
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

		System.out.println("\nCRIAR TAREFA\n");
		String title = getUserInput("Nome da tarefa");

		return title;
	}

	public Task getTaskToPerformAction(List<Task> userTasks, String menuMessage) {
		boolean running = true;

		while (running) {
			showSystemAndUserInfo(userName);
			System.out.println();
			displayUserTasks(userTasks);

			System.out.println("\n" + menuMessage);

			try {
				String opt = getUserInput("\nNúmero da tarefa");

				if (opt.equals("exit")) {
					running = false;
					break;
				} else {
					int taskOption = Integer.parseInt(opt) - 1;
					Task task = userTasks.get(taskOption);
					return task;
				}
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				showInfoMessage("Opção inválida");
			}
		}

		return null;
	}

}
