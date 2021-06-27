package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.*;

/**
 * This is the class used to operate price.json
 * @author kobemary
 *
 */
public class Price {
	
	private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/price.json");
	private JSONArray priceArr;
	private ArrayList<JSONObject> priceArrL = new ArrayList<JSONObject>();
	

	
	/**
	 * 通常拿來取 TicketType or Price
	 * @param priceIndex int type to indicate index of price.json array
	 * @param DesrinationStationsIndex int type to indicate index of DesrinationStations array
	 * @param FaresIndex int type to indicate index of Fares array
	 * @param location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int priceIndex, int DesrinationStationsIndex, int FaresIndex, String... location) {
		switch(location.length) {
		case 3:
			//get TicketType Price
			return "" + priceArrL.get(priceIndex).getJSONArray(location[0]).getJSONObject(DesrinationStationsIndex).getJSONArray(location[1]).getJSONObject(FaresIndex).get(location[2]);
		default:
			return "There may be something wrong with your location array: "+ Arrays.toString(location);
		}

	}
	
	/**
	 * 通常拿來取 ID or Direction
	 * @param priceIndex int type to indicate index of price.json array
	 * @param DesrinationStationsIndex int type to indicate index of DesrinationStationsIndex array
	 * @param location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int priceIndex, int DesrinationStationsIndex, String... location) {
		switch(location.length) {
		case 2:
			// get ID Direction
			if (location[1].equals("Direction")) {
				return "" + priceArrL.get(priceIndex).getJSONArray(location[0]).getJSONObject(DesrinationStationsIndex).getInt(location[1]);
			}
			else {
				return priceArrL.get(priceIndex).getJSONArray(location[0]).getJSONObject(DesrinationStationsIndex).getString(location[1]);
			}
		
		default:
			return "There may be something wrong with your location array: "+ Arrays.toString(location);
		}
	}
	
	/**
	 * use to get OriginStationID
	 * @param priceIndex int type to indicate index of price.json array
	 * @param location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int priceIndex, String... location) {
		// get OriginStationID 
		switch(location.length) {
		case 1:
			return priceArrL.get(priceIndex).getString(location[0]);
		case 2:
			//return priceArrL.get(priceIndex).getJSONArray(location[0])location;
		default:
			return "There may be something wrong with your location array: "+ Arrays.toString(location);
		}
	}
	
	
	public  String toString(){
		return priceArrL.toString();
	}
	public Price(){
		String jsonString = new String();
		try {
			// load file
			File file =  new File(filePath);
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				jsonString = jsonString + scan.nextLine();
			}
		}catch(Exception e) {
			System.out.println("There may be something wrong with file path:"+filePath);
		}
		this.priceArr = new JSONArray(jsonString);
		
		// store as AarrayList
		for (int i=0; i<priceArr.length(); i++) {
			JSONObject tmp = new JSONObject(priceArr.getJSONObject(i).toString());
			priceArrL.add(tmp);
		}
	}
	public Price(String path){
		String jsonString = new String();
		try {
			// process file path
			String path2 = new String("/Users/kobemary/OOP/termProject/src");
			String relativePath = new String("/backend/file/price.json");
			String fullPath = new String(path + relativePath);
			
			// load file
			File file =  new File(fullPath);
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				jsonString = jsonString + scan.nextLine();
			}
			
			
		}catch(Exception e) {
			System.out.println("load station.json file error");
		}
		//System.out.println(jsonString);
		this.priceArr = new JSONArray(jsonString);
		
		// store as AarrayList
		for (int i=0; i<priceArr.length(); i++) {
			JSONObject tmp = new JSONObject(priceArr.getJSONObject(i).toString());
			priceArrL.add(tmp);
		}
	}
	
	

	public static void main(String[] args) {
		Price p = new Price();
		System.out.println(p.toString());
		System.out.println(p.get(0, "OriginStationID"));
		System.out.println(p.get(0, 0, "DesrinationStations", "ID"));
		System.out.println(p.get(0, 0, "DesrinationStations", "Direction"));
		System.out.println(p.get(0, 0, 0, "DesrinationStations", "Fares", "TicketType"));
		System.out.println(p.get(0, 0, 1, "DesrinationStations", "Fares", "Price"));
		

	}

}
