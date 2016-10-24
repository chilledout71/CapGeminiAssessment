package billpamayment.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import billpayment.model.FoodType;
import billpayment.model.Item;
import billpayment.model.Menu;
import billpayment.service.OrderService;
import billpayment.service.OrderServiceImpl;

public class TestOrderServiceImpl {
	
	private Menu menu;
	private OrderService orderService;
	
	@Before
	public void setUp() throws Exception {
		orderService = new OrderServiceImpl();
		
		 menu = new Menu();
		menu.addItemToMenu("COLA", new Item("Cola",FoodType.COLD_DRINK, new BigDecimal(0.5).setScale(2,RoundingMode.HALF_EVEN)));
		menu.addItemToMenu("COFFEE", new Item("Coffee",FoodType.HOT_DRINK, new BigDecimal(1).setScale(2,RoundingMode.HALF_EVEN)));
		menu.addItemToMenu("CHEESE_SANDWICH", new Item("Cheese Sandwich",FoodType.COLD_FOOD, new BigDecimal(2).setScale(2,RoundingMode.HALF_EVEN)));
		menu.addItemToMenu("STEAK_SANDWICH", new Item("Steak Sandwich",FoodType.HOT_FOOD, new BigDecimal(4.5).setScale(2,RoundingMode.HALF_EVEN)));
	}
	
	
	@Test
	public void addOneItemToOrder(){
		
		Item item = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item);
		assertTrue(orderService.getOrder().getOrderedItems().size() ==1);
		assertTrue(orderService.getOrder().getOrderedItems().get(0) == item);
		
		
	}
	@Test
	public void testAddTwoItemsToOrder() {

		Item item1 = menu.getMenuItem("COLA");

		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("COFFEE");

		orderService.addItemToOrder(item2);

		assertTrue(orderService.getOrder().getOrderedItems().size() == 2);
		assertTrue(orderService.getOrder().getOrderedItems().get(0) == item1);
		assertTrue(orderService.getOrder().getOrderedItems().get(1) == item2);
	}
	
	
	
	@Test
	public void testTotalCost(){


		Item item1 = menu.getMenuItem("COLA");

		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("COFFEE");

		orderService.addItemToOrder(item2);
		
		assertTrue(orderService.getTotalOrderCosts().equals(item1.getPrice().add(item2.getPrice())));

	
	}
	
	@Test
	public void testItemPriceList(){
		
		Item item1 = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("COFFEE");
		orderService.addItemToOrder(item2);
		
		assertTrue(orderService.getItemisedCostList().get(0).equals(item1.getPrice() ));
		assertTrue(orderService.getItemisedCostList().get(1).equals(item2.getPrice()));
		
	}
	
	
	

}
