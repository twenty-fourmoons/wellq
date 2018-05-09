package view_matter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.MatterBean;
import dao.Jdbcexecute_matters;
import util.MyPanel;

import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Desktop.Action;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteMattersframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DeleteMattersframe() {
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new MyPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_delete(e);
			}
		});
		btnNewButton_1.setBounds(41, 95, 69, 23);
		contentPane.add(btnNewButton_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(129, 43, 133, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("所删除物品id：");
		lblNewLabel_2.setBounds(41, 41, 124, 24);
		contentPane.add(lblNewLabel_2);
		
	}
	protected void do_delete(ActionEvent e) {
		String code=textField_4.getText().trim();
		MatterBean matter=new MatterBean();
		matter.setMatterid(code);
		int result=Jdbcexecute_matters.delete(matter);
		if(result==0)
			JOptionPane.showMessageDialog(this, "物品不存在");
		else if(result>0)
			JOptionPane.showMessageDialog(this, "物品删除成功");
		else
			JOptionPane.showMessageDialog(this, "物品删除失败");
	}
}
