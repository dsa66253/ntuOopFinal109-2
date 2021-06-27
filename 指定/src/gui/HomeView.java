package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import ordering.*;

import backend.*;

//主畫面介面
public class HomeView extends JFrame {
	private JPanel contentPane;
	private JButton goBookButton;
	private JButton goChangeButton;
	private JButton goSearchButton;
	private JButton goMealButton;
	private JLabel weather;
	private Crawler crawler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		HomeView frame = new HomeView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		goBookButton = new JButton("訂票");
		goBookButton.setBounds(405, 200, 150, 70);
		contentPane.add(goBookButton);

		goChangeButton = new JButton("退票");
		goChangeButton.setBounds(405, 400, 150, 70);
		contentPane.add(goChangeButton);

		goSearchButton = new JButton("查詢");
		goSearchButton.setBounds(405, 600, 150, 70);
		contentPane.add(goSearchButton);

		goMealButton = new JButton("點餐");
		goMealButton.setBounds(800, 200, 150, 70);
		contentPane.add(goMealButton);
		
		
		try {
			weather = new JLabel("臺北目前氣溫  攝氏" + Crawler.weather() + "度");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		weather.setBounds(1000, 100, 140, 19);
		contentPane.add(weather);
	}

	//button for BookView
	public void addGoBookButtonListener(ActionListener listener) {
		goBookButton.addActionListener(listener);
	}

	//button for ChangeView
	public void addGoChangeButtonListener(ActionListener listener) {
		goChangeButton.addActionListener(listener);
	}

	//button for SearchView
	public void addGoSearchButtonListener(ActionListener listener) {
		goSearchButton.addActionListener(listener);
	}

	//button for MealView
	public void addGoMealButtonListener(ActionListener listener) {
		goMealButton.addActionListener(listener);
	}
}