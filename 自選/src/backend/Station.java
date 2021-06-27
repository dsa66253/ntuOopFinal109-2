package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.*;


/**
 * This is the class used to handle station.json
 * @author kobemary
 *
 */
public class Station {
	private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/station.json");
	private JSONArray stationArr;
	private ArrayList<JSONObject> stationArrL = new ArrayList<JSONObject>();
	
	/**
	 * 
	 * @param stationIndex int type to indicate the index of station.json array
	 * @param location String type arguments. You MUST specify clearly each key you want to get
	 * @return String type of value
	 */
	public String get(int stationIndex, String... location) {
		switch(location.length) {
		case 1:
			return stationArrL.get(stationIndex).getString(location[0]);
		case 2:
			return stationArrL.get(stationIndex).getJSONObject(location[0]).getString(location[1]);
		default:
			return "There may be something wrong with your location array: "+ Arrays.toString(location);
		}
	}
	
	/**
	 * @return String type containing all station.json content
	 */
	public  String toString(){
		return stationArrL.toString();
	}
	
	Station(){
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
		//System.out.println(jsonString);
		this.stationArr = new JSONArray(jsonString);
		
		// store as AarrayList
		for (int i=0; i<stationArr.length(); i++) {
			JSONObject tmp = new JSONObject(stationArr.getJSONObject(i).toString());
			stationArrL.add(tmp);
		}
	}

//	Station(String path){
//		String jsonString = new String();
//		try {
//			// process file path
//			String path2 = new String("/Users/kobemary/OOP/termProject/src");
//			String relativePath = new String("/backend/file/station.json");
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
//		//System.out.println(jsonString);
//		this.stationArr = new JSONArray(jsonString);
//		
//		// store as AarrayList
//		for (int i=0; i<stationArr.length(); i++) {
//			JSONObject tmp = new JSONObject(stationArr.getJSONObject(i).toString());
//			stationArrL.add(tmp);
//		}
//	}

	public static void main(String[] args) {
		Station s = new Station();
		System.out.println(s.toString());
		System.out.println(s.get(0, "StationID"));
		System.out.println(s.get(0, "StationName", "En"));
		

	}

}
