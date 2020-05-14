/**
 * Homework for lesson 15 - "Map"
 */
package ua.lviv.lgs.hw15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author alexl
 *
 */
public class ZooClub {
	private final int MIN_MEMBER_AGE = 14;

	private String name;
	private Map<Person, List<Animal>> map = new HashMap<>();

	/**
	 * @param name
	 */
	public ZooClub(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the map
	 */
	public Map<Person, List<Animal>> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<Person, List<Animal>> map) {
		this.map = map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZooClub other = (ZooClub) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ZooClub [name=" + name + ", map=" + map + "]";
	}

	/* private methods declaration block* ======================== */

	private void askAnimalTypesInput() {
		System.out.println("Enter the type of animal from the list below...");
		System.out.print("[");

		for (AnimalTypes anType : AnimalTypes.values()) {
			System.out.print(" " + anType + " ");
		}
		System.out.println("]");
	}

	private String inputStringFromConsole() {

		Scanner sc = new Scanner(System.in);

		if (sc.hasNext()) {
			name = sc.next();
		}

		return name;
	}

	private int inputAgeFromConsole() {
		int age = MIN_MEMBER_AGE;
		int number;
		while (true) {

			System.out.println("Add age (integer value " + MIN_MEMBER_AGE + "):");
			Scanner sc = new Scanner(System.in); // input from user

			if (sc.hasNextInt()) {
				number = sc.nextInt();

				if (number >= MIN_MEMBER_AGE) {
					age = number;
					break;
				} else {
					try {
						throw new WrongInputConsoleParametersException("Value must be correct(age), try again...");
					} catch (WrongInputConsoleParametersException e) {
						System.out.println(e.getMessage());
					}
				}

			} else {
				try {
					throw new WrongInputConsoleParametersException("Input integer value, try again...");
				} catch (WrongInputConsoleParametersException e) {
					System.out.println(e.getMessage());
				}
			}

		}

		return age;
	}

	/* Methods declaration block ===================================== */

	public void addPersonToZooClub() {

		List<Animal> animals = new ArrayList<Animal>();
		String name = null;
		int age = 0;

		System.out.println("Please enter the name of person: ");

		name = inputStringFromConsole();
		age = inputAgeFromConsole();

		Person person = new Person(name, age);
		map.put(person, animals);
		// System.out.println(map.toString());
		showZooClub();

	}

	public void AddAnimalToPerson() {

		//List<Animal> animals = new ArrayList<Animal>();
		String name = null;
		int age = MIN_MEMBER_AGE;
		String animalName = null;
		String animalType = null;

		System.out.println("Please enter the name of person who you want to add animal to: ");

		name = inputStringFromConsole();
		age = inputAgeFromConsole();

		Person personTemp = new Person(name, age);

		if (map.containsKey(personTemp)) {
			System.out.println("Enter the name of animal to add:");

			animalName = inputStringFromConsole();

			askAnimalTypesInput();
			boolean flag = true;
			while (flag) {
				animalType = inputStringFromConsole().toUpperCase();
				/* checking animalType to be from AnimalTypes enum */
				try {
					AnimalTypes.valueOf(animalType);
					flag = false;
				} catch (IllegalArgumentException e) {
					System.out.println("Enter right type from the list");
					flag = true;

				}
			}

			Iterator<Entry<Person, List<Animal>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Person, List<Animal>> entry = iterator.next();
				Animal animalTemp = new Animal(animalType, animalName);
				if (entry.getKey().equals(personTemp)) {
					/* chekcking for duplicate animal: is this animal in database already? */
					if (!entry.getValue().contains(animalTemp)) {
						entry.getValue().add(new Animal(animalType, animalName));
					} else {
						System.out.println("This animal is in database already");
					}

				}
			}

		} else {
			System.out.println("There is not this person in ZooClub, try another person next time");
		}

		// System.out.println(map.toString());
		showZooClub();

	}

	public void removePersonFromZooClub() {

		//List<Animal> animals = new ArrayList<Animal>();
		String name = null;
		int age = 0;

		System.out.println("Please enter the name of person to remove: ");

		name = inputStringFromConsole();
		age = inputAgeFromConsole();

		Person person = new Person(name, age);
		System.out.println(person);

		if (map.containsKey(person)) {
			Iterator<Entry<Person, List<Animal>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Person, List<Animal>> entry = iterator.next();

				if (entry.getKey().equals(person)) {
					
					iterator.remove();
				}
			}

		} else {
			System.out.println("There is not this person in ZooClub, try another person next time");
		}
		// map.put(person, animals);
		// System.out.println(map.toString());
		showZooClub();

	}

	public void removeAnimalFromPerson() {

		List<Animal> animals = new ArrayList<Animal>();
		String name = null;
		int age = MIN_MEMBER_AGE;
		String animalName = null;
		String animalType = null;

		System.out.println("Please enter the name of person who you want to remove animal from: ");

		name = inputStringFromConsole();
		age = inputAgeFromConsole();

		Person personTemp = new Person(name, age);

		if (map.containsKey(personTemp)) {
			System.out.println("Enter the name of animal to remove:");

			animalName = inputStringFromConsole();

			askAnimalTypesInput();

			boolean flag = true;
			while (flag) {
				animalType = inputStringFromConsole().toUpperCase();
				/* checking animalType to be from AnimalTypes enum */
				try {
					AnimalTypes.valueOf(animalType);
					flag = false;
				} catch (IllegalArgumentException e) {
					System.out.println("Enter right type from the list");
					flag = true;

				}
			}

			Iterator<Entry<Person, List<Animal>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Person, List<Animal>> entry = iterator.next();
				Animal animalTemp = new Animal(animalType, animalName);
				if (entry.getKey().equals(personTemp)) {

					if (entry.getValue().contains(animalTemp)) {
						entry.getValue().remove(animalTemp);
					} else {
						System.out.println("This animal is not in database yet, try another one");
					}

				}
			}

		} else {
			System.out.println("There is not this person in ZooClub, try another person next time");
		}

		// System.out.println(map.toString());
		showZooClub();

	}

	public void removeAnimalFromAllPersons() {

		String animalName = null;
		String animalType = null;

		System.out.println("Please enter the animal to remove from all persons... ");

		System.out.println("Enter the name of animal to remove:");

		animalName = inputStringFromConsole();

		askAnimalTypesInput();

		boolean flag = true;
		while (flag) {
			animalType = inputStringFromConsole().toUpperCase();
			/* checking animalType to be from AnimalTypes enum */
			try {
				AnimalTypes.valueOf(animalType);
				flag = false;
			} catch (IllegalArgumentException e) {
				System.out.println("Enter right type from the list");
				flag = true;

			}
		}

		Iterator<Entry<Person, List<Animal>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Person, List<Animal>> entry = iterator.next();
			Animal animalTemp = new Animal(animalType, animalName);

			if (entry.getValue().contains(animalTemp)) {
				entry.getValue().remove(animalTemp);
			} else {
				System.out.println("This animal is not in database yet, try another one");
			}

		}

		// System.out.println(map.toString());
		showZooClub();

	}

	public void showZooClub() {
		System.out.println();
		
		
		if (!map.isEmpty()) {
			System.out.println("The ZooClub consists of:");
			Iterator<Entry<Person, List<Animal>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Person, List<Animal>> entry = iterator.next();
				System.out.println(entry.toString());

			}
		} else {
			System.out.println("The ZooClub is empty for now");
		}
		
		System.out.println();

	}

}
