package view_person;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.eltima.components.ui.DatePicker;

import bean.personBean;
import dao.sqlHelper_person;
import java.awt.Canvas;
import java.awt.Component;

public class PersonMSystem extends JFrame{
	private JPanel contentPane;
	private JTable personInfotable;
	private JTextField textNo;
	private JTextField textName;
	private JTextField textIDCard;
	private JTextField textPlace;
	private JTextField textAddress;
	private JTextField textTelphone;
	private JTextField text;
	private JRadioButton manradioButton;
	private JRadioButton womanradioButton;
	private JButton updateButton;
	private JButton showButton;
    private JButton returnButton;
    private JButton queryButton;
	private JButton saveButton;
	private DefaultTableModel model;
	private DatePicker datePicker;
	
	public PersonMSystem() {
		setTitle("人员信息管理");
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);	
		setResizable(false);
		
	    JLabel NoLabel = new JLabel("人员代码：");
	    NoLabel.setBounds(39, 171, 74, 15);
	    contentPane.add(NoLabel);
	    textNo = new JTextField();
	    textNo.setBounds(114, 168, 133, 21);   
	    contentPane.add(textNo);  
	    
	    JLabel NameLabel = new JLabel("姓    名：");
	    NameLabel.setBounds(339, 171, 74, 15);
	    contentPane.add(NameLabel);
	    textName = new JTextField();
	    textName.setBounds(412, 168, 126, 21);   
	    contentPane.add(textName);   
	   
