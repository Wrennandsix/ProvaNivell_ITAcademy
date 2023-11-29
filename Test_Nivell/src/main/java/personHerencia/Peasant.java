package personHerencia;

import java.util.Comparator;

import iTacademy.InventoryFullException;
import iTacademy.Item;

public class Peasant extends Person {

	private final int MAX_ITEMS = 5;
	private final double TAX = 0.02;
	private final double WEARLOSE = 0.15;


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

	public Item lowestPricedItem() {
        return inventory.stream()
                .min(Comparator.comparingDouble(Item::getPrice))
                .orElse(null);
    }

	@Override
	public String toString() {
		return "Farmer [name=" + name + ", city=" + city + ", id=" + id + "]";
	}
}