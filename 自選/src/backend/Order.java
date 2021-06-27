package backend;
import java.io.File;
import java.util.Scanner;

import org.json.*;

/**
 * This is a class to operate every order of meal.
 * @author kobemary
 *
 */
public class Order {
	private JSONObject order = new JSONObject("{\"number\":\"2\",\"code\":\"123456789\",\"sumPrice\":\"170\",\"dishMsg\":\"雞腿便當\"}");
	
	/**
	 * default constructor
	 */
	public Order(){
	}
	/**constructor with JSONObject type input
	 * @param _order JSONObject type to assign to private variable
	 */
	public Order(JSONObject _order) {
		this.order = _order;
	}
	/**
	 * override Object.toString()
	 */
	public String toString() {
		return order.toString();
	}
	/**
	 * it's used to get value of code
	 * @return String type value of code
	 */
	public String getCode() {
		return order.getString("code");
	}
	/**
	 * it's used to set the value of code
	 * @param input String type value of code
	 */
	public void setCode(String input) {
		order.put("code", input);
	}
	/**
	 * it's used to get value of dishMsg
	 * @return String type value of disMsg
	 */
	public String getDishMsg() {
		return order.getString("dishMsg");
	}
	/**it's used to set value of dishMsg
	 * @param input String type value of dishMsg
	 */
	public void setDishMsg(String input) {
		order.put("dishMsg", input);
	}
	/**it's used to get value of number
	 * @return String type value of number
	 */
	public String getNumber() {
		return order.getString("number");
	}
	/**it's used to set value of number
	 * @param input String type value of number
	 */
	public void setNumber(String input) {
		order.put("number", input);
	}
	/**it's used to get value of sumPrice
	 * @return String type of value of sumPrice
	 */
	public String getSumPrice() {
		return order.getString("sumPrice");
	}
	/**it's used to set value of sumPrice
	 * @param input String type of value of sumPrice
	 */
	public void setSumPrice(String input) {
		order.put("sumPrice", input);
	}


	public static void main(String[] args) {
		Order o = new Order();
		System.out.println(o);
		System.out.println(o.getCode());
		System.out.println(o.getDishMsg());
		System.out.println(o.getNumber());
		System.out.println(o.getSumPrice());
		// modify order content
		o.setCode("8787");
		o.setDishMsg("friedRice");
		o.setNumber("1");
		o.setSumPrice("10000");
		System.out.println(o.toString());
		// create a new order
		Order no = new Order();
		System.out.println(no.toString());
		no.setCode("r09525126");
		System.out.println(no.toString());


	}

}
