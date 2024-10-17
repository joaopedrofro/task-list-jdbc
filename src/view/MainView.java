package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainView {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Scanner scan;

	public MainView(Scanner scan) {
		this.scan = scan;
	}
	
	public Integer menuMainView() {
		topView();
		
		System.out.println("\nOPÇÕES:");
		System.out.println("1 - Login");
		System.out.println("2 - Register");
		System.out.println("3 - Exit");
		
		System.out.print("\nChoice: ");
		
		return Integer.parseInt(scan.nextLine());
	}
	
	public void invalidChoice() {
		System.out.println("\nInvalid choice! Press enter to continue...");
		scan.nextLine();
	}

	public static void topView() {
		System.out.println("\n".repeat(30));
		System.out.println("TASKAPP SYSTEM");
		System.out.println("======================");
		System.out.println("Date: " + sdf.format(new Date()));
		System.out.println("Version: 0.0.1-a");
	}

}
