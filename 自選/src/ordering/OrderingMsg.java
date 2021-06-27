package ordering;
import java.util.Scanner;
import backend.*;

public class OrderingMsg {
	int b = 0;//判斷是否是商務車廂的標記
	String code = "000000000";//使用者將輸入的車票號碼，先initialize，以免出錯
	
/*	public void ResetJson() {
		MealManagerSingle m = MealManagerSingle.getInstance();
		for (int i = 0; i < m.getSize(); i++) {
			m.getOrder(i).setCode("000000000");// 訂餐人
			m.getOrder(i).setDishMsg("空空空空");// 菜品
			m.getOrder(i).setNumber("0");// 份數
			m.getOrder(i).setSumPrice("0");
		}
		m.save();
		MealPrice p = new MealPrice();
		for (int i = 0; i < p.getSize(); i++) {
			p.setCode(i, "000000000");// 訂餐人
			p.setTotalPrice(i, "0");
		}
		p.save();
	}
*/		
	public void MainScreen() { //主畫面
		while (true) {
			Login(); //登入
			OrderingSystem(); //訂餐系統
		}
	}
	
	private void Inquire() { //查詢訂單
		System.out.println("編號\t\t菜品(份數)\t\t\t金額");
		MealManagerSingle m = MealManagerSingle.getInstance(); //呼叫MealManagerSinglejava
		MealPrice p = new MealPrice(); //呼叫MealPrice.java
		Double totalPrice = 0.0; //訂單結算的價格
		int j = 0; //訂單在排版用的編號
		int R = 0; //判斷mealPrice.json裡有無此人資料的標記
		Double discount = 0.9;  //商務車廂訂餐有九折優惠
		for (int i = 0; i < m.getSize(); i++) { //遍歷meal.json裡每一條資料
			if (m.getOrder(i).getCode().equals(code)) { // 查到有這位使用者的資料
				j++; //排版從1, 2, 3...
				Double sumPrice = Double.parseDouble(m.getOrder(i).getSumPrice()); //每筆資料中的份數乘以單價
				String SumPrice = m.getOrder(i).getSumPrice() + "元"; //同上，但是是用來顯示的字串形式
				String dishMsg = m.getOrder(i).getDishMsg(); // 菜品名
				String count = m.getOrder(i).getNumber();//份數
				System.out.println(j + "\t\t"+ dishMsg + "(" + count + ")" + "\t\t" + SumPrice);//顯示一筆完整資料
				totalPrice += sumPrice;//所有sumPrice加總=這位使用者要付的總金額
			}
		}
		for (int i = 0; i < p.getSize(); i++) {//遍歷mealPrice.json裡每一條資料
			if (p.getCode(i).equals(code)) {// 查到有這位使用者的資料
				p.setTotalPrice(i, Double.toString(totalPrice));//存入總金額
				p.save();//儲存變更
				System.out.println("總金額：" + p.getTotalPrice(i));//顯示總金額
				if (b == 1) {//是商務車廂乘客
					totalPrice *= discount;//打九折
					p.setTotalPrice(i, Double.toString(totalPrice));//存入優惠價
					p.save();//儲存變更
					System.out.println("商務車廂優惠價：" + p.getTotalPrice(i));//顯示優惠價
				}
				R = 1;//標記改成"有"(mealPrice.json有此人資料)
				break;
			}
		}
		if (R == 0) {//mealPrice.json無此人資料
			for (int i = 0; i < p.getSize(); i++) {//遍歷mealPrice.json裡每一條資料
				if (p.getCode(i).equals("000000000")) {//找到空的儲存格
					p.setCode(i, code);//存入車票號碼
					p.setTotalPrice(i, Double.toString(totalPrice));//存入總金額
					p.save();//儲存變更
					System.out.println("總金額：" + p.getTotalPrice(i));//顯示總金額
					if (b == 1) {//是商務車廂乘客
						totalPrice *= discount;//打九折
						p.setTotalPrice(i, Double.toString(totalPrice));//存入優惠價
						p.save();//儲存變更
						System.out.println("商務車廂優惠價：" + p.getTotalPrice(i));//顯示優惠價
					}
					break;
				}
			}
		}
	}
	
	private void Login() {//登入
		System.out.println("登入高鐵訂餐系統");
		System.out.println("請輸入車票號碼：");
		int success = 0;//是否成功登入的標記(預設失敗)
		String business = "business";//字串business
		TicketManager tm = new TicketManager();//呼叫TicketManager.java
		while (success == 0) {//若登入失敗，就重複迴圈
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			code = scanner.nextLine();//使用者輸入車票號碼
			for (int i = 0; i < tm.getSize(); i++) {//在booking.json中尋找有無此車票號碼
				if (code.equals(tm.getTicketObj(i).getCode())){//找到了
					success = 1;//標記改為成功
					System.out.println("登入成功！");
					if (tm.getTicketObj(i).getTicketInfo(0, "ticketsType").equals(business)) {
						b = 1;//若使用者為商務車廂乘客，添加標記
					}
					if (tm.getTicketObj(i).getTicketInfo(1, "ticketsType").equals(business)) {
						b = 1;//若使用者為商務車廂乘客，添加標記
					}
					break;
				}
			}
			if (success == 0) {//登入失敗
				System.out.println("查無此號 請重新輸入車票號碼：");
			}
		}
	}
	
