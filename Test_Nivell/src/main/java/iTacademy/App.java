package iTacademy;

import java.util.ArrayList;
import java.util.Scanner;

import personHerencia.Person;

public class App {

	public static void main(String[] args) {

		ArrayList<Person> people = InicialitzeDataBase.createPeople();
		
		int option;
		do {
			option = menu();

			switch (option) {
			case 1:
				int id = Request.requestId();
				Person person = Finder.findPerson(people, id);
				Print.showPersonInventory(person,id);
				break;
			case 2:
				String city = Request.requestCity();
				Print.showCityPeople(people, city);
				break;
			case 3:
				String city2 = Request.requestCity();
				Item item = Finder.findLowestItemCity(people, city2);
				Print.printLowestItemCity(item, city2);
				break;
			case 4:
				String type = Request.requestItemType();
				ArrayList<Item> items = Finder.findItemsType(people, type);
				Print.printItemsType(items, type);
				break;
			case 5:
				int id1 = Request.requestBuyerId();
				int id2 = Request.requestSellerId();
				String itemName = Request.requestItemName();
				Person personBuyer = Finder.findPerson(people, id1);
				Person personSeller = Finder.findPerson(people, id2);
				Transaction.buyItem(personBuyer, personSeller, itemName);
				break;
			case 6:
				Person person2 = Request.requestPersonData();
				Transaction.addPerson(people, person2);
				break;
			case 7:
				Item newItem = Request.requestNewItem();
				int id3 = Request.requestId();
				Person person3 = Finder.findPerson(people, id3);
				Transaction.addNewItem(person3, newItem);
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
}