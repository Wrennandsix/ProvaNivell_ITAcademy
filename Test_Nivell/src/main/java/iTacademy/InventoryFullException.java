package iTacademy;

public class InventoryFullException extends Exception {
	
    public InventoryFullException() {
        super("Warning: Full inventory, can't add more items!!");
    }
    public InventoryFullException(String message) {
        super(message);
    }
}