	private void OrderingSystem() {//主要訂餐系統
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		MealManagerSingle m = MealManagerSingle.getInstance();//呼叫MealManagerSingle.java
		MealPrice p = new MealPrice();//呼叫MealPrice.java

		String[] dishNames = { "雞腿便當", "素食便當", "排骨便當", "鮭魚便當", "滷香雞蛋", "玉米濃湯" };
		double[] prices = { 85, 70, 80, 119, 15, 12 };
		System.out.println("歡迎進入高鐵訂餐系統");
		int num = -1; // 輸入0 默認返回主菜單
		boolean isExit = false;// 默認不退出
		do {
		System.out.println("**********************");
		System.out.println("1:我要訂餐");
		System.out.println("2:查詢訂單");
		System.out.println("3:刪除訂單");
		System.out.println("4:退出系統");
		System.out.println("**********************");
		System.out.println("請選擇：");
		int choice = scanner.nextInt(); //選擇你所需要的服務
		switch (choice) {

		case 1:
		System.out.println("************我要訂餐************");
		System.out.println("您可以選擇下列的菜品：");
		System.out.println("序號\t\t菜名\t\t\t單價");
		for (int j = 0; j < dishNames.length; j++) { // 遍歷菜品
		System.out.println(j + 1 + "\t\t" + dishNames[j]
		+ "\t\t\t" + prices[j]);
		}
		while(true) {
		System.out.println("請您選擇菜品的編號(訂餐完成按7)：");
		@SuppressWarnings("resource")
		Scanner x=new Scanner(System.in);
		int emmm = x.nextInt();//選擇你所想要的菜品
		switch(emmm) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			int R = 0;
			for (int i = 0; i < m.getSize(); i++) { // 訂餐人的集合
				if (m.getOrder(i).getDishMsg().equals(dishNames[emmm - 1]) && m.getOrder(i).getCode().equals(code)) {
					System.out.println("請您輸入點餐的份數：");//若使用者曾訂購過相同餐點，則新的訂單資料會覆蓋舊的
					int number = scanner.nextInt();
					m.getOrder(i).setNumber(Integer.toString(number));
					int choiceName = emmm;
					double sumPrice = prices[choiceName - 1] * number;
					m.getOrder(i).setSumPrice(Double.toString(sumPrice));
					m.save();
					R = 1;
					break;
				}
			}
			if (R == 0) {
				for (int i = 0; i < m.getSize(); i++) { // 訂餐人的集合
					if (m.getOrder(i).getDishMsg().equals("空空空空")) {
						m.getOrder(i).setCode(code); // 獲取訂餐人的車票號碼
						// 用戶開始點菜，並收集信息
						int choiceName = emmm;
						System.out.println("請您輸入點餐的份數：");
						int number = scanner.nextInt();
						double sumPrice = prices[choiceName - 1] * number;
						// 把輸入的信息塞進數組中
						m.getOrder(i).setSumPrice(Double.toString(sumPrice)); // 金額
						m.getOrder(i).setDishMsg(dishNames[choiceName - 1]); // 定的菜品
						m.getOrder(i).setNumber(Integer.toString(number)); // 份數
						m.save();
						break;
					}
				}
			}
			continue;
		case 7:
			Inquire();//查詢訂單(這裡用來顯示剛送出的訂單)
			System.out.println("*****訂餐成功*****");
			break;
		}
		break;
		}
		break;

		case 2:
		System.out.println("************查詢訂單************");
		Inquire();//查詢訂單
		break;

		case 3:
		System.out.println("************刪除訂單************");
		Inquire();//查詢訂單
		System.out.println("這是您的訂單　是否要刪除？Yes按1/No按0");
		Scanner x = new Scanner(System.in);
		int delete = x.nextInt();
		switch(delete) {//刪除
		case 1:
			for (int i = 0; i < m.getSize(); i++) {
				// 刪除訂餐人在meal.json的所有信息
				if (m.getOrder(i).getCode().equals(code)) {
					m.getOrder(i).setCode("000000000");// 訂餐人
					m.getOrder(i).setDishMsg("空空空空");// 菜品
					m.getOrder(i).setNumber("0");// 份數
					m.getOrder(i).setSumPrice("0");
				}
			}
			m.save();
			for (int i = 0; i < p.getSize(); i++) {
				// 刪除訂餐人在mealPrice.json的所有信息
				if (p.getCode(i).equals(code)) {
					p.setCode(i, "000000000");// 訂餐人
					p.setTotalPrice(i, "0");
				}
			}
			p.save();
			System.out.println("訂單刪除成功");
			break;
		case 0:
			break;
		}
		break;

		case 4:
		// 退出系統
		b = 0;
		isExit = true;
		break;
		
		default:
		// 退出系統
		isExit = true;
		break;
		}
		
		if (!isExit) { // 默認這是不退出
		System.out.println("輸入0返回主選單！");
		num = scanner.nextInt();
		} else {
		break; // 退出系統
		}
		} while (num == 0);
		System.out.println("謝謝您的光臨！！！！！");
		}
	
}