package iTacademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import personHerencia.Merchant;
import personHerencia.Peasant;
import personHerencia.Person;
import personHerencia.Thief;

public class MenuMethods {

	static ArrayList<Person> people = InicialitzeDataBase.createPeople();

	public static void startMenu() {
		int option;
		do {
			option = menu();

			switch (option) {
			case 1:
				int id = requestId();
				Person person = findPerson(people, id);
				showPersonInventory(person,id);
				break;
			case 2:
				String city = requestCity();
				showCityPeople(people, city);
				break;
			case 3:
				String city2 = requestCity();
				Item item = findLowestItemCity(people, city2);
				printLowestItemCity(item, city2);
				break;
			case 4:
				String type = requestItemType();
				ArrayList<Item> items = findItemsType(people, type);
				printItemsType(items, type);
				break;
			case 5:
				int id1 = requestBuyerId();
				int id2 = requestSellerId();
				String itemName = requestItemName();
				Person personBuyer = findPerson(people, id1);
				Person personSeller = findPerson(people, id2);
				buyItem(personBuyer, personSeller, itemName);
				break;
			case 6:
				Person person2 = requestPersonData();
				addPerson(people, person2);
				break;
			case 7:
				Item newItem = requestNewItem();
				int id3 = requestId();
				Person person3 = findPerson(people, id3);
				addNewItem(person3, newItem);
				break;
			case 8:
				System.out.println("Shutting down the app");
				break;
			default:
				System.out.println("Input a valid number, please see the following options:");
			}

		} while (option != 8);
	}


	public static int menu() {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		option = Input.llegirInt("*** Welcome to city manager ***\n" + "1- Show a seller's items.\n"
				+ "2- Show the sellers in a city.\n"
				+ "3- Show the lowest priced items from all the sellers in a city\n"
				+ "4- Show all items of an item type ordered by price.\n" + "5- Buy an item from another Person.\n"
				+ "6- Create person in our database\n" + "7- Add new item to a Person\n" + "8- Shut down the app");
		return option;
	}


	public static int requestId() {
		int id = Input.llegirInt("Input a valid person ID:");
		return id;

	}

	public static int requestBuyerId() {
		int id = Input.llegirInt("Input your id as buyer:");
		return id;
	}

	public static int requestSellerId() {
		int id = Input.llegirInt("Input the id from the seller you want to buy:");
		return id;
	}

