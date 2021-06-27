package backend;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.*;


/**
 * This is the class used to operate universityDiscount.json
 * @author kobemary
 *
 */
public class UniversityDiscount {
	
	private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/universityDiscount.json");
	private JSONObject universityDiscountJSONObj;
	
	
	/**
	 * This is used to get value of TrainNo Monday
	 * @param DiscountTrainsIndex int type to indicate index of DiscountTrains array
	 * @param location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int DiscountTrainsIndex, String...location) {
		switch(location.length) {
		case 2:
			return "" + universityDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).get(location[1]);
		case 3:
			return "" + universityDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getDouble(location[2]);
		default:
			return "There may be something wrong with your location array: "+ Arrays.toString(location);
		}
	}

	
	/**
	 * This is used to get the value of UpdateTime EffectiveDate ExpiringDate
	 * 
	 * @param location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(String... location) {
		return "" + universityDiscountJSONObj.get(location[0]);
		
	}
	
	/**
	 *@return A string that containing all the universityDiscount.json
	 */
	public String toString() {
		return universityDiscountJSONObj.toString();
	}
	
	public UniversityDiscount(){
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
		universityDiscountJSONObj = new JSONObject(jsonString);
	
	}
//	UniversityDiscount(String path){
//		String jsonString = new String();
//		try {
//			// process file path
//			String path2 = new String("/Users/kobemary/OOP/termProject/src");
//			String relativePath = new String("/backend/file/universityDiscount.json");
//			String fullPath = new String(path + relativePath);
//			
//			// load file
//			File file =  new File(fullPath);
//			Scanner scan = new Scanner(file);
//			while(scan.hasNext()) {
//				jsonString = jsonString + scan.nextLine();
//			}
//			
//			
//		}catch(Exception e) {
//			System.out.println("load station.json file error");
//		}
//		universityDiscountJSONObj = new JSONObject(jsonString);
//		
//	}

	public static void main(String[] args) {
		UniversityDiscount u = new UniversityDiscount();
		System.out.println(u.toString());
		System.out.println(u.get("EffectiveDate"));
		System.out.println(u.get("DiscountTrains"));
		System.out.println(u.get(0, "DiscountTrains", "TrainNo"));
		System.out.println(u.get(0, "DiscountTrains", "ServiceDayDiscount"));
		System.out.println(u.get(0, "DiscountTrains", "ServiceDayDiscount", "Monday"));

	}

}
