package billpayment.service;

import java.math.BigDecimal;
import java.util.List;

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
	
	/**
	 * get the total cost of the order
	 * @return 
	 */
	public BigDecimal getTotalOrderCosts();
	
	/**
	 * get the cost of each item in the order
	 * @return
	 */
	public List<BigDecimal> getItemisedCostList();
	
	/**
	 * calculate the service charge for this order
	 * @return
	 */
	public  BigDecimal calculateServiceCharge();
	
	/**
	 * get the final cost 
	 * @return
	 */
	public BigDecimal getFullCost();
	
	/**
	 * print out the full bill
	 * @return
	 */
	public String printFullBill();

}
