package iTacademy;

import java.util.ArrayList;

import personHerencia.Merchant;
import personHerencia.Peasant;
import personHerencia.Person;
import personHerencia.Thief;

public class InicialitzeDataBase {
	
	public static ArrayList<Person> createPeople() {

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

