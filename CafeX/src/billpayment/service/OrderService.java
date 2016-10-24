package billpayment.service;

import billpayment.model.Item;
import billpayment.model.Order;


/**
 * @author leeharris
 * Interface to the order service
 */
public interface OrderService {
	/**
	 * add a menu item to the order
	 * @param item
	 */
	public void addItemToOrder(Item item);
	
	/**
	 * get the current order
	 * @return
	 */
	public Order getOrder();
	

}
