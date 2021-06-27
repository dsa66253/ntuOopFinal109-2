package backend;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;
import org.json.*;

public class MealPrice {
	private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/mealPrice.json");
	private ArrayList<JSONObject> mealPriceArrL = new ArrayList<JSONObject>();
	private JSONArray mealPriceArr;
	
	public void remove(int index) {
		mealPriceArrL.remove(index);
	}
	public void add(String _code, String _totalPrice) {
		JSONObject tmp = new JSONObject("{\"code\":"+_code+",\"totalPrice\":"+_totalPrice+"}");
		mealPriceArrL.add(tmp);
	}
	public void add() {
		JSONObject tmp = new JSONObject("{\"code\":\"null\",\"totalPrice\":\"0\"}");
		mealPriceArrL.add(tmp);
	}

	
	public String toString() {
		return mealPriceArrL.toString();
	}
	public int getSize() {
		return mealPriceArrL.size();
	}
	public String getCode(int index) {
		return mealPriceArrL.get(index).getString("code");
	}
	public String getTotalPrice(int index){
		return mealPriceArrL.get(index).getString("totalPrice");
	}
	public void setCode(int index, String _code) {
		mealPriceArrL.get(index).put("code", _code);
	}
	public void setTotalPrice(int index, String _totalPrice) {
		mealPriceArrL.get(index).put("totalPrice", _totalPrice);
	}
	public void save() {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(mealPriceArrL.toString());
			writer.close();
		}catch(Exception e) {
			System.out.println("Saving file went wrong");
		}
		//System.out.println("The file has been saved");
	}
	public void save(String newFileName) {
		try {
			FileWriter writer = new FileWriter(System.getProperty("user.dir")+"/src/backend/file/"+newFileName);
			writer.write(mealPriceArrL.toString());
			writer.close();
		}catch(Exception e) {
			System.out.println("Saving file went wrong");
		}
		System.out.println("The file has been saved with new file name:"+newFileName);
	}
	
	public MealPrice() {
		String jsonString = new String();
		try {
			File file =  new File(filePath);
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				jsonString = jsonString + scan.nextLine();
			}
			
		}catch(Exception e) {
			System.out.println("There may be something wrong with file path:"+filePath);
		}
		this.mealPriceArr = new JSONArray(jsonString);
		for (int i=0; i<mealPriceArr.length(); i++) {
			JSONObject tmp = new JSONObject(mealPriceArr.getJSONObject(i).toString());
			mealPriceArrL.add(tmp);//有實體ArrayList才可以add()
		}
	}
	public static void main(String[] args) {
		MealPrice m = new MealPrice();
		System.out.println(m.toString());
		System.out.println(m.getCode(0));
		System.out.println(m.getTotalPrice(0));
		//set Code
		m.setCode(1, "r09525126");
		m.setTotalPrice(1, "150");
		
		System.out.println(m.toString());
		System.out.println(m.getCode(1));
		System.out.println(m.getTotalPrice(1));
		
		//add new 
		m.add("firstAdd", "10000");//你可以在add就直接數入code totalPrice的key，也可以之後再用setter設定
		System.out.println(m.toString());
		
		m.add();//之後用setter設定欄位
		System.out.println(m.toString());
		m.setCode(3, "secondAdd");
		m.setTotalPrice(3, "150");
		System.out.println(m.toString());
		
		//save the file
//		m.save();
		
		

	}

}
