package view_chart;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreBookFrame extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public StoreBookFrame () {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("年份：");
		lblNewLabel.setBounds(99, 95, 54, 15);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(177, 92, 66, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("物料代码：");
		lblNewLabel_1.setBounds(99, 152, 68, 15);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(177, 149, 66, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button(e);
			}
		});
		btnNewButton.setBounds(132, 215, 93, 23);
		getContentPane().add(btnNewButton);
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
	}
	protected void do_button(ActionEvent e) {
		String year = textField.getText().trim();
		String matterID = textField_1.getText().trim();
		StoreBookDialog sbd = new StoreBookDialog(year, matterID);
		
		sbd.setSize(1000, 600);
		sbd.setLocationRelativeTo(null);
		sbd.setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
		StoreBookFrame storeBookFrame = new StoreBookFrame();
		storeBookFrame.setVisible(true);
	}
}
