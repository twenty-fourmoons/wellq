package view_multi;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sunking.swing.JDatePicker;

import bean.MatterBean;
import bean.multiBean;
import bean.personBean;
import dao.Jdbcexecute_matters;
import dao.sqlHelper_multi;
import dao.sqlHelper_person;
import dao.sqlHelper_simple;
import util.MyPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Date;

public class multiStoreFrame extends JFrame {
	private JPanel contentPane;
	private JTextField ioCounttextField;	//进出仓数量文本框
	private JComboBox pNocomboBox;   //人员编号下拉框
	private JComboBox mNocomboBox;	//物料代码下拉框
	private JRadioButton inRadioButton;   //进仓单选按钮
	private JRadioButton outRadioButton;	//出仓单选按钮
	private JTextArea remarktextArea;	//备注富文本框
	private JDatePicker datePicker;		//进出仓日期控件
	private JTable table;
	private DefaultTableModel model;	//表格模型
	private JLabel dateLabel;
	private JLabel amountLabel;
	private JButton addButton;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	//日期格式转换（2018-04-11）
	private JButton delButton;
	
	public multiStoreFrame() {
		setTitle("多物料进/出仓管理");
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		setResizable(false);	//窗体大小不可变
		contentPane = new MyPanel();		//主面板
	    setContentPane(contentPane);
	    contentPane.setLayout(null); //无布局
	    
	    JLabel lblNewLabel = new JLabel("操作类型：");
	    lblNewLabel.setBounds(21, 167, 90, 15);
	    contentPane.add(lblNewLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    inRadioButton = new JRadioButton("进仓");
	    inRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				dateLabel.setText("进仓时间：");
				amountLabel.setText("进仓数量：");
	    	}
	    });

	    inRadioButton.setFocusTraversalPolicyProvider(true);
	    inRadioButton.setSelected(true);	//默认选择进仓单选按钮
	    buttonGroup.add(inRadioButton);
	    inRadioButton.setBounds(117, 163, 62, 23);
	    contentPane.add(inRadioButton);
	    
	    outRadioButton = new JRadioButton("出仓");
	    outRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dateLabel.setText("出仓时间：");
				amountLabel.setText("出仓数量：");
	    	}
	    });
	    buttonGroup.add(outRadioButton);
	    outRadioButton.setBounds(198, 163, 76, 23);
	    contentPane.add(outRadioButton);

	    dateLabel = new JLabel("进仓时间：");
	    dateLabel.setBounds(21, 28, 101, 15);
	    contentPane.add(dateLabel);
	    datePicker = new JDatePicker();
	    datePicker.setBounds(117, 25, 137, 21);
	    datePicker.setEnabled(false);
	    contentPane.add(datePicker);

	    amountLabel = new JLabel("进仓数量：");
	    amountLabel.setBounds(329, 28, 90, 15);
	    contentPane.add(amountLabel);
	    ioCounttextField = new JTextField();
	    ioCounttextField.setBounds(429, 28, 137, 21);
	    contentPane.add(ioCounttextField);
	    
	    JLabel lblNewLabel_2 = new JLabel("操作人员代码：");
	    lblNewLabel_2.setBounds(21, 75, 101, 15);
	    contentPane.add(lblNewLabel_2);
	    
	    //从数据库人员表 获取人员编号显示在人员编号下拉框中
	    List<personBean> list = sqlHelper_person.queryAll();	//查询全部人员信息 保存在集合
	    String pNo[] = new String[list.size() + 1];	//定义数组保存编号
	    for(int i = 0; i < list.size(); i++) {	//遍历集合 获取人员编号
	    	pNo[i] = list.get(i).getNo();
	    }
	    
	    pNocomboBox = new JComboBox(pNo);    //人员代码
	    pNocomboBox.setBounds(117, 72, 137, 21);
	    contentPane.add(pNocomboBox);

	    JLabel lblNewLabel_3 = new JLabel("物料代码：");
	    lblNewLabel_3.setBounds(21, 122, 76, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    //从数据库物料表 获取人员编号显示在物料代码下拉框中
	    List<MatterBean> list2 = Jdbcexecute_matters.queryall(); //查询全部物料信息 保存在集合
	    String mNo[] = new String[list2.size() + 1];	//定义数组保存编号
	    for(int i = 0; i < list2.size(); i++) {		//遍历集合 获取物料编号
	    	mNo[i ] = list2.get(i).getMatterid();
	    }
	    
	    mNocomboBox = new JComboBox(mNo);   //物料代码
	    mNocomboBox.setBounds(117, 119, 137, 21);
	    contentPane.add(mNocomboBox);
	    
	    JLabel lblNewLabel_5 = new JLabel("备注：");
	    lblNewLabel_5.setBounds(329, 75, 54, 15);
	    contentPane.add(lblNewLabel_5);
	    
	    remarktextArea = new JTextArea();
	    remarktextArea.setBounds(383, 75, 183, 62);
	    contentPane.add(remarktextArea);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(79, 192, 479, 118);
	    contentPane.add(scrollPane);
	    
	    table = new JTable();
	    scrollPane.setViewportView(table);
	    table.setRowHeight(20);	//表格行高
	    model = (DefaultTableModel) table.getModel();	
	    
	    //设置表头
	    model.setColumnIdentifiers(new Object[] { "订单号", "日期", "人员代码", "备注", "物料代码", "数量", "状态"});
	    scrollPane.setViewportView(table);   //显示表  
	    //使表格内容居中显示
	    DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
	    r.setHorizontalAlignment(JLabel.CENTER);   
	    table.setDefaultRenderer(Object.class, r);
	    
	    JLabel lblNewLabel_6 = new JLabel("预览：");
	    lblNewLabel_6.setBounds(21, 192, 54, 15);
	    contentPane.add(lblNewLabel_6);
	    
	    addButton = new JButton("添  加");
	    addButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					do_addButton();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
	    	}
	    });
	    addButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					try {
						do_addButton();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	    addButton.setBounds(380, 163, 93, 23);
	    contentPane.add(addButton);
	    
	    JButton saveButton = new JButton("保  存");
	    saveButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					do_saveButton();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
	    	}
	    });
	    saveButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					try {
						do_saveButton();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	    saveButton.setBounds(264, 327, 93, 23);
	    contentPane.add(saveButton);
	    
	    JButton returnButton = new JButton("返  回");
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
	    returnButton.setBounds(419, 327, 93, 23);
	    contentPane.add(returnButton);
	    
	    delButton = new JButton("删  除");
	    delButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int selectRow = table.getSelectedRow();	//获取选择的行
	    		if (selectRow < 0) {
	    			JOptionPane.showMessageDialog(null, "请选择需要删除的行！");
	    			return;
	    		}
	    		model.removeRow(selectRow);
	    	}
	    });
	    delButton.setBounds(117, 327, 93, 23);
	    contentPane.add(delButton);
	    
	}
	

	//自动生成订单号
	protected String makeOrderId() {
		multiBean multi = new multiBean();
		Date date = new Date();
		String orderid = null;
		List<multiBean> list = sqlHelper_multi.queryMaxOrderID(multi);
		String oID = list.get(0).getOrderID();	//获取数据库最大订单号
		
		String id4 = oID.substring(oID.length()-4, oID.length());	//获取字符串后4位
		int id4int = Integer.parseInt(id4);	//转为整型
		int lastIntID = id4int +1;	//加 1
		String StringID = String.valueOf(lastIntID);	//转为String型
		String last4 = null;
		if (StringID.length() == 1) {
			last4= "000" + StringID;
		}
		if (StringID.length() == 2) {
			last4= "00" + StringID;
		}
		if (StringID.length() == 3) {
			last4= "0" + StringID;
		}
		
		if (inRadioButton.isSelected()) {
			orderid = sdf.format(date) + last4;
		}
		if (outRadioButton.isSelected()) {
			orderid = sdf.format(date) + last4;
		}
		return orderid;
	}
	
	
	
	//添加数据 到表格
	protected void do_addButton() throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");;	//日期格式转换（2018-04-11）
		Date date = datePicker.getSelectedDate();
		String pno = pNocomboBox.getSelectedItem().toString();	//获取选择的下拉框的值
		String mno = mNocomboBox.getSelectedItem().toString();
		String remarks = remarktextArea.getText();	//获取备注富文本框的值
		int inoutcount = Integer.valueOf(ioCounttextField.getText());	//获取进出仓数量  转换为整型
		String state = null;	//进、出仓的状态
		String orderID = makeOrderId();	
		if (inRadioButton.isSelected()) {
			state = "进仓";  //进仓
		}
		else {
			state = "出仓";
		}

		//将每次从窗体中输入的值放在数组中
		String[] rowDate = {orderID, sdf1.format(date), pno, remarks, mno, String.valueOf(inoutcount), state};
		model.addRow(rowDate);  //把数组中的数据添加到表格模型中
		
	}
	
	//保存到数据库中
	protected void do_saveButton() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;	//日期格式转换（2018-04-11）
		Boolean result = null;
		int count = 0;
		for(int i = 0; i < model.getRowCount(); i ++) {//遍历获取表格模型中每一行的数据
			multiBean multi = new multiBean();
			String orderID =  (String) model.getValueAt(i, 0);
			Date date =  sdf.parse((String) model.getValueAt(i, 1));
			String pno = (String) model.getValueAt(i, 2);
			String remarks = (String) model.getValueAt(i, 3);
			String mno = (String) model.getValueAt(i, 4);
			count = Integer.valueOf((String) model.getValueAt(i, 5)) ;
			String state = (String) model.getValueAt(i, 6);
			
			//System.out.println(orderID + " "  + date + " " + pno + " " + remarks + " " + mno + " " + count + " " + state);
			
			multi.setOrderID(orderID);	//再把数据保存到实体中
			multi.setInoutDate(date);
			multi.setNo(pno);
			multi.setRemarks(remarks);
			multi.setMatterid(mno);
			multi.setInoutCount(count);
			multi.setState(state);
			
			result = sqlHelper_multi.addMulti(multi);	//调用添加多物料订单存储过程
		}
		model.setRowCount(0);
		MatterBean matter = new MatterBean();
		List<MatterBean> list = sqlHelper_simple.queryAmount(matter); 	//查询数量
		int amount[] = new int[list.size() + 1];	
		int i;
		for( i = 0; i < list.size(); i++) {		//获取库存数量
			amount[0] = list.get(i).getAmount();
			//System.out.println(amount[0]);
 		}
		if (outRadioButton.isSelected()) {
			if (count > amount[0]) {	//出仓数量大于库存数量，则库存不足
				JOptionPane.showMessageDialog(this , "库存不足！！！"); 
				return;
			}
		}
		if (result) {
			JOptionPane.showMessageDialog(this , "订单增加成功！");
		}
	}
}















