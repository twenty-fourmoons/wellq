package view_person;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bean.personBean;
import dao.sqlHelper_person;
import util.MyPanel;

import javax.swing.JSeparator;

public class updatePersonFrame extends JFrame {
	private static final long serialVersionUID = 4005699580364315522L;
	private JPanel contentPane;
	private JTable personInfotable;
	private JTextField textNo;
	private JTextField textName;
	private JTextField textBirth;
	private JTextField textIDCard;
	private JTextField textPlace;
	private JTextField textAddress;
	private JTextField textTelphone;
	private JTextField text;
	private JRadioButton manradioButton;
	private JRadioButton womanradioButton;
	private ButtonGroup buttonGroup;
	private JButton updateButton;
	private JButton showButton;
    private JButton returnButton;
    private JButton queryButton;
	private DefaultTableModel model;
	private JSeparator separator;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	public updatePersonFrame() {
		setTitle("修改人员信息");
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane = new MyPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);	
		setResizable(false);
		
	    JLabel NoLabel = new JLabel("人员代码：");
	    NoLabel.setBounds(39, 171, 74, 15);
	    contentPane.add(NoLabel);
	    textNo = new JTextField();
	    textNo.setBounds(114, 168, 133, 21);   
	    contentPane.add(textNo);  
	    textNo.setEditable(false);
	    textNo.setForeground(Color.RED);
	    
	    JLabel NameLabel = new JLabel("姓    名：");
	    NameLabel.setBounds(339, 171, 74, 15);
	    contentPane.add(NameLabel);
	    textName = new JTextField();
	    textName.setBounds(412, 168, 126, 21);   
	    contentPane.add(textName);   
	    
	    textName.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String reg = "^[\\u4e00-\\u9fa5]{0,}$";
				if (!textName.getText().matches(reg) || textName.getText().isEmpty()) {
					lblNewLabel_3.setText("×");
					lblNewLabel_3.setForeground(Color.RED);
				}	
				else {
					lblNewLabel_3.setText("");
					lblNewLabel_3.setForeground(Color.GREEN);
				}
			}
		});
	   
	    JLabel SexLabel = new JLabel("性    别：");
	    SexLabel.setBounds(39, 204, 74, 15);
	    contentPane.add(SexLabel);
	    
	    buttonGroup = new ButtonGroup();
	    manradioButton = new JRadioButton("男");
	    buttonGroup.add(manradioButton);
	    manradioButton.setSelected(true);
	    manradioButton.setBounds(114, 200, 57, 23);
	    contentPane.add(manradioButton);
	    
	    womanradioButton = new JRadioButton("女");
	    buttonGroup.add(womanradioButton);
	    womanradioButton.setBounds(194, 200, 74, 23);
	    contentPane.add(womanradioButton);   
	    
	    JLabel BirthLabel = new JLabel("出生日期：");
	    BirthLabel.setBounds(339, 204, 74, 15);
	    contentPane.add(BirthLabel);
	    textBirth = new JTextField();
	    textBirth.setBounds(412, 201, 126, 21);
	    contentPane.add(textBirth);   
	    
	    JLabel IDCardLabel = new JLabel("身份证号：");
	    IDCardLabel.setBounds(39, 242, 74, 15);
	    contentPane.add(IDCardLabel);
	    textIDCard = new JTextField();
	    textIDCard.setBounds(114, 239, 133, 21);
	    contentPane.add(textIDCard);	
	    
	    textIDCard.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
				if (!textIDCard.getText().matches(reg)) {
					lblNewLabel.setText("×");
					lblNewLabel.setForeground(Color.RED);
				}
				else {
					lblNewLabel.setText("");
					lblNewLabel_3.setForeground(Color.GREEN);
				}
			}
		});
	    
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
	    
	    textTelphone.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String reg = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
				if (!textTelphone.getText().matches(reg)) {
					lblNewLabel_2.setText("×");
					lblNewLabel_2.setForeground(Color.RED);
				}
				else {
					lblNewLabel_2.setText("");
					lblNewLabel_2.setForeground(Color.GREEN);
				}
			}
		});

		showButton = new JButton("显  示");
		showButton.setBounds(77, 318, 83, 34);
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_showButton();
			}
		});
		showButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_showButton();
				}
			}
		});
		
		contentPane.add(showButton);

		returnButton = new JButton("返  回");
		returnButton.setBounds(390, 318, 83, 34);
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
		
	    updateButton = new JButton("修  改");
	    updateButton.setBounds(234, 318, 74, 34);
	    contentPane.add(updateButton);
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do_updateButton();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
	    updateButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					try {
						do_updateButton();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});
	    
	    queryButton = new JButton("查  询");
	    queryButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_queryButton();
	    	}
	    });
	    queryButton.addKeyListener(new KeyListener() {
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
	    
	    queryButton.setBounds(390, 20, 93, 23);
	    contentPane.add(queryButton);
	    
	    JLabel lblNewLabel_1 = new JLabel("请输入要查询的相关信息：");
	    lblNewLabel_1.setBounds(55, 24, 192, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    text = new JTextField();
	    text.setBounds(219, 21, 107, 21);
	    contentPane.add(text);
	    text.setColumns(10);
	    


		JScrollPane scrollPane = new JScrollPane();// 创建滚动窗格
		scrollPane.setBounds(10, 61, 561, 91);
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
		
		separator = new JSeparator();
		separator.setBounds(0, 310, 594, 15);
		contentPane.add(separator);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(254, 242, 54, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(540, 276, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(540, 171, 54, 15);
		contentPane.add(lblNewLabel_3);
	}
	
	protected void do_showButton() {  
		int selectRow = personInfotable.getSelectedRow();
		if (selectRow < 0) {
			JOptionPane.showMessageDialog(this, "请选择需要显示的行！", "", JOptionPane.WARNING_MESSAGE);
            return;
		}
		else {
			final personBean person = new personBean();
			person.setNo((String) personInfotable.getValueAt(selectRow, 0));
			person.setName((String) personInfotable.getValueAt(selectRow, 1));
			person.setSex((String) personInfotable.getValueAt(selectRow, 2));
			person.setBirth((Date) personInfotable.getValueAt(selectRow, 3));
			person.setIDCard((String) personInfotable.getValueAt(selectRow, 4));
			person.setPlace((String) personInfotable.getValueAt(selectRow, 5));
			person.setAddress((String) personInfotable.getValueAt(selectRow, 6)); 
			person.setTelphone((String) personInfotable.getValueAt(selectRow, 7));
			
			
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 填充控件
	    	textNo.setText(person.getNo());
	    	textName.setText(person.getName());
	    
	    	textAddress.setText(person.getAddress());
	    	textBirth.setText(sdf.format(person.getBirth()));
	    	textIDCard.setText(person.getIDCard());
	    	textPlace.setText(person.getPlace());
	    	textTelphone.setText(person.getTelphone());
		}
	}
	
	 protected void do_updateButton() throws ParseException {    //修改按钮
		 String No = textNo.getText().trim();
	     String Name = textName.getText().trim();
		 String Sex = null;
		 if (manradioButton.isSelected()) {
			 Sex = "男";
		 }
		 else {
			 Sex = "女";
		 }
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String Birth = textBirth.getText();
		 String IDCard = textIDCard.getText().trim();
	  	 String Place = textPlace.getText().trim();
		 String Address = textAddress.getText().trim();
		 String Telphone = textTelphone.getText().trim();
		 
		 Date birth = sdf.parse(Birth);
		 personBean person = new personBean();
		 person.setNo(No);
		 person.setName(Name);
		 person.setSex(Sex);
		 person.setBirth(birth);
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
}
