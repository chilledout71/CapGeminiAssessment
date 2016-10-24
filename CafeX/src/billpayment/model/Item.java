package billpayment.model;

import java.math.BigDecimal;

/** model object that represents an item off the menu**/
public class Item {
	
private String name;
private FoodType foodType;
private BigDecimal price;
public String getName() {
	return name;
}
public FoodType getFoodType() {
	return foodType;
}
public BigDecimal getPrice() {
	return price;
}
public Item(String name, FoodType foodType, BigDecimal bigDecimal) {
	super();
	this.name = name;
	this.foodType = foodType;
	this.price = bigDecimal;
}


	

}
