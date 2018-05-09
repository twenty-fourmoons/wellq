package view_simple;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Calendar.CalendarPanel;
import bean.MatterBean;
import bean.SimpleBean;
import bean.personBean;
import dao.Jdbcexecute_matters;
import dao.sqlConfig;
import dao.sqlHelper_person;
import dao.sqlHelper_simple;
import util.MyPanel;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import com.sunking.swing.JDatePicker;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JSeparator;

public class simpleStoreFrame extends JFrame implements sqlConfig{
	private static final long serialVersionUID = 7356898009743901053L;
	private JPanel contentPane;
	private JTextField ioCounttextField;	//进出仓数量文本框
	private JComboBox pNocomboBox;   //人员编号下拉框
	private JComboBox mNocomboBox;	//物料代码下拉框
	private JRadioButton inRadioButton;   //进仓单选按钮
	private JRadioButton outRadioButton;	//出仓单选按钮
	private JTextArea remarktextArea;	//备注富文本框
	private JDatePicker datePicker;		//进出仓日期控件
	private JLabel dateLabel;
	private JLabel amountLabel;
	private JTextField orderIDtextField;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	//日期格式转换（2018-04-11）
	private JSeparator separator;
	private JLabel lblNewLabel_2;

	
	
	public simpleStoreFrame() {
		setTitle("简单物料进/出仓信息管理");
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		setResizable(false);	//窗体大小不可变
		contentPane = new MyPanel();	//主面板
	    setContentPane(contentPane);
	    contentPane.setLayout(null); //无布局
	    
	    JLabel lblNewLabel = new JLabel("操作类型：");
	    lblNewLabel.setBounds(21, 46, 90, 15);
	    contentPane.add(lblNewLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    inRadioButton = new JRadioButton("进仓");
	    inRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dateLabel.setText("进仓时间：");
				amountLabel.setText("进仓数量：");
				orderIDtextField.setText(makeOrderId());
	    	}
	    });
	    inRadioButton.setFocusTraversalPolicyProvider(true);
	    inRadioButton.setSelected(true);	//默认选择进仓单选按钮
	    buttonGroup.add(inRadioButton);
	    inRadioButton.setBounds(117, 42, 62, 23);
	    contentPane.add(inRadioButton);
	    
	    outRadioButton = new JRadioButton("出仓");
	    outRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dateLabel.setText("出仓时间：");
				amountLabel.setText("出仓数量：");
				orderIDtextField.setText(makeOrderId());
	    	}
	    });
	    buttonGroup.add(outRadioButton);
	    outRadioButton.setBounds(200, 42, 76, 23);
	    contentPane.add(outRadioButton);

	    dateLabel = new JLabel("进仓日期：");
	    dateLabel.setBounds(21, 95, 101, 15);
	    contentPane.add(dateLabel);
	    
	    datePicker = new JDatePicker();
	    datePicker.setBounds(117, 92, 137, 21);
	    datePicker.setEnabled(false);
	    contentPane.add(datePicker);

	    amountLabel = new JLabel("操作人员代码：");
	    amountLabel.setBounds(21, 152, 101, 15);
	    contentPane.add(amountLabel);
	    
	    //从数据库人员表 获取人员编号显示在人员编号下拉框中
	    List<personBean> list = sqlHelper_person.queryAll();	//查询全部人员信息 保存在集合
	    String pNo[] = new String[list.size() + 1];	//定义数组保存编号
	    for(int i = 0; i < list.size(); i++) {	//遍历集合 获取人员编号
	    	pNo[i] = list.get(i).getNo();
	    }
	    
	    pNocomboBox = new JComboBox(pNo);    //人员代码
	    pNocomboBox.setBounds(117, 149, 137, 21);
	    contentPane.add(pNocomboBox);
	    pNocomboBox.setEditable(true);

	    JLabel lblNewLabel_3 = new JLabel("物料代码：");
	    lblNewLabel_3.setBounds(21, 206, 76, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    //从数据库物料表 获取人员编号显示在物料代码下拉框中
	    List<MatterBean> list2 = Jdbcexecute_matters.queryall(); //查询全部物料信息 保存在集合
	    String mNo[] = new String[list2.size() + 1];	//定义数组保存编号
	    for(int i = 0; i < list2.size(); i++) {		//遍历集合 获取物料编号
	    	mNo[i ] = list2.get(i).getMatterid();
	    }
	    
	    mNocomboBox = new JComboBox(mNo);   //物料代码
	    mNocomboBox.setBounds(117, 203, 137, 21);
	    contentPane.add(mNocomboBox);
	    mNocomboBox.setEditable(true);
	    
	    amountLabel = new JLabel("进仓数量：");
	    amountLabel.setBounds(21, 263, 90, 15);
	    contentPane.add(amountLabel);
	    
	    ioCounttextField = new JTextField();
	    ioCounttextField.setBounds(117, 260, 137, 21);
	    contentPane.add(ioCounttextField);
	    ioCounttextField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (!ioCounttextField.getText().matches("^[0-9]*[1-9][0-9]*$")) {
					lblNewLabel_2.setText("请输入大于0的整数!");
					lblNewLabel_2.setForeground(Color.RED);
				}
				else {
					lblNewLabel_2.setText("");
				}
			}
		});
	    
	    JLabel lblNewLabel_5 = new JLabel("备注：");
	    lblNewLabel_5.setBounds(333, 95, 54, 15);
	    contentPane.add(lblNewLabel_5);
	    
	    remarktextArea = new JTextArea();
	    remarktextArea.setBounds(369, 120, 183, 66);
	    contentPane.add(remarktextArea);
	    
	    JButton returnButton = new JButton("返  回");
	    returnButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();	//关闭窗体
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
	    returnButton.setBounds(326, 328, 93, 23);
	    contentPane.add(returnButton);
	    
	    JButton saveButton = new JButton("确  认");
	    saveButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					do_saveButton();
						orderIDtextField.setText(makeOrderId());
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
							orderIDtextField.setText(makeOrderId());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	    saveButton.setBounds(117, 328, 93, 23);
	    contentPane.add(saveButton);
	    
	    JLabel lblNewLabel_1 = new JLabel("订单号：");
	    lblNewLabel_1.setBounds(333, 46, 54, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    orderIDtextField = new JTextField();
	    orderIDtextField.setBounds(415, 43, 137, 21);
	    orderIDtextField.setEditable(false);
	    orderIDtextField.setText(makeOrderId());
	    orderIDtextField.setForeground(Color.RED);
	    contentPane.add(orderIDtextField);
	    
	    separator = new JSeparator();
	    separator.setBounds(0, 316, 594, 2);
	    contentPane.add(separator);
	    
	    lblNewLabel_2 = new JLabel();
	    lblNewLabel_2.setBounds(117, 291, 137, 15);
	    contentPane.add(lblNewLabel_2);
	    
	}
	
	//自动生成订单号
	protected String makeOrderId() {
		SimpleBean simple = new SimpleBean();
		Date date = new Date();
		String orderid = null;
		String last4 = null;
		List<SimpleBean> list = sqlHelper_simple.queryMaxOrderID(simple);
		String oID = list.get(0).getOrderID();  //获取数据库中最大订单号
		
		String id4 = oID.substring(oID.length()-4, oID.length());	//获取字符串后4位
		int id4int = Integer.parseInt(id4);	//转为整型
		int lastIntID = id4int +1;	//加 1
		String StringID = String.valueOf(lastIntID);	//转为String型
		//System.out.println("StringID " + StringID);
		if (StringID.length() == 1) {
			last4 = "000" + StringID;
		}
		if (StringID.length() == 2) {
			last4 = "00" + StringID;
		}
		if (StringID.length() == 3) {
			last4 = "0" + StringID;
		}
		
		
		if (inRadioButton.isSelected()) {
			orderid = sdf.format(date)  + last4;
			System.out.println(orderid);
		}
		if (outRadioButton.isSelected()) {
			orderid = sdf.format(date)  + last4;
		}
		return orderid;
		
	}
	
	 //保存按钮事件
	protected void do_saveButton() throws ParseException {    
		MatterBean matter =new MatterBean();
		Date date = datePicker.getSelectedDate();	//获取日期控件的值 java.util.Date类型 （Wed Apr 11 00:00:00 CST 2018）
		//System.out.println(date);
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
		SimpleBean simple = new SimpleBean();
		simple.setOrderID(orderID);		//将得到的值保存在实体中
		simple.setState(state);
		simple.setInoutDate(date);
		simple.setNo(pno);
		simple.setMatterid(mno);
		simple.setRemarks(remarks);
		simple.setInoutCount(inoutcount);
		matter.setMatterid(mno);
		//	System.out.println(orderID);
		List<MatterBean> list = sqlHelper_simple.queryAmount(matter); 	//查询数量
		int amount = list.get(0).getAmount();  //获取库存数量
		
			//System.out.println(amount);
		boolean result = sqlHelper_simple.savaInOutStore(simple);	//调用保存订单方法
		if (outRadioButton.isSelected()) {
			if (inoutcount > amount) {	//出仓数量大于库存数量，则库存不足
				JOptionPane.showMessageDialog(this , "库存不足！！！"); 
				return;
			}
		}
		if (result) {
			JOptionPane.showMessageDialog(this , "订单增加成功！");
		}
	}
}
