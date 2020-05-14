/**
 * Homework for lesson 15 - "Map"
 */
package ua.lviv.lgs.hw15;


import java.util.Scanner;

/**
 * @author alexl
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ZooClub zooClub = new ZooClub("StarAnimals");

		String menuString;
		Scanner sc;

		while (true) {
			System.out.println();
			printMenu();
			/* checking of menuString from the user */
			sc = new Scanner(System.in);
			InputMenuString inputMenuString = new InputMenuString(sc.next().toLowerCase());

			menuString = inputMenuString.getString();

			if (inputMenuString.flag) {
				switch (menuString) {
				case "1": {
					zooClub.addPersonToZooClub();
					break;

				}

				case "2": {
					zooClub.AddAnimalToPerson();
					break;

				}

				case "3": {
					zooClub.removeAnimalFromPerson();
					break;

				}

				case "4": {
					zooClub.removePersonFromZooClub();
					break;

				}

				case "5": {
					zooClub.removeAnimalFromAllPersons();
					break;

				}

				case "6": {
					zooClub.showZooClub();
					break;

				}

				case "7": {
					System.out.println("Exit application... Good buy!");
					System.exit(0);
					sc.close();
					break;

				}

				}

			}
		}

	}

	public static void printMenu() {

		System.out.println("| -----------------Menu--------------------|");
		System.out.println("| Input 1 to add Person to ZooClub         |");
		System.out.println("| Input 2 to add Animal to Person          |");
		System.out.println("| Input 3 to remove Animal from Person     |");
		System.out.println("| Input 4 to remove Person from ZooClub    |");
		System.out.println("| Input 5 to remove Animal from All Persons|");
		System.out.println("| Input 6 to show ZooClub                  |");
		System.out.println("| Input 7 to exit                          |");

	}

}
