package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

//退票介面
public class ChangeView extends JFrame {
	private JPanel contentPane;
	private JLabel label;
	private JLabel label_1;
	//private JLabel label_2;
	private JTextField uidIn;
	private JTextField referNumber;
	//private JRadioButton radioButton;
	//private JRadioButton radioButton1;
	private ButtonGroup buttonGroup;
	private JButton enterButton;
	private JButton homeButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ChangeView frame = new ChangeView();
        frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ChangeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("UID");
		label.setBounds(390, 94, 68, 19);
		contentPane.add(label);
		
		label_1 = new JLabel("訂位代號");
		label_1.setBounds(390, 137, 68, 19);
		contentPane.add(label_1);
		
//		label_2 = new JLabel("項目");
//		label_2.setBounds(390, 180, 68, 19);
//		contentPane.add(label_2);
		
		uidIn = new JTextField();
		uidIn.setEnabled(true);
		uidIn.setBounds(460, 94, 150, 19);
		contentPane.add(uidIn);
		uidIn.setColumns(10);
		
		referNumber = new JTextField();
		referNumber.setEnabled(true);
		referNumber.setBounds(460, 137, 150, 19);
		contentPane.add(referNumber);
		referNumber.setColumns(10);
		
//		radioButton = new JRadioButton("退票");
//		radioButton.setBounds(460, 180, 80, 19);
//		contentPane.add(radioButton);
//		
//		radioButton1 = new JRadioButton("減少人數");
//		radioButton1.setBounds(460, 200, 80, 19);
//		contentPane.add(radioButton1);
//		
//		buttonGroup = new ButtonGroup();
//		buttonGroup.add(radioButton);
//		buttonGroup.add(radioButton1);
		
		enterButton = new JButton("確認");
		enterButton.setBounds(800, 450, 68, 19);
		contentPane.add(enterButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
	}
	
	public String getuidIn() {
		return (uidIn.getText());
	}
	
	public String getReferNumber() {
		return (referNumber.getText());
	}
	
	//button for change ticket
	public void addEnterButtonListener(ActionListener listener) {
		enterButton.addActionListener(listener);
	}
	
	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	public void clear() {
		uidIn.setText("");
		referNumber.setText("");
		//buttonGroup.clearSelection();
	}
}