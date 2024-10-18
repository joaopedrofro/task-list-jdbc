package util;

import java.util.Scanner;

public abstract class Scan {
	
	private static Scanner scan;
	
	public static Scanner getScanInstance() {
		if (scan == null) {
			scan = new Scanner(System.in);
			return scan;
		} else {
			return scan;
		}
	}
	
	public static void closeScan() {
		scan.close();
	}
}
