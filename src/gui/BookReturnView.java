package gui;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//輸入回程資訊的介面
public class BookReturnView extends JFrame {
	private JPanel contentPane;
	private JTextField uidIn;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_2;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JLabel label_2;
	private JRadioButton radioButton;
	private JRadioButton radioButton1;
	private JLabel label_3;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JLabel label_4;
	private JRadioButton radioButton5;
	private JRadioButton radioButton6;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JCheckBox checkBox;
	private JTextField timeIn;
	private JTextField timeIn1;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JComboBox comboBox2;
	private JComboBox comboBox3;
	private ButtonGroup buttonGroup;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private JButton searchingButton;
	private JButton homeButton;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel label_12;
	private JTextField toTime;
	private JLabel label_13;
	private JLabel label_14;
	private JTextField fromTime;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	private JTextField orderIndex;
	private JButton enterButton;
	private JLabel label_18;
	private JTextField trainNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BookReturnView frame = new BookReturnView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public BookReturnView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_16 = new JLabel("請輸入回程資訊");
		label_16.setBounds(460, 290, 100, 19);
		contentPane.add(label_16);
		

		label_7 = new JLabel("回程");
		label_7.setBounds(460, 329, 68, 19);
		contentPane.add(label_7);

		timeIn1 = new JTextField();
		timeIn1.setEnabled(true);
		timeIn1.setBounds(500, 329, 68, 19);
		contentPane.add(timeIn1);
		timeIn1.setColumns(10);
		
		label_14 = new JLabel("約");
		label_14.setBounds(580, 329, 20, 19);
		contentPane.add(label_14);
		
		fromTime = new JTextField();
		fromTime.setEnabled(true);
		fromTime.setBounds(600, 329, 20, 19);
		contentPane.add(fromTime);
		
		label_15 = new JLabel("點出發");
		label_15.setBounds(625, 329, 40, 19);
		contentPane.add(label_15);
		
		label_18 = new JLabel("車次號碼");
		label_18.setBounds(580, 350, 60, 19);
		contentPane.add(label_18);
		
		trainNumber = new JTextField();
		trainNumber.setEnabled(true);
		trainNumber.setBounds(640, 350, 50, 19);
		contentPane.add(trainNumber);

		searchingButton = new JButton("搜尋");
		searchingButton.setBounds(800, 380, 68, 19);
		contentPane.add(searchingButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(390, 430, 300, 300);
		contentPane.add(scrollPane);
		
		label_17 = new JLabel("請輸入代號");
		label_17.setBounds(750, 500, 100, 19);
		contentPane.add(label_17);
		
		orderIndex = new JTextField();
		orderIndex.setEnabled(true);
		orderIndex.setBounds(820, 500, 20, 19);
		contentPane.add(orderIndex);
		orderIndex.setColumns(10);
		
		enterButton = new JButton("確認");
		enterButton.setBounds(800, 650, 68, 19);
		contentPane.add(enterButton);
	}

	public String getuidIn() {
		return (uidIn.getText());
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

	public String getStandardOrBusiness() {
		if (radioButton.isSelected())
			return "standard";
		else if (radioButton1.isSelected())
			return "business";
		else
			return null;
	}

	public int getSeatPrefer() {
		if (radioButton2.isSelected())
			return 0;
		if (radioButton3.isSelected())
			return 1;
		if (radioButton4.isSelected())
			return 2;
		else
			return 3;
	}

	public String getOrderType() {
		if (radioButton5.isSelected())
			return "0";
		else if (radioButton6.isSelected())
			return "1";
		else
			return null;
	}

	public String getTimeIn() {
		return (timeIn.getText());
	}

	public String getTime1In() {
		return (timeIn1.getText());
	}

	public int getNumberOfTicket() {
		return (comboBox2.getSelectedIndex());
	}

	public int getNumberOfDiscount() {
		return (comboBox3.getSelectedIndex());
	}
	
	public int getToTime() {
		return (Integer.parseInt(toTime.getText()));
	}
	
	public int getFromTime() {
		return (Integer.parseInt(fromTime.getText()));
	}
	
	public int getOrderIndex() {
		return (Integer.parseInt(orderIndex.getText()));
	}
	
	public String getTrainNumber() {
		return (trainNumber.getText());
	}
	
	//button for search result
	public void addSearchingButtonListener_2(ActionListener listener) {
		searchingButton.addActionListener(listener);
	}
	
	//button for booking
	public void addBookingButtonListener_2(ActionListener listener) {
		enterButton.addActionListener(listener);
	}
	
	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	//output search result
	public void output(String a) {
		textArea.append(a);
	}
	
	public void clear() {
		//uidIn.setText("");
		//timeIn.setText("");
		timeIn1.setText("");
		//toTime.setText("");
		fromTime.setText("");
		trainNumber.setText("");
		//buttonGroup.clearSelection();
		//buttonGroup1.clearSelection();
		//buttonGroup2.clearSelection();
		textArea.setText("");
		//comboBox.setSelectedIndex(0);
		//comboBox1.setSelectedIndex(0);
		//comboBox2.setSelectedIndex(0);
		//comboBox3.setSelectedIndex(0);
		orderIndex.setText("");
	}
}