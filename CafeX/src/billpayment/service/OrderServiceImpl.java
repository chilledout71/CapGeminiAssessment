package billpayment.service;

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
		// TODO Auto-generated method stub
		return order;
	}

}
