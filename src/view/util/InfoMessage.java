package view.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.User;

public class InfoMessage {

	public static void showInfoMessage(String message, Scanner scan) {
		System.out.println("\n" + message + "! Press Enter to continue...");
		scan.nextLine();
	}

	public static void showSystemInfo() {
		System.out.println("\n".repeat(30));
		System.out.println("TASKAPP SYSTEM");
		System.out.println("======================");
		System.out.println("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		System.out.println("Version: 0.0.1-a");
	}
	
	public static void showUserInfo(User user) {
		System.out.println("User: " + user.getName());
	}
	
	public static void showSystemAndUserInfo(User user) {
		showSystemInfo();
		System.out.println("User: " + user.getName());
	}

}
