package view;

import java.util.Scanner;

public class RiskUI {
	private String welcomeMessage = "\t\t*****Risk Game*****";
	private String editMapYesOrNoMessage = "Do you want to create/edit map? (Y/N)\n";
	private String editMapRequestingMessage = "Enter corresponding commands for creating/editing a map.\n"
			+ "Whenever you are happy with the result, enter \"finishediting\".\n";
	private String loadMapRequestingMessage = "Load the map you with to play by using \"loadmap\" command:\n";
	private String addOrRemovePlayersRequestingMessage = "Add/remove players and at the end, enter \"populatecountries\":\n";
	private String startupRequestingMessage = "Place your armies on your selected country or use \"placeall\" command:\n";
	private String reinforceRequestingMessage = "Now, it's time to reinforce!\n";
	private String fortifyRequestingMessage = "Let's fortify! or type in \"fortify none\" if you don't want to.\n";

	private Scanner scanner;

	public RiskUI() {
		scanner = new Scanner(System.in);
	}

	public void RiskUIStartTheGame() {

		String editMapYesOrNoAnswer;
		boolean editMapWhileStatus = true;

		String loadMapCommands;
		String loadMapFilePath;
		boolean loadMapWhileStatus = true;

		String playersCommands;
		String addOrRemovePlayerCommand;
		String playerName;
		boolean playersWhileStatus = true;

		String startupCommands;
		String countryNamePlaceArmyCommand;
		int numberPlaceArmyCommand;
		boolean startupWhileStatus = true;
		

		while(editMapWhileStatus) {

			System.out.println(welcomeMessage);
			System.out.println(editMapRequestingMessage);
			editMapYesOrNoAnswer = scanner.next();

			switch (editMapYesOrNoAnswer) {
			case "Y":
			case "y":


				editMapWhileStatus = false;
				break;

			case "N":
			case "n":

				editMapWhileStatus = false;
				break;

			default:
				System.out.println("Your answer is not valid! Try again...\n");
				break;
			}
		}

		System.out.println(loadMapRequestingMessage);
		loadMapCommands = scanner.next();


		while(loadMapWhileStatus) {
			switch (loadMapCommands) {

			case "loadmap":
			case "LOADMAP":
				loadMapFilePath = scanner.next();
				loadMapWhileStatus = false;
				break;

			default:
				System.out.println("Your command is not valid! Try again...\\n");
				break;
			}
		}

		System.out.println(addOrRemovePlayersRequestingMessage);

		while(playersWhileStatus) {
			playersCommands = scanner.next();

			switch (playersCommands) {
			case "gameplayer":
			case "GAMEPLAYER":
				if (scanner.hasNext()) 
					while (scanner.hasNext()) {
						addOrRemovePlayerCommand = scanner.next();
						playerName = scanner.next();

						switch (addOrRemovePlayerCommand) {
						case "-add":
						case "-ADD":

							break;

						case "-remove":
						case "-REMOVE":

							break;
						}
					}
				else 
					System.out.println("Your command is not valid! Try again...\\n");

				break;

			case "populatecountries":
			case "POPULATECOUNTRIES":

				playersWhileStatus = false;
				break;

			default:
				System.out.println("Your command is not valid! Try again...\\n");
				break;
			}
		}

		System.out.println(startupRequestingMessage);

		for(int i = 0; i < ...; i++) {

			System.out.println("Player" + ...);
			startupWhileStatus = true;

			while(startupWhileStatus) {
				System.out.println("You have -" + ... + "- army(ies) to place!");
				startupCommands = scanner.next();

				switch (startupCommands) {
				case "placearmy":
				case "PLACEARMY":
					if (scanner.hasNext()) 
						while (scanner.hasNext()) {
							countryNamePlaceArmyCommand = scanner.next();
							numberPlaceArmyCommand = scanner.nextInt();
							
						}
					else 
						System.out.println("Your command is not valid! Try again...\\n");


					break;

				case "placeall":
				case "PLACEALL":

					startupWhileStatus = false;
					break;

				default:
					System.out.println("Your command is not valid! Try again...\\n");
					break;
				}	
			}
		}
		
		System.out.println(reinforceRequestingMessage);
		
		
		System.out.println(fortifyRequestingMessage);
		
	}
}
