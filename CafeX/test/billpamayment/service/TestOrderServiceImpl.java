package billpamayment.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import billpayment.model.BillPaymentConstants;
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
		menu.addItemToMenu("COLA", new Item("Cola", FoodType.COLD_DRINK,
				new BigDecimal(0.5).setScale(2, RoundingMode.HALF_EVEN)));
		menu.addItemToMenu("COFFEE", new Item("Coffee", FoodType.HOT_DRINK,
				new BigDecimal(1).setScale(2, RoundingMode.HALF_EVEN)));
		menu.addItemToMenu("CHEESE_SANDWICH",
				new Item("Cheese Sandwich", FoodType.COLD_FOOD, new BigDecimal(
						2).setScale(2, RoundingMode.HALF_EVEN)));
		menu.addItemToMenu("STEAK_SANDWICH",
				new Item("Steak Sandwich", FoodType.HOT_FOOD, new BigDecimal(
						4.5).setScale(2, RoundingMode.HALF_EVEN)));
	}

	@Test
	public void addOneItemToOrder() {

		Item item = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item);
		assertTrue(orderService.getOrder().getOrderedItems().size() == 1);
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
	public void testTotalCost() {

		Item item1 = menu.getMenuItem("COLA");

		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("COFFEE");

		orderService.addItemToOrder(item2);

		assertTrue(orderService.getTotalOrderCosts().equals(
				item1.getPrice().add(item2.getPrice())));

	}

	@Test
	public void testItemPriceList() {

		Item item1 = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("COFFEE");
		orderService.addItemToOrder(item2);

		assertTrue(orderService.getItemisedCostList().get(0)
				.equals(item1.getPrice()));
		assertTrue(orderService.getItemisedCostList().get(1)
				.equals(item2.getPrice()));

	}

	@Test
	public void testColdFoodItemServiceCharge() {

		Item item1 = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("CHEESE_SANDWICH");
		orderService.addItemToOrder(item2);

		BigDecimal serviceCharge = item1.getPrice().add(item2.getPrice())
				.multiply(BillPaymentConstants.COLD_FOOD_SERVICE_CHARGE).setScale(2, RoundingMode.HALF_EVEN);

		assertTrue(orderService.calculateServiceCharge().equals(serviceCharge));
	}
	
	@Test 
	public void testServiceChargeNoFood(){

		Item item1 = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("COFFEE");
		orderService.addItemToOrder(item2);

		orderService.addItemToOrder(item2);
		
		assertTrue(orderService.calculateServiceCharge().equals( new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN)));
	}

	
	@Test
	public void testHotFoodItemServiceChargeBelowMaximum() {

		Item item1 = menu.getMenuItem("COLA");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("STEAK_SANDWICH");
		orderService.addItemToOrder(item2);

		BigDecimal serviceCharge = item1.getPrice().add(item2.getPrice())
				.multiply(BillPaymentConstants.HOT_FOOD_SERVICE_CHARGE).setScale(2, RoundingMode.HALF_EVEN);

		assertTrue(orderService.calculateServiceCharge().equals(serviceCharge));
	}
	
	@Test
	public void testHotFoodItemServiceChargeOverMaximum(){
		//Service charge would be 20.7 so should return 20
		for(int i = 0; i<23;i++){
			orderService.addItemToOrder(menu.getMenuItem("STEAK_SANDWICH"));
		
		}
		
		assertTrue(orderService.calculateServiceCharge().equals(new BigDecimal(20).setScale(2)));
		
		
		
	}
	
	@Test
	public void testColdFoodHasNoMaxServiceCharge(){
		//Service charge will be 20.4 , no max applied
		for(int i = 0; i<51;i++){
			orderService.addItemToOrder(menu.getMenuItem("CHEESE_SANDWICH"));
		}
		BigDecimal charge = menu.getMenuItem("CHEESE_SANDWICH").getPrice().multiply(new BigDecimal(51)).multiply(BillPaymentConstants.COLD_FOOD_SERVICE_CHARGE);
		

		assertTrue(orderService.calculateServiceCharge().equals(charge.setScale(2,RoundingMode.HALF_EVEN)));
		
	}
	
	@Test
	public void testFinalCost(){
		
		Item item1 = menu.getMenuItem("COFFEE");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("CHEESE_SANDWICH");

		orderService.addItemToOrder(item2);

		Item item3 = menu.getMenuItem("STEAK_SANDWICH");

		orderService.addItemToOrder(item3);

		BigDecimal charge = item1.getPrice().add(item2.getPrice()).add(item3.getPrice()).multiply(BillPaymentConstants.HOT_FOOD_SERVICE_CHARGE).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal fullCost = charge.add(item1.getPrice()).add(item2.getPrice()).add(item3.getPrice()); 
		assertTrue(orderService.getFullCost().equals(fullCost));
		
		
	}
	
	@Test
	public void testFullBillPrint(){
		Item item1 = menu.getMenuItem("COFFEE");
		orderService.addItemToOrder(item1);

		Item item2 = menu.getMenuItem("CHEESE_SANDWICH");

		orderService.addItemToOrder(item2);

		Item item3 = menu.getMenuItem("STEAK_SANDWICH");

		orderService.addItemToOrder(item3);

		BigDecimal charge = item1.getPrice().add(item2.getPrice()).add(item3.getPrice()).multiply(BillPaymentConstants.HOT_FOOD_SERVICE_CHARGE).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal fullCost = charge.add(item1.getPrice()).add(item2.getPrice()).add(item3.getPrice()); 
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("[").append(item1.getPrice()).append( ", ").append(item2.getPrice()).append(", ").append(item3.getPrice()).append("]"); //[[1.00, 2.00, 4.50]
		sb.append(System.getProperty("line.separator"));
		sb.append("Total of items is " + item1.getPrice().add(item2.getPrice()).add(item3.getPrice()));
		sb.append(System.getProperty("line.separator"));
		sb.append("Service Charge is " + charge);
		sb.append(System.getProperty("line.separator"));
		sb.append("Grand Total is " + fullCost);
	
		
		assertTrue(orderService.printFullBill().equals(sb.toString()));
		
	}

}
