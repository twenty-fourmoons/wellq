package view_matter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.MatterBean;
import dao.Jdbcexecute_matters;
import util.MyPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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


public class AdditionMattersframe extends JFrame {
	private static final long serialVersionUID = -3532714624170593561L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField_6;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textField_3;
	private JComboBox<String> comboBox;

	public AdditionMattersframe() {
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new MyPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("物料代码：");
		lblNewLabel.setBounds(41, 54, 69, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("物料名称：");
		lblNewLabel_1.setBounds(41, 104, 69, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(111, 51, 124, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 101, 124, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("规格型号：");
		lblNewLabel_3.setBounds(41, 154, 69, 15);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("计量单位：");
		lblNewLabel_4.setBounds(282, 104, 74, 15);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("库存数量：");
		lblNewLabel_5.setBounds(282, 54, 73, 15);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("备注：");
		lblNewLabel_6.setBounds(68, 212, 42, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_6 = new JTextField("0");
		textField_6.setBounds(361, 51, 124, 21);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(361, 102, 124, 18);
		comboBox.addItem("件");
		comboBox.addItem("套");
		comboBox.addItem("公斤");
		comboBox.addItem("吨");
		comboBox.addItem("升");
		comboBox.addItem("米");
		comboBox.addItem("毫米");
		comboBox.addItem("个");
		contentPane.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 207, 374, 60);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(111, 151, 124, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				do_addition(e);
			}
		});
		btnNewButton.setBounds(111, 305, 69, 23);
		contentPane.add(btnNewButton);
		
		
	}
	protected void do_addition(ActionEvent e) {
		String code=textField.getText().trim();
		String name=textField_1.getText().trim();
		String spe=textField_3.getText().trim();
		String measurement=(String) comboBox.getSelectedItem();
		int amount=Integer.parseInt(textField_6.getText().trim());
		String other=textArea.getText().trim();
		MatterBean matter=new MatterBean();
		matter.setMatterid(code);
		matter.setMattername(name);
		matter.setSpec(spe);
		matter.setMeasurement(measurement);
		matter.setAmount(amount);
		matter.setOther(other);
		int result=Jdbcexecute_matters.addition(matter);
		if(result>=0) {
			JOptionPane.showMessageDialog(this, "物料信息添加成功");
		}else {
			JOptionPane.showMessageDialog(this, "物料信息添加失败");
		}	
	}
	
}
