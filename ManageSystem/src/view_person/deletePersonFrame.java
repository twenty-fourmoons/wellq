package view_person;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bean.personBean;
import dao.sqlHelper_person;
import util.MyPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JSeparator;

public class deletePersonFrame extends JFrame {
	private static final long serialVersionUID = -8247860006019780524L;
	private JPanel contentPane;
	private JTable personInfotable;
	private JTextField text;
	private DefaultTableModel model;
	
	public deletePersonFrame() {
		setTitle("删除人员信息");
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new MyPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 551, 162);
		contentPane.add(scrollPane);
		
		personInfotable = new JTable();
		personInfotable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personInfotable.setRowHeight(20);   // 设置表体高度
		
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
	    r.setHorizontalAlignment(JLabel.CENTER);   
	    personInfotable.setDefaultRenderer(Object.class, r);
		
		JTableHeader header = personInfotable.getTableHeader();// 获得表头对象
		header.setPreferredSize(new Dimension(header.getWidth(), 30)); // 设置表头高度
		
		model = (DefaultTableModel) personInfotable.getModel();// 获得表格模型
		model.setRowCount(0);// 清空表格中的数据
		model.setColumnIdentifiers(new Object[] {"编号", "姓名", "性别", "出生日期", "身份证号", "籍贯", "家庭住址", "联系电话"});
//		List<personBean> results = sqlHelper_person.queryAll();// 获得数据库中表格的全部数据
//		for (personBean Person : results) {   // 将数据加载到表格模型中
//			 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
//					 Person.getPlace(), Person.getAddress(), Person.getTelphone() });	
//		}
		
		personInfotable.setModel(model);
		scrollPane.setViewportView(personInfotable);	//显示表
		
		JButton delButton = new JButton("删除");
		delButton.setBounds(111, 317, 83, 29);
	    contentPane.add(delButton);
		delButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				do_deleteButton();
			}
		});
		delButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_deleteButton();
				}
			}
		});
				
		JButton returnButton = new JButton("返回");
		returnButton.setBounds(348, 317, 83, 29);
	    contentPane.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});	
		returnButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					dispose();
				}
			}
		});
		
	    JButton btnNewButton = new JButton("查  询");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_queryButton();
	    	}
	    });
	    btnNewButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_queryButton();
				}
			}
		});
	    btnNewButton.setBounds(391, 24, 93, 23);
	    contentPane.add(btnNewButton);
		
	    JLabel lblNewLabel = new JLabel("查询到的信息：");
	    lblNewLabel.setBounds(20, 81, 102, 21);
	    contentPane.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("请输入要查询的相关信息：");
	    lblNewLabel_1.setBounds(54, 28, 192, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    text = new JTextField();
	    text.setBounds(220, 25, 107, 21);
	    contentPane.add(text);
	    text.setColumns(10);
	    
	    JSeparator separator = new JSeparator();
	    separator.setBounds(0, 293, 606, 2);
	    contentPane.add(separator);
	}
	
	//删除信息
	 protected void do_deleteButton () {  
		 int selectRow = personInfotable.getSelectedRow();	//获取选择的行
		if (selectRow < 0) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的行！");
			return;
		}
		else {
			int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
			if (result == JOptionPane.OK_OPTION) {
				final personBean person = new personBean();
				person.setNo((String) personInfotable.getValueAt(selectRow, 0));
				sqlHelper_person.delete(person);
				
				DefaultTableModel model = (DefaultTableModel) personInfotable.getModel();// 获得表格模型
		        model.setRowCount(0);// 清空表格中的数据
		        List<personBean> results = sqlHelper_person.queryAll();// 删除某一条信息后获得数据库中表格的全部数据
		        for (personBean Person : results) {   // 将数据加载到表格模型中
					 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
							 Person.getPlace(), Person.getAddress(), Person.getTelphone() });	
			   }
				personInfotable.setModel(model);	
		    }
	    }
	 }
	 //模糊查询
	 protected void do_queryButton() {
		String Text = text.getText().trim();
		personBean person = new personBean();
		person.setName(Text);	
		person.setNo(Text);
		person.setAddress(Text);
		person.setIDCard(Text);
		person.setPlace(Text);
		person.setTelphone(Text);
		final List<personBean> results = sqlHelper_person.query(person);
		if (results.size() > 0) {
			model.setRowCount(0);
			for (personBean Person : results) {   // 将数据加载到表格中
				 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
						 Person.getPlace(), Person.getAddress(), Person.getTelphone() });				
			}			
		personInfotable.setModel(model);
		}
		else {
			 JOptionPane.showMessageDialog(this, "无符合条件的结果！");
		}			
	 }
}
