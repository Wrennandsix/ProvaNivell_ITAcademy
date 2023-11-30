package iTacademy;

import java.util.Scanner;

import personHerencia.Merchant;
import personHerencia.Peasant;
import personHerencia.Person;
import personHerencia.Thief;

public class Request {
	
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
			option = Input.llegirInt("Input 1 for Merchant\n" + "Input 2 for Peasant\n" + "Input 3 for Thief");
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
}