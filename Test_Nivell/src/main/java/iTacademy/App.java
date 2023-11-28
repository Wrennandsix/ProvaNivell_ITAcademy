package iTacademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import personHerencia.Peasant;
import personHerencia.Merchant;
import personHerencia.Person;
import personHerencia.Thief;

public class App {

	public static void main(String[] args) {

		ArrayList<Person> people = createPeople();

		int option;
		do {
			option = menu();

			switch (option) {
			case 1:
				int id = requestId();
				Person person = findPerson(people, id);
				showPersonInventory(person);
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
				System.out.println("Input a valid  number, please see the following options:");
			}

		} while (option != 8);
	}

	public static int menu() {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		System.out.println("*** Welcome to city manager ***\n" + "1- Show a seller's items.\n"
				+ "2- Show the sellers in a city.\n"
				+ "3- Show the lowest priced items from all the sellers in a city\n"
				+ "4- Show all items of an item type ordered by price.\n" + "5- Buy an item from another Person.\n"
				+ "6- Create person in our database\n" + "7- Add new item to a Person\n" + "8- Shutt down the app");

		option = sc.nextInt();
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
		System.out.println("Input a valid type for a person that is not in our database(like: Merchant)");
		String type = sc.next();
		System.out.println("Input a valid name for add the person to our database ");
		String name = sc.next();
		String city = requestCity();

		if (type.equalsIgnoreCase("Merchant")) {
			person = new Merchant(name, city);

		} else if (type.equalsIgnoreCase("Peasant")) {
			person = new Peasant(name, city);

		} else if (type.equalsIgnoreCase("Thief")) {
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

		Person personFinded = null;
		boolean finded = false;
		int i = 0;
		while (i < people.size() && finded == false) {
			if (people.get(i).getPersonId() == id) {
				personFinded = people.get(i);
				finded = true;
			}
			i++;
		}

		return personFinded;

	}

	public static void showPersonInventory(Person person) {

		if (person == null) {
			System.out.println("The person is not in our database");
		} else {
			System.out.println("The inventory of " + person.getName() + " is:" + person.getInventory());
		}

	}

	public static void showCityPeople(ArrayList<Person> people, String city) {
		boolean cityFinded = false;
		for (Person person : people) {
			if (person.getCity().equalsIgnoreCase(city)) {
				System.out.println(person);
				cityFinded = true;
			}
		}
		if (cityFinded == false) {
			System.out.println("The city " + city + " is not in our database");
		}
	}

	public static Item findLowestItemCity(ArrayList<Person> people, String city) {

		return people.stream()
				.filter(person -> !person.getInventory().isEmpty() && person.getCity().equalsIgnoreCase(city))
				.flatMap(person -> person.getInventory().stream()).min(Comparator.comparingDouble(Item::getPrice))
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

		return people.stream().flatMap(person -> person.getInventory().stream())
				.filter(item -> item.getType().equalsIgnoreCase(type)).collect(Collectors.toCollection(ArrayList::new));
	}

	public static void printItemsType(ArrayList<Item> items, String type) {

		if (items.isEmpty() == true) {
			System.out.println("The item type: " + type + " does not exist or any person have this item type");
		} else {
			Collections.sort(items, Comparator.comparingDouble(Item::getPrice));

			System.out.println("Items of the type " + type + ":\n" + items);
		}
	}

	public static void buyItem(Person personBuyer, Person personSeller, String itemName) {
		Item item2 = null;

		if (personSeller.getInventory().isEmpty() == true) {
			System.out.println("The seller have no items");
		} else {
			for (Item item : personSeller.getInventory()) {
				if (item.getName().equalsIgnoreCase(itemName)) {
					item2 = item;
					try {
						personBuyer.buyItem(item2);
						personSeller.sellItem(item2);
						System.out.println(personBuyer.getName() + " with id:" + personBuyer.getPersonId()
								+ " Bought the item: " + item2 + " with wear percentage: " + item2.getPercentageWear()
								+ " from:" + personSeller.getName() + " with id:" + personSeller.getPersonId());

					} catch (InventoryFullException ife) {
						System.out.println(ife.getMessage());

					}
				}
			}
		}
		if (item2 == null) {
			System.out.println("The seller doesn't have the item: " + itemName);
		}
	}

	private static void addPerson(ArrayList<Person> people, Person person) {
		if (person == null) {
			System.out.println(" The type of person it's not valid");
		} else {
			System.out.println("The person: " + person + " was succefully addd to our database");
			people.add(person);

		}
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

	private static ArrayList<Person> createPeople() {

		ArrayList<Person> people = new ArrayList<>();

		Merchant carlos = new Merchant("Carlos", "Barcelona");
		people.add(carlos);
		Merchant pepe = new Merchant("Pepe", "Badalona");
		people.add(pepe);

		Thief mariano = new Thief("Mariano", "Barcelona");
		people.add(mariano);
		Thief pedro = new Thief("Pedro", "Badalona");
		people.add(pedro);

		Peasant jhon = new Peasant("Jhon", "Barcelona");
		people.add(jhon);
		Peasant javi = new Peasant("Javi", "Badalona");
		people.add(javi);
		System.out.println("Created People");

		Item onion = new Item("onion", "vegetable", 0.30);
		Item carrot = new Item("carrot", "vegetable", 0.50);
		Item apple = new Item("apple", "fruit", 0.70);
		Item hamburger = new Item("hamburger", "meat", 5.50);
		Item helmet = new Item("helmet", "armor", 99.89);
		Item platelegs = new Item("platelegs", "armor", 152.60);
		Item axe = new Item("axe", "tool", 19.99);
		Item hammer = new Item("hammer", "tool", 57.93);

		System.out.println("Created Items");
		try {
			jhon.addItem(carrot);
			mariano.addItem(onion);
			carlos.addItem(hamburger);
			carlos.addItem(helmet);
			carlos.addItem(platelegs);
			pedro.addItem(hammer);
			pedro.addItem(apple);
			pedro.addItem(axe);

			System.out.println("Added items to people");
		} catch (InventoryFullException ife) {
			System.out.println(ife.getMessage());
		}
		return people;
	}
}