	public static String requestCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a valid city (like: Barcelona):");
		String city = sc.next();
		return city;
	}

	public static String requestItemType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a item type (like: tool):");
		String type = sc.next();
		return type;
	}

	public static String requestItemName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a valid item name (like: apple):");
		String itemName = sc.next();
		return itemName;
	}

	public static Person requestPersonData() {
		Scanner sc = new Scanner(System.in);
		Person person = null;
		int option = 0;
		do {
			System.out.println("Input 1 for Merchant\n" + "Input 2 for Peasant\n" + "Input 3 for Thief");
			option = sc.nextInt();
			if (option != 1 && option != 2 && option != 3) {
				System.out.println("Please input a valid number option.");
			}
		} while (option != 1 && option != 2 && option != 3);

		System.out.println("Input a valid name for add the person to our database ");
		String name = sc.next();
		String city = requestCity();

		if (option == 1) {
			person = new Merchant(name, city);

		} else if (option == 2) {
			person = new Peasant(name, city);

		} else if (option == 3) {
			person = new Thief(name, city);
		}
		return person;
	}

	public static Item requestNewItem() {
		Item item = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a valid item name (like: apple):");
		String itemName = sc.next();
		System.out.println("Input a valid item type for this item (like: tool)");
		String type = sc.next();
		System.out.println("Input item price");
		double price = sc.nextDouble();
		item = new Item(itemName, type, price);
		return item;
	}

	public static Person findPerson(ArrayList<Person> people, int id) {

		Optional<Person> personFound = people.stream()
				.filter(person -> person.getPersonId() == id)
				.findFirst();

		return personFound.orElse(null);
	}

	public static void showPersonInventory(Person person, int id) {

		if (person == null) {
			System.out.println("The person with id: " + id + " is not in our database");
		} else if(person.getInventory().isEmpty()) {
			System.out.println("The inventory of the person with id: "+id+ " is empty.");
		}
		else{System.out.println("The inventory of " + person.getName() + " is:");
			person.getInventory().stream().forEach(System.out::println);

		}

	}
	public static void showCityPeople(ArrayList<Person> people, String city) {

		ArrayList<Person> peopleCity = people.stream()
				.filter(person -> person.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toCollection(ArrayList::new));

		if (!peopleCity.isEmpty()) {
			System.out.println("The people in "+city+" are:");
			peopleCity.forEach(System.out::println);
		} else {
			System.out.println("No people found in the city: " + city);
		}
	}

	public static Item findLowestItemCity(ArrayList<Person> people, String city) {

		return people.stream()
				.filter(person -> !person.getInventory().isEmpty() && person.getCity().equalsIgnoreCase(city))
				.flatMap(person -> person.getInventory().stream())
				.min(Comparator.comparingDouble(Item::getPrice))
				.orElseGet(() -> new Item(null, null, 0));
	}

	public static void printLowestItemCity(Item item, String city) {
		if (item.getName() == null) {
			System.out.println(
					"The city " + city + " does not exist in our database or all persons in this city have no items");
		} else {
			System.out.println("The lowest priced item in " + city + " is: " + item);
		}
	}

	public static ArrayList<Item> findItemsType(ArrayList<Person> people, String type) {

		return people.stream()
				.flatMap(person -> person.getInventory().stream())
				.filter(item -> item.getType().equalsIgnoreCase(type))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	public static void printItemsType(ArrayList<Item> items, String type) {

		if (items.isEmpty() == true) {
			System.out.println("The item type: " + type + " does not exist or any person have this item type");
		} else {
			Collections.sort(items, Comparator.comparingDouble(Item::getPrice));
			System.out.println("Items of the type " + type + ":");
			items.stream().forEach(System.out::println);
		}
	}

	public static void buyItem(Person personBuyer, Person personSeller, String itemName) {

		Item item2 = null;

		if (personBuyer == null || personSeller == null) {
			System.out.println("The buyer or the seller are not in our database");
		} else if (personSeller.getInventory().isEmpty() == true) {
			System.out.println("The seller have no items");
		} else {
			item2 = personSeller.getInventory().stream()
					.filter(item -> item.getName().equalsIgnoreCase(itemName))
					.findFirst()
					.orElse(null);
			{
				try {
					if (item2 != null) {
						personBuyer.buyItem(item2);
						personSeller.sellItem(item2);
						System.out.println(personBuyer.getName() + " with id:" + personBuyer.getPersonId()
								+ " Bought the item: " + item2 + " with wear percentage: " + item2.getPercentageWear()
								+ " from:" + personSeller.getName() + " with id:" + personSeller.getPersonId());
					}
				} catch (InventoryFullException ife) {
					System.out.println("Person with id: " + personBuyer.getPersonId() + " " + ife.getMessage());

				}
			}
		}
		if (item2 == null) {
			System.out.println("The seller doesn't have the item: " + itemName);
		}
	}

	private static void addPerson(ArrayList<Person> people, Person person) {

		System.out.println("The person: " + person + " was succefully addd to our database");
		people.add(person);

	}

	private static void addNewItem(Person person, Item item) {
		
		if (person == null) {
			System.out.println("The person doesn't exist in our database");
		} else if (item == null) {
			System.out.println("The item it's not valid");
		} else {
			try {
				person.addItem(item);
				System.out.println(person + " added succefully to his inventory " + item + "with wear percentage: "
						+ item.getPercentageWear());
			} catch (InventoryFullException ife) {
				System.out.println(ife.getMessage());
			}
		}
	}
}


