package view;

public class SystemView extends GenericView {

	private void showMenu() {
		showSystemInfo();
		System.out.println("\nMENU PRINCIPAL\n");
		System.out.println("1 - Entrar");
		System.out.println("2 - Cadastrar usuário");
		System.out.println("3 - Sair");
	}
	
	public Integer getOptionFromMenu() {
		showMenu();
		
		int option = 0;
		
		try {
			option = Integer.parseInt(getUserInput("\nOPÇÃO"));
		} catch (NumberFormatException e) {
			return 0;
		}
		
		return option;
	}

}
