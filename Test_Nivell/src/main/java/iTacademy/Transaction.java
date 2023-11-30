package iTacademy;

import java.util.ArrayList;

import personHerencia.Person;

public class Transaction {
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
	public static void addPerson(ArrayList<Person> people, Person person) {

		System.out.println("The person: " + person + " was succefully added to our database");
		people.add(person);

	}

	public static void addNewItem(Person person, Item item) {
		
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
