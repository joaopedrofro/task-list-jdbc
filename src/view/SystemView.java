package view;

public class SystemView extends GenericView {

	private void showMenu() {
		showSystemInfo();
		System.out.println("\nOPTIONS:");
		System.out.println("1 - Login");
		System.out.println("2 - Register");
		System.out.println("3 - Exit");
		System.out.print("\nChoice: ");
	}
	
	public Integer getOptionFromMenu() {
		showMenu();
		return Integer.parseInt(scanner.nextLine());
	}

}
