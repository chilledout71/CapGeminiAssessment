package billpayment.model;

/**
 * 
 * @author leeharris
 * a enum of all the available food and drink types
 */
public enum FoodType {
	HOT_DRINK("Hot Drink"),
	COLD_DRINK("Cold Drink"),
	HOT_FOOD("Hot Food"),
	COLD_FOOD("Cold Food");
	
	private String type;
	private FoodType(String type){
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
	
}
