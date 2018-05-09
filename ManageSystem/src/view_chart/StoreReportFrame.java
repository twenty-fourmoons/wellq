package view_chart;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.awt.event.ActionEvent;

public class StoreReportFrame extends JFrame {
	private JTextField monthtext;
	public StoreReportFrame() {
		getContentPane().setLayout(null);
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		JLabel lblNewLabel = new JLabel("�·ݣ�");
		lblNewLabel.setBounds(72, 89, 54, 15);
		getContentPane().add(lblNewLabel);
		
		monthtext = new JTextField();
		monthtext.setBounds(189, 86, 66, 21);
		getContentPane().add(monthtext);
		monthtext.setColumns(10);
		
		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button(e);
			}
		});
		btnNewButton.setBounds(121, 151, 93, 23);
		getContentPane().add(btnNewButton);
	}

	protected void do_button(ActionEvent e) {
		String month = monthtext.getText();
		StoreReportDialog dg = new StoreReportDialog(month);
		dg.setSize(1000, 600);
		dg.setLocationRelativeTo(null);
		dg.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		StoreReportFrame StoreReportFrame = new StoreReportFrame();
		StoreReportFrame.setVisible(true);

	}

}
