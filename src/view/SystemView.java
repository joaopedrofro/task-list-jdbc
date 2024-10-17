package view;

import java.util.Scanner;

import view.util.InfoMessage;

public class SystemView {

	private Scanner scan;

	public SystemView(Scanner scan) {
		this.scan = scan;
	}

	public Integer showSystemView() {
		InfoMessage.showSystemInfo();

		System.out.println("\nOPTIONS:");
		System.out.println("1 - Login");
		System.out.println("2 - Register");
		System.out.println("3 - Exit");

		System.out.print("\nChoice: ");

		return Integer.parseInt(scan.nextLine());
	}

}
