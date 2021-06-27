package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

//當日時刻表查詢介面
public class SearchTimetableView extends JFrame{
	private JPanel contentPane;
	private JLabel label;
	private JTextField dateIn;
	private JButton searchingButton;
	private JButton homeButton;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SearchTimetableView frame = new SearchTimetableView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SearchTimetableView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("日期");
		label.setBounds(390, 94, 68, 19);
		contentPane.add(label);

		dateIn = new JTextField();
		dateIn.setEnabled(true);
		dateIn.setBounds(460, 94, 150, 19);
		contentPane.add(dateIn);
		dateIn.setColumns(10);

		searchingButton = new JButton("搜尋");
		searchingButton.setBounds(800, 450, 68, 19);
		contentPane.add(searchingButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(390, 430, 300, 300);
		contentPane.add(scrollPane);
	}
	
	public String getDateIn() {
		return (dateIn.getText());
	}
	
	//button for search result
	public void addSearchingButtonListener(ActionListener listener) {
		searchingButton.addActionListener(listener);
	}
	
	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	public void output(String a) {
		textArea.append(a);
	}
	
	public void clear() {
		dateIn.setText("");
		textArea.setText("");
	}
	
	public void clear_1() {
		textArea.setText("");
	}
}