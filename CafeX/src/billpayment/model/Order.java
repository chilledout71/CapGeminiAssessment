package billpayment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author leeharris Bean that will hold the current order
 */
public class Order {

	private List<Item> orderedItems;

	public List<Item> getOrderedItems() {
		return orderedItems;
	}

	public void addItemToOrder(Item menuItem) {
		if (orderedItems == null) {
			orderedItems = new ArrayList<Item>();

		}
		this.orderedItems.add(menuItem);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		orderedItems.stream()
				.forEach(m -> sb.append(m.toString()).append("\n"));
		return sb.toString();

	}
}
