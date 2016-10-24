package billpamayment.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import billpayment.model.FoodType;
import billpayment.model.Item;
import billpayment.service.OrderService;
import billpayment.service.OrderServiceImpl;

public class TestOrderServiceImpl {

	@Before
	public void setUp() throws Exception {
		orderService = new OrderServiceImpl();

	}
	
	private OrderService orderService;
	@Test
	public void addOneItemToOrder(){
		
		Item item = new Item("Cola",FoodType.COLD_DRINK, new BigDecimal(2.99).setScale(2,RoundingMode.HALF_EVEN));
		
		orderService.addItemToOrder(item);
		assertTrue(orderService.getOrder().getOrderedItems().size() ==1);
		assertTrue(orderService.getOrder().getOrderedItems().get(0) == item);
		
		
	}
	@Test
	public void testAddTwoItemsToOrder() {

		Item item1 = new Item("Coffee", FoodType.HOT_DRINK, new BigDecimal(1).setScale(2,RoundingMode.HALF_EVEN));

		orderService.addItemToOrder(item1);

		Item item2 = new Item("Cola", FoodType.COLD_DRINK, new BigDecimal(2.99).setScale(2,RoundingMode.HALF_EVEN));

		orderService.addItemToOrder(item2);

		assertTrue(orderService.getOrder().getOrderedItems().size() == 2);
		assertTrue(orderService.getOrder().getOrderedItems().get(0) == item1);
		assertTrue(orderService.getOrder().getOrderedItems().get(1) == item2);
	}
	

}
