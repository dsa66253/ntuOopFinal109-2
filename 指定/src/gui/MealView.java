package gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//訂餐功能介面
public class MealView extends JFrame {
	private JPanel contentPane;
	private JButton orderMeal;
	private JButton searchMeal;
	private JButton cancelMeal;
	//private JButton searchDiscount;
	private JButton homeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MealView frame = new MealView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MealView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		orderMeal = new JButton("我要訂餐");
		orderMeal.setBounds(405, 200, 150, 30);
		contentPane.add(orderMeal);

		searchMeal = new JButton("查詢訂單");
		searchMeal.setBounds(405, 300, 150, 30);
		contentPane.add(searchMeal);

		cancelMeal = new JButton("刪除訂單");
		cancelMeal.setBounds(405, 400, 150, 30);
		contentPane.add(cancelMeal);

		//searchDiscount = new JButton("查詢優惠車次");
		//searchDiscount.setBounds(405, 500, 150, 30);
		//contentPane.add(searchDiscount);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
	}

	//button for order meal
	public void addOrderMealListener(ActionListener listener) {
		orderMeal.addActionListener(listener);
	}

	//button for search meal
	public void addSearchMealListener(ActionListener listener) {
		searchMeal.addActionListener(listener);
	}

	//button for cancel meal
	public void addCancelMealListener(ActionListener listener) {
		cancelMeal.addActionListener(listener);
	}

//	public void addSearchDiscountListener(ActionListener listener) {
//		searchDiscount.addActionListener(listener);
//	}

	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
}