package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.User;
import util.Scan;

public abstract class GenericView {

	protected static Scanner scanner;

	public GenericView() {
		scanner = Scan.getScanInstance();
	}

	public static void showInfoMessage(String message) {
		System.out.println("\n" + message + "! Press Enter to continue...");
		scanner.nextLine();
	}

	public static void showSystemInfo() {
		System.out.println("\n".repeat(30));
		System.out.println("TASKAPP SYSTEM");
		System.out.println("======================");
		System.out.println("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		System.out.println("Version: 0.0.1-a");
	}

	public static void showSystemAndUserInfo(User user) {
		showSystemInfo();
		System.out.println("User: " + user.getName());
	}
}
