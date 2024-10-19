package util;

import java.util.Scanner;

public abstract class Scan {
	
	private static Scanner scanner;
	
	public static Scanner getScanInstance() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
			return scanner;
		} else {
			return scanner;
		}
	}
	
	public static void closeScanner() {
		scanner.close();
	}
}
