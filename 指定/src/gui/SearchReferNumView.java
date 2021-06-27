package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

//查詢訂位代碼介面
public class SearchReferNumView extends JFrame{
	private JPanel contentPane;
	private JLabel label;
	private JTextField uidIn;
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_2;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JLabel label_2;
	private JTextField dateIn;
	private JLabel label_3;
	private JTextField carNumIn;
	private JButton searchingButton;
	private JButton homeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SearchReferNumView frame = new SearchReferNumView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SearchReferNumView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("UID");
		label.setBounds(390, 94, 68, 19);
		contentPane.add(label);

		uidIn = new JTextField("");
		uidIn.setEnabled(true);
		uidIn.setBounds(460, 94, 150, 19);
		contentPane.add(uidIn);
		uidIn.setColumns(10);

		label_1 = new JLabel("起訖站");
		label_1.setBounds(390, 137, 68, 19);
		contentPane.add(label_1);

		label_1_1 = new JLabel("起程站");
		label_1_1.setBounds(460, 137, 68, 19);
		contentPane.add(label_1_1);

		String[] station = { "南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營" };
		comboBox = new JComboBox(station);
		comboBox.setBounds(500, 137, 68, 19);
		contentPane.add(comboBox);

		label_1_2 = new JLabel("到達站");
		label_1_2.setBounds(580, 137, 68, 19);
		contentPane.add(label_1_2);

		comboBox1 = new JComboBox(station);
		comboBox1.setBounds(620, 137, 68, 19);
		contentPane.add(comboBox1);
		
		label_2 = new JLabel("發車日期");
		label_2.setBounds(390, 180, 68, 19);
		contentPane.add(label_2);

		dateIn = new JTextField();
		dateIn.setEnabled(true);
		dateIn.setBounds(460, 180, 150, 19);
		contentPane.add(dateIn);
		dateIn.setColumns(10);
		
//		label_3 = new JLabel("車次號碼");
//		label_3.setBounds(390, 223, 68, 19);
//		contentPane.add(label_3);
//
//		carNumIn = new JTextField();
//		carNumIn.setEnabled(true);
//		carNumIn.setBounds(460, 223, 150, 19);
//		contentPane.add(carNumIn);
//		carNumIn.setColumns(10);

		searchingButton = new JButton("搜尋");
		searchingButton.setBounds(800, 450, 68, 19);
		contentPane.add(searchingButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
	}
	
	public String getStationFromID() {
		//comboBox.addActionListener(comboBox);
		String ID = "";
		if (comboBox.getSelectedIndex() == 0)
			ID = "0990";
		if (comboBox.getSelectedIndex() == 1)
			ID = "1000";
		if (comboBox.getSelectedIndex() == 2)
			ID = "1010";
		if (comboBox.getSelectedIndex() == 3)
			ID = "1020";
		if (comboBox.getSelectedIndex() == 4)
			ID = "1030";
		if (comboBox.getSelectedIndex() == 5)
			ID = "1035";
		if (comboBox.getSelectedIndex() == 6)
			ID = "1040";
		if (comboBox.getSelectedIndex() == 7)
			ID = "1043";
		if (comboBox.getSelectedIndex() == 8)
			ID = "1047";
		if (comboBox.getSelectedIndex() == 9)
			ID = "1050";
		if (comboBox.getSelectedIndex() == 10)
			ID = "1060";
		if (comboBox.getSelectedIndex() == 11)
			ID = "1070";
		return ID;
	}
	
	public String getStationToID() {
		String ID = "";
		if (comboBox1.getSelectedIndex() == 0)
			ID = "0990";
		if (comboBox1.getSelectedIndex() == 1)
			ID = "1000";
		if (comboBox1.getSelectedIndex() == 2)
			ID = "1010";
		if (comboBox1.getSelectedIndex() == 3)
			ID = "1020";
		if (comboBox1.getSelectedIndex() == 4)
			ID = "1030";
		if (comboBox1.getSelectedIndex() == 5)
			ID = "1035";
		if (comboBox1.getSelectedIndex() == 6)
			ID = "1040";
		if (comboBox1.getSelectedIndex() == 7)
			ID = "1043";
		if (comboBox1.getSelectedIndex() == 8)
			ID = "1047";
		if (comboBox1.getSelectedIndex() == 9)
			ID = "1050";
		if (comboBox1.getSelectedIndex() == 10)
			ID = "1060";
		if (comboBox1.getSelectedIndex() == 11)
			ID = "1070";
		return ID;
	}
	
	public String getuidIn() {
		return (uidIn.getText());
	}
	
	public String getDateIn() {
		return (dateIn.getText());
	}
	
//	public String getCarNumIn() {
//		return (carNumIn.getText());
//	}
	
	
	//button for search result
	public void addSearchingButtonListener(ActionListener listener) {
		searchingButton.addActionListener(listener);
	}
	
	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	public void clear() {
		uidIn.setText("");
		dateIn.setText("");
		//carNumIn.setText("");
		comboBox.setSelectedIndex(0);
		comboBox1.setSelectedIndex(0);
	}
}