package iTacademy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import personHerencia.Person;

public class Finder {
	
	public static Person findPerson(ArrayList<Person> people, int id) {

		Optional<Person> personFound = people.stream()
				.filter(person -> person.getPersonId() == id)
				.findFirst();

		return personFound.orElse(null);
	}
	public static ArrayList<Item> findItemsType(ArrayList<Person> people, String type) {

		return people.stream()
				.flatMap(person -> person.getInventory().stream())
				.filter(item -> item.getType().equalsIgnoreCase(type))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public static Item findLowestItemCity(ArrayList<Person> people, String city) {

		return people.stream()
				.filter(person -> !person.getInventory().isEmpty() && person.getCity().equalsIgnoreCase(city))
				.flatMap(person -> person.getInventory().stream())
				.min(Comparator.comparingDouble(Item::getPrice))
				.orElseGet(() -> new Item(null, null, 0));
	}

}
