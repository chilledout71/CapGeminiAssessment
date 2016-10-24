package billpayment.model;

import java.math.BigDecimal;

public class BillPaymentConstants {
	public static final BigDecimal COLD_FOOD_SERVICE_CHARGE = new BigDecimal(0.1); //10 % service charge for food
	public static final BigDecimal HOT_FOOD_SERVICE_CHARGE = new BigDecimal(0.2); //10 % service charge for food
	public static final BigDecimal MAXIMUM_HOT_CHARGE = new BigDecimal(20);
}
