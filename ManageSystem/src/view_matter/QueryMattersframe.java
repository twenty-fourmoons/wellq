package view_matter;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bean.MatterBean;
import dao.Jdbcexecute_matters;
import util.MyPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class QueryMattersframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ScrollPane scrollPane;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public QueryMattersframe() {
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new MyPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("物料代码：");
		lblNewLabel.setBounds(43, 21, 120, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(121, 18, 166, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		table=new JTable();
		model=(DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new Object[] {"matterid","mattername","spec","measurement","amount","other"});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 122, 564, 229);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_query(e);
			}
		});
		btnNewButton.setBounds(331, 17, 93, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("物料名称：");
		label.setBounds(43, 67, 72, 15);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(121, 64, 166, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("物料规格：");
		lblNewLabel_1.setBounds(331, 67, 66, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(407, 64, 120, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		
		
	}
	private void do_query(ActionEvent e) {
		String matterid=textField.getText().trim();
		String mattername=textField_1.getText().trim();
		String matterspec=textField_2.getText().trim();
		MatterBean matter=new MatterBean();
		matter.setMatterid(matterid);
		matter.setMattername(mattername);
		matter.setSpec(matterspec);
		java.util.List<MatterBean> result=Jdbcexecute_matters.query(matter);
		model.setRowCount(0);
		
		for (MatterBean matterBean : result) {
			model.addRow(new Object[] {matterBean.getMatterid(),matterBean.getMattername(),matterBean.getSpec(),matterBean.getMeasurement(),matterBean.getAmount(),matterBean.getOther()});
		}
		table.setModel(model);
		 
	}
}
