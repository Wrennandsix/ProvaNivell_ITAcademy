package iTacademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import personHerencia.Person;

public class Print {

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
	public static void printLowestItemCity(Item item, String city) {
		if (item.getName() == null) {
			System.out.println(
					"The city " + city + " does not exist in our database or all persons in this city have no items");
		} else {
			System.out.println("The lowest priced item in " + city + " is: " + item);
		}
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


}
