package view;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import main.Program;
import util.Scan;

public abstract class GenericView {

	protected static Scanner scanner;
	private static Console console = System.console();
	
	public GenericView() {
		scanner = Scan.getScanInstance();
	}

	public static void showInfoMessage(String message) {
		System.out.print("\n" + message + "! Pressione Enter para continuar...");
		scanner.nextLine();
	}

	protected static void showSystemInfo() {
		System.out.println("\n".repeat(30));
		System.out.println("GERENCIADOR DE TAREFAS");
		System.out.println("===================================");
		System.out.println("Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		System.out.println("Versão: 0.0.1-a");
	}

	protected void showSystemAndUserInfo(String userName) {
		showSystemInfo();
		System.out.println("Usuário logado: " + userName);
	}
	
	protected String getUserInput(String message) {
		System.out.printf("%s: ", message);
		
		String input = null;
		
		try {
			input = scanner.nextLine();
		} catch (NoSuchElementException e) {
			Program.closeResources();
			System.exit(0);
		}
		
		return input;
	}
	
	protected String getUserPasswordInput(String message) {
		String input = null;
		
		try {
			input = String.valueOf(console.readPassword("%s: ", message));
		} catch (NullPointerException e) {
			Program.closeResources();
			System.exit(0);
		}
		
		return input;
	}
}
