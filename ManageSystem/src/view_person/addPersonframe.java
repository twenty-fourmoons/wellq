package view_person;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;

import bean.personBean;
import dao.sqlHelper_person;
import util.MyPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import com.eltima.components.ui.DatePicker;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class addPersonframe extends JFrame {
	private static final long serialVersionUID = -8901330721832444587L;
	private JPanel contentPane;
	private JTextField textNo;		//人员编号
	private JTextField textName;	//人员姓名
	private JTextField textIDCard;	//身份证号
	private JTextField textPlace;	//籍贯
	private JTextField textAddress;	//家庭住址
	private JTextField textTelphone;	//联系电话
	private JRadioButton manradioButton;	//男	单选按钮
	private JRadioButton womanradioButton;	//女 单选按钮
	private JButton clearButton;	//清空按钮
	private JButton saveButton;		//保存按钮
	private JButton returnButton;	//返回主界面按钮
	private DatePicker datePicker;	//日期控件
	personBean person = new personBean();
	private JSeparator separator;
	private JLabel namewarm,idcardwarm,telwarm;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	public addPersonframe() {
		setTitle("增加人员信息");
		contentPane = new MyPanel();	//主面板
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		setResizable(false);	//窗体 大小不可变
	    
	    JLabel NoLabel = new JLabel("人员代码：");
	    NoLabel.setBounds(68, 31, 74, 15);
	    contentPane.add(NoLabel);
	    textNo = new JTextField();
	    textNo.setForeground(Color.RED);
	    textNo.setEditable(false);
	    textNo.setBounds(152, 28, 121, 21);
	    contentPane.add(textNo);
	    textNo.setText(makeNo());
	    
	    
	    namewarm = new JLabel();
	    namewarm.setBounds(401, 56, 160, 15);
	    contentPane.add(namewarm);
	    JLabel NameLabel = new JLabel("姓  名：");
	    NameLabel.setBounds(329, 31, 74, 15);
	    contentPane.add(NameLabel);
	    textName = new JTextField();
	    textName.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String reg = "^[\\u4e00-\\u9fa5]{0,}$";
				if (!textName.getText().matches(reg) || textName.getText().isEmpty()) {
					namewarm.setText("请输入中文姓名！！");
					namewarm.setForeground(Color.RED);

					lblNewLabel_4.setText("");
				}	
				else {
					namewarm.setText("");
					lblNewLabel_4.setText("✔");
					lblNewLabel_4.setForeground(Color.GREEN);
				}
			}
		});

	    textName.setBounds(401, 28, 121, 21);
	    contentPane.add(textName);
	   
	    JLabel SexLabel = new JLabel("性  别：");
	    SexLabel.setBounds(68, 94, 74, 15);
	    contentPane.add(SexLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();	//按钮组
	    manradioButton = new JRadioButton("男");
	    buttonGroup.add(manradioButton);
	    manradioButton.setSelected(true);	//默认选择 男 单选按钮
	    manradioButton.setBounds(148, 90, 56, 23);
	    contentPane.add(manradioButton);
	    
	    womanradioButton = new JRadioButton("女");
	    buttonGroup.add(womanradioButton);
	    womanradioButton.setBounds(206, 90, 67, 23);
	    contentPane.add(womanradioButton);
	    
	    JLabel BirthLabel = new JLabel("出生日期：");
	    BirthLabel.setBounds(68, 159, 74, 15);
	    contentPane.add(BirthLabel);
	    datePicker = new DatePicker();
	    datePicker.setBounds(152, 159, 89, 21);
	    contentPane.add(datePicker);
	    
	    idcardwarm = new JLabel();
	    idcardwarm.setBounds(401, 119, 160, 15);
	    contentPane.add(idcardwarm);
	    JLabel IDCardLabel = new JLabel("身份证号：");
	    IDCardLabel.setBounds(329, 94, 74, 15);
	    contentPane.add(IDCardLabel);
	    textIDCard = new JTextField();
	    textIDCard.setBounds(401, 91, 121, 21);
	    contentPane.add(textIDCard);
	    textIDCard.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
				if (!textIDCard.getText().matches(reg)) {
					idcardwarm.setText("请输入正确的号码！");
					idcardwarm.setForeground(Color.RED);
					lblNewLabel_3.setText("");
				}
				else {
					idcardwarm.setText("");
					lblNewLabel_3.setText("✔");
					lblNewLabel_3.setForeground(Color.GREEN);
				}
			}
		});
	    
	    JLabel PlaceLabel = new JLabel("籍  贯：");
	    PlaceLabel.setBounds(329, 159, 74, 15);
	    contentPane.add(PlaceLabel);
	    textPlace = new JTextField();
	    textPlace.setBounds(401, 156, 121, 21);
	    contentPane.add(textPlace);
	    
	    JLabel AddressLabel = new JLabel("家庭住址：");
	    AddressLabel.setBounds(68, 237, 74, 15);
	    contentPane.add(AddressLabel);
	    textAddress = new JTextField();
	    textAddress.setBounds(154, 234, 119, 21);
	    contentPane.add(textAddress);
	    
	    telwarm = new JLabel();
	    telwarm.setBounds(401, 262, 160, 15);
	    contentPane.add(telwarm);
	    
	    JLabel TelphoneLabel = new JLabel("联系电话：");
	    TelphoneLabel.setBounds(329, 237, 89, 15);
	    contentPane.add(TelphoneLabel);
	    textTelphone = new JTextField();
	    textTelphone.setBounds(401, 234, 121, 21);
	    contentPane.add(textTelphone);
	    textTelphone.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String reg = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
				if (!textTelphone.getText().matches(reg)) {
					telwarm.setText("请输入正确的11位号码！");
					telwarm.setForeground(Color.RED);
					lblNewLabel_2.setText("");
				}
				else {
					telwarm.setText("");
					lblNewLabel_2.setText("✔");
					lblNewLabel_2.setForeground(Color.GREEN);
				}
			}
		});
	    
	    
	    saveButton = new JButton("保  存");
	    saveButton.setBounds(112, 328, 74, 23);
	    contentPane.add(saveButton);
	    saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_saveButton();
				textNo.setText(makeNo());
			}
		});
	    saveButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_saveButton();
				}
			}
		});
	    
	    returnButton = new JButton("返  回");
	    returnButton.setBounds(392, 328, 74, 23);
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
	    
	    clearButton = new JButton("清  空");
	    clearButton.setBounds(260, 328, 74, 23);
	    contentPane.add(clearButton);
	    clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearButton();
			}
		});
	    clearButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_clearButton();
				}
			}
		});
	    
	    separator = new JSeparator();
	    separator.setBounds(10, 303, 594, 15);
	    contentPane.add(separator);
	    
	    lblNewLabel_2 = new JLabel();
	    lblNewLabel_2.setBounds(532, 237, 54, 15);
	    contentPane.add(lblNewLabel_2);
	    
	    lblNewLabel_3 = new JLabel();
	    lblNewLabel_3.setBounds(532, 94, 54, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    lblNewLabel_4 = new JLabel();
	    lblNewLabel_4.setBounds(532, 31, 54, 15);
	    contentPane.add(lblNewLabel_4);
	    

	
	    
	}
	
	//自动生成人员编号
	
	protected String makeNo() {
		String pno = null;
		String pNo = null;
		List<personBean> list = sqlHelper_person.queryMaxNo(person);
			for(int i = 0 ; i < list.size(); i++) {
				pNo = list.get(0).getNo();
			}
			int no = Integer.parseInt(pNo);
			int No = no + 1;
			pno = String.valueOf(No);
	
		return pno;
		
	}
 
	
	
	
	 //保存按钮事件
	protected void do_saveButton() {  
		String pNo = makeNo();
		System.out.println(pNo);
		String Name = textName.getText().trim();
		String Sex = null;
		if (manradioButton.isSelected()) {
			Sex = "男";
		}
		else {
			Sex = "女";
		}
		//获取个组件的值
		Date Birth = (Date) datePicker.getValue();
		String IDCard = textIDCard.getText().trim();
		String Place = textPlace.getText().trim();
		String Address = textAddress.getText().trim();
		String Telphone = textTelphone.getText().trim();
		
		//把值保存到实体中
		personBean person = new personBean();
		person.setNo(pNo);
		person.setName(Name);
		person.setSex(Sex);
		person.setBirth(Birth);
		person.setIDCard(IDCard);
		person.setPlace(Place);
		person.setAddress(Address);
		person.setTelphone(Telphone);
		if (Name.isEmpty() || IDCard.isEmpty() || Place.isEmpty() || Address.isEmpty() || Telphone.isEmpty()) {
			JOptionPane.showMessageDialog(this, "请把信息填写完整！", "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else {
			int result = sqlHelper_person.save(person);
			if(result >= 0) {
				JOptionPane.showMessageDialog(this , "信息增加成功！"); 
				return;
			}else {
	            JOptionPane.showMessageDialog(this, "信息增加失败！");
	            return;
	        }
		}
	}
	
	//清空按钮
	protected void do_clearButton() {
		textNo.setText(makeNo());
		textName.setText("");
		textAddress.setText("");
		textIDCard.setText("");
		textTelphone.setText("");
		textPlace.setText("");
	}
}
