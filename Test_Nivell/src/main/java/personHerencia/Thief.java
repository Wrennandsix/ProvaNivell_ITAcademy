package personHerencia;

import java.util.Comparator;

import iTacademy.InventoryFullException;
import iTacademy.Item;

public class Thief extends Person {

	private final int MAX_ITEMS = 3;
	private final double TAX = 1;
	private final double WEARLOSE = 0.25;

	public Thief(String name, String city) {
		super(name, city);

	}

	public void addItem(Item item) throws InventoryFullException {

		if (super.inventory.size() <= this.MAX_ITEMS) {
			item.setWearPercentage(item.getPercentageWear() - item.getPercentageWear() * this.WEARLOSE);
			item.setPrice(item.getPrice() * this.TAX);
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
			item.setPrice(item.getPrice() * this.TAX);
			inventory.add(item);
		}

	}

	public void sellItem(Item item) {
		inventory.remove(item);
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
        return inventory.stream()
                .min(Comparator.comparingDouble(Item::getPrice))
                .orElse(null);
    }

	@Override
	public String toString() {
		return "Thief [name=" + name + ", city=" + city + ", id=" + id + "]";
	}

}
