package view_matter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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



public class ModifyMattersframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField_6;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox<String> comboBox;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ModifyMattersframe() {
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new MyPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("物料代码：");
		lblNewLabel.setBounds(41, 239, 69, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("物料名称：");
		lblNewLabel_1.setBounds(41, 280, 69, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(111, 236, 124, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 277, 124, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("规格型号：");
		lblNewLabel_3.setBounds(41, 324, 69, 15);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("计量单位：");
		lblNewLabel_4.setBounds(299, 239, 74, 15);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("库存数量：");
		lblNewLabel_5.setBounds(41, 369, 73, 15);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("备注：");
		lblNewLabel_6.setBounds(324, 280, 42, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_6 = new JTextField("0");
		textField_6.setBounds(111, 366, 124, 21);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(373, 237, 148, 18);
		comboBox.addItem("件");
		comboBox.addItem("套");
		comboBox.addItem("公斤");
		comboBox.addItem("吨");
		comboBox.addItem("升");
		comboBox.addItem("米");
		comboBox.addItem("毫米");
		comboBox.addItem("个");
		contentPane.add(comboBox);
		
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_modify();
			}
		});
		btnNewButton_1.setBounds(435, 30, 86, 23);
		contentPane.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 283, 148, 124);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(111, 321, 124, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(146, 31, 133, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("所修改物品id：");
		lblNewLabel_2.setBounds(47, 28, 124, 24);
		contentPane.add(lblNewLabel_2);
		
		btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_queryforid();
			}
		});
		btnNewButton.setBounds(306, 30, 92, 23);
		contentPane.add(btnNewButton);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(43, 74, 478, 124);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		model=(DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new Object[] {"matterid","mattername","spec","measurement","amount","other"});
		scrollPane_1.setViewportView(table);
		
	}
	
	protected void do_queryforid() {
		String id=textField_4.getText();
		MatterBean matter=new MatterBean();
		matter.setMatterid(id);
		MatterBean matter1=Jdbcexecute_matters.queryone(matter); 
		model.setRowCount(0);
		model.addRow(new Object[] {matter1.getMatterid(),matter1.getMattername(),matter1.getSpec(),matter1.getMeasurement(),matter1.getAmount(),matter1.getOther()});	
		table.setModel(model);
		 
	}
	
	protected void do_modify() {
		String modifyid=textField_4.getText();
		String id=textField.getText();
		String name=textField_1.getText();
		String spec=textField_3.getText();
		int amount=Integer.parseInt(textField_6.getText());
		String measurement=(String) comboBox.getSelectedItem();
		String other=textArea.getText();
		MatterBean matter=new MatterBean();
		matter.setMatterid(id);
		matter.setMattername(name);
		matter.setSpec(spec);
		matter.setAmount(amount);
		matter.setMeasurement(measurement);
		matter.setOther(other);
		MatterBean matter1=new MatterBean();
		matter1.setMatterid(modifyid);
		int rs=Jdbcexecute_matters.updateforquery(matter, matter1);
		if(rs>0)
			JOptionPane.showMessageDialog(this,"修改成功");
		else 
			JOptionPane.showMessageDialog(this, "修改失败");
	}
}
