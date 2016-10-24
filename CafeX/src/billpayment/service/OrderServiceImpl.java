package billpayment.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import billpayment.model.BillPaymentConstants;
import billpayment.model.FoodType;
import billpayment.model.Item;
import billpayment.model.Order;

public class OrderServiceImpl implements OrderService {

	private Order order;

	@Override
	public void addItemToOrder(Item item) {
		if (order == null) {
			order = new Order();
		}
		order.addItemToOrder(item);

	}

	@Override
	public Order getOrder() {

		return order;
	}

	@Override
	public BigDecimal getTotalOrderCosts() {
		BigDecimal total = order.getOrderedItems().stream().map(Item::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}

	@Override
	public List<BigDecimal> getItemisedCostList() {
		List<BigDecimal> itemCostList = new ArrayList<BigDecimal>();

		order.getOrderedItems().stream()
				.forEach(item -> itemCostList.add(item.getPrice()));

		return itemCostList;
	}

	@Override
	public BigDecimal calculateServiceCharge() {
		BigDecimal charge = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);

		
		/// check for  hot food items, if this is greater than 0 use the hot food
		// charge
		if (order.getOrderedItems().stream()
				.filter(item -> item.getFoodType() == FoodType.HOT_FOOD)
				.count() > 0) {

			charge = this.getTotalOrderCosts().multiply(
					BillPaymentConstants.HOT_FOOD_SERVICE_CHARGE);
			if(charge.compareTo(BillPaymentConstants.MAXIMUM_HOT_CHARGE)>0){
				charge = BillPaymentConstants.MAXIMUM_HOT_CHARGE;
			}
			
		}
		// else check for cold food items, if this is greater than 0 use the cold food
		// charge
		else if (order.getOrderedItems().stream()
				.filter(item -> item.getFoodType() == FoodType.COLD_FOOD)
				.count() > 0) {

			charge = this.getTotalOrderCosts().multiply(
					BillPaymentConstants.COLD_FOOD_SERVICE_CHARGE);
		}

		return charge.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	@Override
	public BigDecimal getFullCost() {
		BigDecimal finalCost = this.getTotalOrderCosts().add(
				this.calculateServiceCharge()).setScale(2,
				RoundingMode.HALF_EVEN);
		return finalCost;
	}

	@Override
	public String printFullBill() {
		StringBuffer sb = new StringBuffer();
		sb.append(getItemisedCostList());
		sb.append(System.getProperty("line.separator"));
		sb.append("Total of items is " + getTotalOrderCosts());
		sb.append(System.getProperty("line.separator"));
		sb.append("Service Charge is " + calculateServiceCharge());
		sb.append(System.getProperty("line.separator"));
		sb.append("Grand Total is " + getFullCost());
		return sb.toString();
	}

}
