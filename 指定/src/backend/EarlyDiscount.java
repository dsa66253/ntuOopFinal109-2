package backend;

import java.io.File;
import java.util.Scanner;

import org.json.JSONObject;

public class EarlyDiscount {
    private static final String filePath = new String(System.getProperty("user.dir") + "/src/backend/file/earlyDiscount.json");
    private JSONObject earlyDiscountJSONObj;

    public String get(int DiscountTrainsIndex, int WeekDayIndex, String... location) {    //若該日折扣非 JSONArray (如 0, 1) 會出事
        return location[3].equals("discount") ? "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(WeekDayIndex).getDouble(location[3]) : "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(WeekDayIndex).getInt(location[3]);
    }

    public String get(int DiscountTrainsIndex, String... location) {    //為配合輸出格式 這邊有大幅改動 不必輸入 WeekDayIndex 就可以輸出該日折扣
        if(location.length == 3 && location[1].equals("ServiceDayDiscount")) {
            String discountDisplay = "";
            if (this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).get(location[2]).equals(0) ||
                this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).get(location[2]).equals(1)){
                discountDisplay = "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).get(location[2]);
            }
            else {
                switch (this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).length()) {
                    case 3:
                        discountDisplay = "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(2).getDouble("discount") + ", ";
                    case 2:
                        discountDisplay += "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(1).getDouble("discount") + ", ";
                    case 1:
                        discountDisplay += "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getJSONArray(location[2]).getJSONObject(0).getDouble("discount");
                    default:
                        break;
                }
            }
            return discountDisplay;
        }
        return "" + this.earlyDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).get(location[1]);
    }

    public String get(String... location) {
        return this.earlyDiscountJSONObj.getString(location[0]);
    }

    public String toString() {
        return this.earlyDiscountJSONObj.toString();
    }

    public EarlyDiscount() {
        String jsonString = new String();

        try {
            File file = new File(filePath);

            for(Scanner scan = new Scanner(file); scan.hasNext(); jsonString = jsonString + scan.nextLine()) {
            }
        } catch (Exception var4) {
            System.out.println("There may be something wrong with file path:" + filePath);
        }

        this.earlyDiscountJSONObj = new JSONObject(jsonString);
    }

    public static void main(String[] args) throws backend.BadArguments {
        EarlyDiscount e = new EarlyDiscount();
        System.out.println(e.toString());
        System.out.println(e.get("ExpiringDate"));
        System.out.println(e.get(0, "DiscountTrains", "TrainNo"));
        System.out.println(e.get(0, 0, "DiscountTrains", "ServiceDayDiscount", "Saturday", "discount"));
        System.out.println(e.get(0, 0, "DiscountTrains", "ServiceDayDiscount", "Monday", "tickets"));
    }
}