	    JLabel SexLabel = new JLabel("性    别：");
	    SexLabel.setBounds(39, 204, 74, 15);
	    contentPane.add(SexLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    manradioButton = new JRadioButton("男");
	    manradioButton.setFocusTraversalKeysEnabled(true);
	    buttonGroup.add(manradioButton);
	    manradioButton.setSelected(true);
	    manradioButton.setBounds(114, 200, 57, 23);
	    contentPane.add(manradioButton);
	    
	    womanradioButton = new JRadioButton("女");
	    womanradioButton.setFocusTraversalKeysEnabled(true);
	    buttonGroup.add(womanradioButton);
	    womanradioButton.setBounds(194, 200, 74, 23);
	    contentPane.add(womanradioButton);   
	    
	    JLabel BirthLabel = new JLabel("出生日期：");
	    BirthLabel.setBounds(339, 204, 74, 15);
	    contentPane.add(BirthLabel);
	    
		datePicker = new DatePicker();
		datePicker.setBounds(412, 198, 126, 21);
		contentPane.add(datePicker);
	    
	    JLabel IDCardLabel = new JLabel("身份证号：");
	    IDCardLabel.setBounds(39, 242, 74, 15);
	    contentPane.add(IDCardLabel);
	    textIDCard = new JTextField();
	    textIDCard.setBounds(114, 239, 133, 21);
	    contentPane.add(textIDCard);	
	    
	    JLabel PlaceLabel = new JLabel("籍    贯：");
	    PlaceLabel.setBounds(339, 242, 74, 15);
	    contentPane.add(PlaceLabel);
	    textPlace = new JTextField();
	    textPlace.setBounds(412, 239, 126, 21);
	    contentPane.add(textPlace);	  
	    
	    JLabel AddressLabel = new JLabel("家庭住址：");
	    AddressLabel.setBounds(39, 276, 74, 15);
	    contentPane.add(AddressLabel);
	    textAddress = new JTextField();
	    textAddress.setBounds(114, 273, 133, 21);
	    contentPane.add(textAddress); 
	    
	    JLabel TelphoneLabel = new JLabel("联系电话：");
	    TelphoneLabel.setBounds(339, 279, 74, 15);
	    contentPane.add(TelphoneLabel);
	    textTelphone = new JTextField();
	    textTelphone.setBounds(412, 273, 126, 21);   
	    contentPane.add(textTelphone);

		showButton = new JButton("显  示");
		showButton.setBounds(39, 318, 83, 34);
		contentPane.add(showButton);
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_showButton(e);
			}
		});

		returnButton = new JButton("返  回");
		returnButton.setBounds(488, 318, 83, 34);
		contentPane.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	    updateButton = new JButton("修  改");
	    updateButton.setBounds(272, 318, 74, 34);
	    contentPane.add(updateButton);
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_updateButton(e);
				
			}
		});
	    
	    queryButton = new JButton("查  询");
	    queryButton.setBounds(390, 20, 93, 23);
	    contentPane.add(queryButton);
	    queryButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_queryButton(e);
	    	}
	    });
	    
		JButton delButton = new JButton("删  除");
		delButton.setBounds(151, 318, 83, 34);
	    contentPane.add(delButton);
		delButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				do_deleteButton(e);
			}
		});
		
	    saveButton = new JButton("保  存");
	    saveButton.setBounds(385, 318, 74, 34);
	    contentPane.add(saveButton);
	    saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_saveButton(e);
				
			}
		});
	    
	    
	    
	    JLabel lblNewLabel_1 = new JLabel("请输入要查询的相关信息：");
	    lblNewLabel_1.setBounds(55, 24, 192, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    text = new JTextField();
	    text.setBounds(219, 21, 107, 21);
	    contentPane.add(text);
	    text.setColumns(10);
	    


		JScrollPane scrollPane = new JScrollPane();// 创建滚动窗格
		scrollPane.setBounds(10, 61, 574, 91);
		contentPane.add(scrollPane); // 应用滚动窗格
		
		personInfotable = new JTable();
		personInfotable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personInfotable.setRowHeight(20);   // 设置表体高度
		JTableHeader header = personInfotable.getTableHeader();// 获得表头对象
		header.setPreferredSize(new Dimension(header.getWidth(), 30)); // 设置表头高度
			
		model = (DefaultTableModel) personInfotable.getModel();// 获得表格模型
		model.setRowCount(0);// 清空表格中的数据
		model.setColumnIdentifiers(new Object[] {"编号", "姓名", "性别", "出生日期", "身份证号", "籍贯", "家庭住址", "联系电话"});

		personInfotable.setModel(model);
		
		scrollPane.setViewportView(personInfotable);
		

	}
	
	protected void do_showButton(ActionEvent e) {  //显示某一条信息到文本框中
		int selectRow = personInfotable.getSelectedRow();
		if (selectRow < 0) {
			JOptionPane.showMessageDialog(this, "请选择需要显示的行！", "", JOptionPane.WARNING_MESSAGE);
            return;
		}
		else {
			final personBean person = new personBean();
			person.setNo((String) personInfotable.getValueAt(selectRow, 0));    //从表格中获取值
			person.setName((String) personInfotable.getValueAt(selectRow, 1));
			person.setSex((String) personInfotable.getValueAt(selectRow, 2));
			person.setBirth((Date) personInfotable.getValueAt(selectRow, 3));
			person.setIDCard((String) personInfotable.getValueAt(selectRow, 4));
			person.setPlace((String) personInfotable.getValueAt(selectRow, 5));
			person.setAddress((String) personInfotable.getValueAt(selectRow, 6)); 
			person.setTelphone((String) personInfotable.getValueAt(selectRow, 7));
			// 把值填充到控件
	    	textNo.setText(person.getNo());
	    	textName.setText(person.getName());
	    	textAddress.setText(person.getAddress());
	    	
	    	textIDCard.setText(person.getIDCard());
	    	textPlace.setText(person.getPlace());
	    	textTelphone.setText(person.getTelphone());
		}
	}
		
		 protected void do_updateButton(ActionEvent e) {    //修改按钮
			 String No = textNo.getText().trim();
		     String Name = textName.getText().trim();
			 String Sex = null;
			 if (manradioButton.isSelected()) {
				 Sex = "男";
			 }
			 else {
				 Sex = "女";
			 }
			 Date Birth = (Date) datePicker.getValue();
			 String IDCard = textIDCard.getText().trim();
		  	 String Place = textPlace.getText().trim();
			 String Address = textAddress.getText().trim();
			 String Telphone = textTelphone.getText().trim();
				
			 personBean person = new personBean();
			 person.setNo(No);
			 person.setName(Name);
			 person.setSex(Sex);
			 person.setBirth(Birth);
			 person.setIDCard(IDCard);
			 person.setPlace(Place);
			 person.setAddress(Address);
			 person.setTelphone(Telphone);
				
			 int result = sqlHelper_person.update(person);
			 if (result >= 0) {
				 JOptionPane.showMessageDialog(this, "人员信息修改成功！");
			 }
			 else {
		            JOptionPane.showMessageDialog(this, "个人信息修改失败！！！");
		     }
			 
			
		 }
		 
		 //模糊查询
		 protected void do_queryButton(ActionEvent e) {
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
				for (personBean Person : results) {   // 将数据加载到表格模型中
					 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), 
							 Person.getBirth(), Person.getIDCard(), 
							 Person.getPlace(), Person.getAddress(), Person.getTelphone() });				
				}			
			personInfotable.setModel(model);
			}
			else {
				 JOptionPane.showMessageDialog(this, "无符合条件的结果！");
			}			
		 }
		 
		//删除信息
		 protected void do_deleteButton (ActionEvent e) {  
			 int selectRow = personInfotable.getSelectedRow();
				if (selectRow < 0) {
					JOptionPane.showMessageDialog(this, "请选择需要修改的行！", "", JOptionPane.WARNING_MESSAGE);
		            return;
				}
				else {
					final personBean person = new personBean();
					person.setNo((String) personInfotable.getValueAt(selectRow, 0));
					sqlHelper_person.delete(person);
					DefaultTableModel model = (DefaultTableModel) personInfotable.getModel();// 获得表格模型
			        model.setRowCount(0);// 清空表格中的数据
			        model.setColumnIdentifiers(new Object[] { "编号", "姓名", "性别", "出生日期", "身份证号", "籍贯", "家庭住址", "联系电话" });
			        List<personBean> results = sqlHelper_person.queryAll();// 删除某一条信息后获得数据库中表格的全部数据
			        for (personBean Person : results) {   // 将数据加载到表格模型中
						 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
								 Person.getPlace(), Person.getAddress(), Person.getTelphone() });	
					}
					personInfotable.setModel(model);		        
				}
		 }
		 
			protected void do_saveButton(ActionEvent e) {     //保存按钮事件 添加信息
				String No = textNo.getText().trim();
				String Name = textName.getText().trim();
				String Sex = null;
				if (manradioButton.isSelected()) {
					Sex = "男";
				}
				else {
					Sex = "女";
				}
				Date Birth = (Date) datePicker.getValue();
				String IDCard = textIDCard.getText().trim();
				String Place = textPlace.getText().trim();
				String Address = textAddress.getText().trim();
				String Telphone = textTelphone.getText().trim();
				
				personBean person = new personBean();
				person.setNo(No);
				person.setName(Name);
				person.setSex(Sex);
				person.setBirth(Birth);
				person.setIDCard(IDCard);
				person.setPlace(Place);
				person.setAddress(Address);
				person.setTelphone(Telphone);
				if (No.isEmpty() || Name.isEmpty() || IDCard.isEmpty() || Place.isEmpty() || Address.isEmpty() || Telphone.isEmpty()) {
					JOptionPane.showMessageDialog(this, "请把信息填写完整！", "", JOptionPane.WARNING_MESSAGE);
			   }
				else {
					int result = sqlHelper_person.save(person);
					if(result > 0) {
						JOptionPane.showMessageDialog(this , "信息增加成功！"); 
					}
				}


			}

	public static void main(String[] args) { 
		PersonMSystem pms = new PersonMSystem();
		pms.setVisible(true);
	}
}
