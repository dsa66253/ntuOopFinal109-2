package backend;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;

/**
 * You can imagine this class as real person help you to make order in real life.
 * It's recommended that you are familiar with Order object operation.
 * @author kobemary
 *
 */
public final class MealManagerSingle {
	private static MealManagerSingle INSTANCE;
	
	private JSONArray orderArr;
	private ArrayList<Order> orderArrL =  new ArrayList<Order>();
	private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/meal.json");

	
    /**It's use to create ONLY one MeanlManagerSingle object.
     * Because constructor is private, you ONLY can you this method to replace with constructor
     * @return MealManagerSingle type object. you can use this object to operate Meal object
     */
    public static MealManagerSingle getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MealManagerSingle();
        }
        return INSTANCE;
    }
	/**
	 * constructor and will read the meal.json
	 */
	private MealManagerSingle() {
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
		this.orderArr = new JSONArray(jsonString);
		for (int i=0; i<orderArr.length(); i++) {
			Order order = new Order(orderArr.getJSONObject(i));
			orderArrL.add(order);//有實體ArrayList才可以add()
		}
	}
	
	/**
	 * override the Object.toString()
	 */
	public String toString() {
		return orderArrL.toString();
	}
	
	/**
	 * get how many order in our database
	 * @return int type variabel to indicate how many order in our database
	 */
	public int getSize() {
		return orderArrL.size();
	}
	
	/** you can extract certain order in the json file with index
	 * @param index int type variable to indicate which order you want ot get
	 * @return Order type Object for you to operate
	 */
	public Order getOrder(int index) {
		return orderArrL.get(index);
	}
	
	/**
	 * it's usually used when you want to create new meal order
	 * @param _order Order type object you create
	 */
	public void addOrder(Order _order) {
		orderArrL.add(_order);
	}
	/**
	 * It's used to remove certain order by index.
	 * @param _index int type to indicate which order you want to drop
	 */
	public void removeOrder(int _index) {
		orderArrL.remove(_index);
	}

	
	/** call this function when you finish all of the changes you made
	 * 	and it will overwrite the original json file
	 */
	public void save() {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(orderArrL.toString());
			writer.close();
		}catch(Exception e) {
			System.out.println("Saving file went wrong");
		}
		System.out.println("The file has been saved");
	}
	
	/** call this function when you finish all of the changes you made
	 * 	and it will overwrite the create a new file under the same directory
	 * @param newFileName String type to indicate your file name
	 */
	public void save(String newFileName) {
		try {
			FileWriter writer = new FileWriter(System.getProperty("user.dir")+"/src/backend/file/"+newFileName);
			writer.write(orderArrL.toString());
			writer.close();
		}catch(Exception e) {
			System.out.println("Saving file went wrong");
		}
		System.out.println("The file has been saved with new file name:"+newFileName);
	}
	
	public static void main(String[] args) {
		MealManagerSingle mm = MealManagerSingle.getInstance();//get singleoton object
		System.out.println(Integer.toHexString(System.identityHashCode(mm)));//get address of singleoton mm
		System.out.println(mm.toString());
		System.out.println(mm.getSize());
		
		Order o = mm.getOrder(0);
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
		// you have to you add order before you save the file
		mm.addOrder(no);
		System.out.println(mm.toString());
		// you can call MealManager save() to save all of the change you made
		// if you don't want to overwrite the original meal.json file, use overloaded save()
//		mm.save();
		
		
		
		

	}

}
