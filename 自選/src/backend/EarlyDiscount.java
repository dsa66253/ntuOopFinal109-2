package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.*;
/**
 * This is the class to handle earlyDiscount.json
 * @author kobemary
 *
 */
public class EarlyDiscount {
	
	private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/earlyDiscount.json");;
	private JSONObject earlyDiscountJSONObj;
	
	/**
	 * This is used to get discount or tickets
	 * @param DiscountTrainsIndex int type to indicate the index of DiscountTrains array
	 * @param WeekDayIndex int type to indicate the index of Monday, Tuesday, Wednesday, Thursday, or Friday array
	 * @param location location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int DiscountTrainsIndex, int WeekDayIndex, String...location) {
		//get discount tickets
		if (location[3].equals("discount")) {
			// get discount
			return "" + earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(WeekDayIndex).getDouble(location[3]);
		}else {
			// get tickets
			return "" + earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(WeekDayIndex).getInt(location[3]);
		}
		
	}
	
	/**
	 * This is used to get TrainNo
	 * @param DiscountTrainsIndex int type to indicate the index of DiscountTrains array
	 * @param  location location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int DiscountTrainsIndex, String...location) {
		// get TrainNo
		return "" + earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).get(location[1]);
	}
	
	/**
	 * This is used to get UpdateTime EffectiveDate ExpiringDate
	 * @param location location String type arguments. You MUST specify clearly each key you want to get
	 * @return A String type of value
	 */
	public String get(String... location) {
		// get UpdateTime EffectiveDate ExpiringDate
		return earlyDiscountJSONObj.getString(location[0]);
			
	}
	

	
	public String toString() {
		return earlyDiscountJSONObj.toString();
	}
	
	
	

	public EarlyDiscount(){
		String jsonString = new String();
		try {
			// cwd is at termProject
			//System.out.println(System.getProperty("user.dir"));
			
			
			// load file
			File file =  new File(filePath);
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				jsonString = jsonString + scan.nextLine();
			}
		}catch(Exception e) {
			System.out.println("There may be something wrong with file path:"+filePath);
		}
		earlyDiscountJSONObj = new JSONObject(jsonString);
	}
//	EarlyDiscount(String path){
//		String jsonString = new String();
//		try {
//			// cwd is at termProject
//			//System.out.println(System.getProperty("user.dir"));
//			
//			
//			// load file
//			File file =  new File(filePath);
//			Scanner scan = new Scanner(file);
//			while(scan.hasNext()) {
//				jsonString = jsonString + scan.nextLine();
//			}
//		}catch(Exception e) {
//			System.out.println("There may be something wrong with"+filePath);
//		}
//		earlyDiscountJSONObj = new JSONObject(jsonString);
//		
//	}
	

	public static void main(String[] args){
		EarlyDiscount e = new EarlyDiscount();
		System.out.println(e.toString());
		System.out.println(e.get("ExpiringDate"));
		System.out.println(e.get(0, "DiscountTrains", "TrainNo"));
		System.out.println(e.get(0, 0, "DiscountTrains", "ServiceDayDiscount", "Monday", "discount"));
		System.out.println(e.get(0, 0, "DiscountTrains", "ServiceDayDiscount", "Monday", "tickets"));
		

	}

}
