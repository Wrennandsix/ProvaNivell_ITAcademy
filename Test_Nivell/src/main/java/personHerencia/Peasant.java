package personHerencia;

import iTacademy.InventoryFullException;
import iTacademy.Item;

public class Peasant extends Person {

	private final int MAX_ITEMS = 5;
	private final double TAX = 0.02;
	private final double WEARLOSE = 0.15;
	private final String TYPE = "Peasant";

	public Peasant(String name, String city) {
		super(name, city);
	}

	public void addItem(Item item) throws InventoryFullException {

		if (super.inventory.size() <= this.MAX_ITEMS) {
			item.setWearPercentage(item.getPercentageWear() - item.getPercentageWear() * this.WEARLOSE);
			item.setPrice(item.getPrice() + item.getPrice() * this.TAX);
			super.inventory.add(item);
		} else {
			throw new InventoryFullException();
		}
	}

	public void buyItem(Item item) throws InventoryFullException {

		if (inventory.size() >= this.MAX_ITEMS) {
			throw new InventoryFullException();
		} else {
			item.setWearPercentage(item.getPercentageWear() - item.getPercentageWear() * this.WEARLOSE);
			item.setPrice(item.getPrice() + item.getPrice() * this.TAX);
			inventory.add(item);
		}
	}

	public void sellItem(Item item) {
		inventory.remove(item);
	}

	public String getTYPE() {
		return TYPE;
	}

	public void showInventory() {

		if (inventory.isEmpty()) {
			System.out.println("This person has no items.");
		}
		for (Item item : inventory) {
			System.out.println(item);
		}
	}

	@Override
	public Item lowestPricedItem() {
		double lowest = Double.POSITIVE_INFINITY;
		Item lowestPricedItem = null;

		for (Item item : inventory) {
			if (item.getPrice() < lowest) {
				lowest = item.getPrice();
				lowestPricedItem = item;
			}
		}
		return lowestPricedItem;
	}

	@Override
	public String toString() {
		return "Farmer [TYPE=" + TYPE + ", name=" + name + ", city=" + city + ", id=" + id + "]";
	}
}