package personHerencia;

import iTacademy.InventoryFullException;
import iTacademy.Item;

public class Merchant extends Person  {

	private final int MAX_ITEMS = 7;
	private final double TAX = 0.04;
	private final double WEARLOSE = 1;
	private final String TYPE = "Merchant";

	public Merchant(String name, String city) {
		super(name,city);
	}

	public void addItem(Item item) throws InventoryFullException {
		try {
			if (super.inventory.size() <= this.MAX_ITEMS) {
				item.setWearPercentage(item.getPercentageWear() * this.WEARLOSE);
				item.setPrice(item.getPrice() + item.getPrice() * this.TAX);
				super.inventory.add(item);
			} else {
				throw new InventoryFullException();
			}
		} catch (InventoryFullException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void buyItem(Item item) throws InventoryFullException {
		try {
			if (inventory.size() >= this.MAX_ITEMS) {
				throw new InventoryFullException();
			} else {
				item.setWearPercentage(item.getPercentageWear() * this.WEARLOSE);
				item.setPrice(item.getPrice() + item.getPrice() * this.TAX);
				inventory.add(item);
			}
		} catch (InventoryFullException ex) {
			System.out.println(ex.getMessage());
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
		return "Merchant [TYPE=" + TYPE + ", name=" + name + ", city=" + city + ", id=" + id + "]";
	}

}