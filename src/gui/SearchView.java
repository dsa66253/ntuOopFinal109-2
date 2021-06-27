package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

//查詢介面
public class SearchView extends JFrame {
	private JPanel contentPane;
	private JButton searchBook;
	private JButton searchReferNum;
	private JButton searchTimetable;
	private JButton searchDiscount;
	private JButton homeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SearchView frame = new SearchView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SearchView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		searchBook = new JButton("查詢訂票");
		searchBook.setBounds(405, 200, 150, 30);
		contentPane.add(searchBook);

		searchReferNum = new JButton("查詢訂位代碼");
		searchReferNum.setBounds(405, 300, 150, 30);
		contentPane.add(searchReferNum);

		searchTimetable = new JButton("當日時刻表查詢");
		searchTimetable.setBounds(405, 400, 150, 30);
		contentPane.add(searchTimetable);

		searchDiscount = new JButton("查詢優惠車次");
		searchDiscount.setBounds(405, 500, 150, 30);
		contentPane.add(searchDiscount);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
	}
	
	//button for SearchBookView
	public void addSearchBookListener(ActionListener listener) {
		searchBook.addActionListener(listener);
	}

	//button for SearchReferNumView
	public void addSearchReferNumListener(ActionListener listener) {
		searchReferNum.addActionListener(listener);
	}

	//button for SearchTimetableView
	public void addSearchTimetableListener(ActionListener listener) {
		searchTimetable.addActionListener(listener);
	}

	//button for SearchDiscountView
	public void addSearchDiscountListener(ActionListener listener) {
		searchDiscount.addActionListener(listener);
	}

	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
}