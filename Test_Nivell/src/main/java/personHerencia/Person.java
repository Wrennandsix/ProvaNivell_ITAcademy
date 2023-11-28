package personHerencia;

import java.util.ArrayList;

import iTacademy.InventoryFullException;
import iTacademy.Item;

	public abstract  class Person {
		
	    protected String name;
	    protected String city;
	    protected ArrayList<Item> inventory;
	    protected int id;
	    private static int nextId = 0;
	   
		public Person(String name, String city) {
			super();
			this.id = nextId++;
			this.inventory = new ArrayList<Item>();
			this.name = name;
			this.city = city;
			
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public ArrayList<Item> getInventory() {
			return inventory;
		}
		public void setInventory(ArrayList<Item> inventory) {
			this.inventory = inventory;
		}
		
	    public int getPersonId() {
	        return id;
	    }
	    
	    public abstract void sellItem(Item item);
	    
		public abstract void addItem(Item item) throws InventoryFullException;
		
	    public abstract void buyItem(Item item) throws InventoryFullException;
	    
	    public abstract void showInventory();
	    
	    public abstract Item lowestPricedItem();
	    	    
	}

