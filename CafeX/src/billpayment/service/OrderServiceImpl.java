package billpayment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

}
