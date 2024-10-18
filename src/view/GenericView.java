package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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

	protected static void showSystemInfo() {
		System.out.println("\n".repeat(30));
		System.out.println("TASKAPP SYSTEM");
		System.out.println("======================");
		System.out.println("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		System.out.println("Version: 0.0.1-a");
	}

	protected void showSystemAndUserInfo(String userName) {
		showSystemInfo();
		System.out.println("User: " + userName);
	}
}
