package billpayment.model;

import java.util.HashMap;

public class Menu {

	private HashMap<String,Item> menuItems = new HashMap<String,Item>();
	
	
	public Item getMenuItem(String name){
		return menuItems.get(name);
	}
	
	public void addItemToMenu(String name, Item item){
		menuItems.put(name,item);
	}
}
