package personHerencia;


import iTacademy.InventoryFullException;
import iTacademy.Item;

	public class Thief extends Person {

		private final int MAX_ITEMS = 3;
		private final double TAX = 1;
		private final double WEARLOSE = 0.25;
		private final String TYPE = "Thief";

		public Thief(String name, String city) {
			super(name, city);

		}

		public void addItem(Item item) throws InventoryFullException {
			try {
				if (super.inventory.size() <= this.MAX_ITEMS) {
					item.setWearPercentage(item.getPercentageWear() - item.getPercentageWear() * this.WEARLOSE);
					item.setPrice(item.getPrice() * this.TAX);
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
					item.setWearPercentage(item.getPercentageWear() - item.getPercentageWear() * this.WEARLOSE);
					item.setPrice(item.getPrice() * this.TAX);
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
			return "Thief [TYPE=" + TYPE + ", name=" + name + ", city=" + city + ", id=" + id + "]";
		}

	}
