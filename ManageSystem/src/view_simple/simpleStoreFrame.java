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
	private JTextField ioCounttextField;	//�����������ı���
	private JComboBox pNocomboBox;   //��Ա���������
	private JComboBox mNocomboBox;	//���ϴ���������
	private JRadioButton inRadioButton;   //���ֵ�ѡ��ť
	private JRadioButton outRadioButton;	//���ֵ�ѡ��ť
	private JTextArea remarktextArea;	//��ע���ı���
	private JDatePicker datePicker;		//���������ڿؼ�
	private JLabel dateLabel;
	private JLabel amountLabel;
	private JTextField orderIDtextField;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	//���ڸ�ʽת����2018-04-11��
	private JSeparator separator;
	private JLabel lblNewLabel_2;

	
	
	public simpleStoreFrame() {
		setTitle("�����Ͻ�/������Ϣ����");
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		setResizable(false);	//�����С���ɱ�
		contentPane = new MyPanel();	//�����
	    setContentPane(contentPane);
	    contentPane.setLayout(null); //�޲���
	    
	    JLabel lblNewLabel = new JLabel("�������ͣ�");
	    lblNewLabel.setBounds(21, 46, 90, 15);
	    contentPane.add(lblNewLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    inRadioButton = new JRadioButton("����");
	    inRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dateLabel.setText("����ʱ�䣺");
				amountLabel.setText("����������");
				orderIDtextField.setText(makeOrderId());
	    	}
	    });
	    inRadioButton.setFocusTraversalPolicyProvider(true);
	    inRadioButton.setSelected(true);	//Ĭ��ѡ����ֵ�ѡ��ť
	    buttonGroup.add(inRadioButton);
	    inRadioButton.setBounds(117, 42, 62, 23);
	    contentPane.add(inRadioButton);
	    
	    outRadioButton = new JRadioButton("����");
	    outRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dateLabel.setText("����ʱ�䣺");
				amountLabel.setText("����������");
				orderIDtextField.setText(makeOrderId());
	    	}
	    });
	    buttonGroup.add(outRadioButton);
	    outRadioButton.setBounds(200, 42, 76, 23);
	    contentPane.add(outRadioButton);

	    dateLabel = new JLabel("�������ڣ�");
	    dateLabel.setBounds(21, 95, 101, 15);
	    contentPane.add(dateLabel);
	    
	    datePicker = new JDatePicker();
	    datePicker.setBounds(117, 92, 137, 21);
	    datePicker.setEnabled(false);
	    contentPane.add(datePicker);

	    amountLabel = new JLabel("������Ա���룺");
	    amountLabel.setBounds(21, 152, 101, 15);
	    contentPane.add(amountLabel);
	    
	    //�����ݿ���Ա�� ��ȡ��Ա�����ʾ����Ա�����������
	    List<personBean> list = sqlHelper_person.queryAll();	//��ѯȫ����Ա��Ϣ �����ڼ���
	    String pNo[] = new String[list.size() + 1];	//�������鱣����
	    for(int i = 0; i < list.size(); i++) {	//�������� ��ȡ��Ա���
	    	pNo[i] = list.get(i).getNo();
	    }
	    
	    pNocomboBox = new JComboBox(pNo);    //��Ա����
	    pNocomboBox.setBounds(117, 149, 137, 21);
	    contentPane.add(pNocomboBox);
	    pNocomboBox.setEditable(true);

	    JLabel lblNewLabel_3 = new JLabel("���ϴ��룺");
	    lblNewLabel_3.setBounds(21, 206, 76, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    //�����ݿ����ϱ� ��ȡ��Ա�����ʾ�����ϴ�����������
	    List<MatterBean> list2 = Jdbcexecute_matters.queryall(); //��ѯȫ��������Ϣ �����ڼ���
	    String mNo[] = new String[list2.size() + 1];	//�������鱣����
	    for(int i = 0; i < list2.size(); i++) {		//�������� ��ȡ���ϱ��
	    	mNo[i ] = list2.get(i).getMatterid();
	    }
	    
	    mNocomboBox = new JComboBox(mNo);   //���ϴ���
	    mNocomboBox.setBounds(117, 203, 137, 21);
	    contentPane.add(mNocomboBox);
	    mNocomboBox.setEditable(true);
	    
	    amountLabel = new JLabel("����������");
	    amountLabel.setBounds(21, 263, 90, 15);
	    contentPane.add(amountLabel);
	    
	    ioCounttextField = new JTextField();
	    ioCounttextField.setBounds(117, 260, 137, 21);
	    contentPane.add(ioCounttextField);
	    ioCounttextField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (!ioCounttextField.getText().matches("^[0-9]*[1-9][0-9]*$")) {
					lblNewLabel_2.setText("���������0������!");
					lblNewLabel_2.setForeground(Color.RED);
				}
				else {
					lblNewLabel_2.setText("");
				}
			}
		});
	    
	    JLabel lblNewLabel_5 = new JLabel("��ע��");
	    lblNewLabel_5.setBounds(333, 95, 54, 15);
	    contentPane.add(lblNewLabel_5);
	    
	    remarktextArea = new JTextArea();
	    remarktextArea.setBounds(369, 120, 183, 66);
	    contentPane.add(remarktextArea);
	    
	    JButton returnButton = new JButton("��  ��");
	    returnButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();	//�رմ���
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
	    
	    JButton saveButton = new JButton("ȷ  ��");
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
	    
	    JLabel lblNewLabel_1 = new JLabel("�����ţ�");
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
	
	//�Զ����ɶ�����
	protected String makeOrderId() {
		SimpleBean simple = new SimpleBean();
		Date date = new Date();
		String orderid = null;
		String last4 = null;
		List<SimpleBean> list = sqlHelper_simple.queryMaxOrderID(simple);
		String oID = list.get(0).getOrderID();  //��ȡ���ݿ�����󶩵���
		
		String id4 = oID.substring(oID.length()-4, oID.length());	//��ȡ�ַ�����4λ
		int id4int = Integer.parseInt(id4);	//תΪ����
		int lastIntID = id4int +1;	//�� 1
		String StringID = String.valueOf(lastIntID);	//תΪString��
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
	
	 //���水ť�¼�
	protected void do_saveButton() throws ParseException {    
		MatterBean matter =new MatterBean();
		Date date = datePicker.getSelectedDate();	//��ȡ���ڿؼ���ֵ java.util.Date���� ��Wed Apr 11 00:00:00 CST 2018��
		//System.out.println(date);
		String pno = pNocomboBox.getSelectedItem().toString();	//��ȡѡ����������ֵ
		String mno = mNocomboBox.getSelectedItem().toString();
		String remarks = remarktextArea.getText();	//��ȡ��ע���ı����ֵ
		int inoutcount = Integer.valueOf(ioCounttextField.getText());	//��ȡ����������  ת��Ϊ����
		String state = null;	//�������ֵ�״̬
		String orderID = makeOrderId();	
		if (inRadioButton.isSelected()) {
			state = "����";  //����
		}
		else {
			state = "����";
		}
		SimpleBean simple = new SimpleBean();
		simple.setOrderID(orderID);		//���õ���ֵ������ʵ����
		simple.setState(state);
		simple.setInoutDate(date);
		simple.setNo(pno);
		simple.setMatterid(mno);
		simple.setRemarks(remarks);
		simple.setInoutCount(inoutcount);
		matter.setMatterid(mno);
		//	System.out.println(orderID);
		List<MatterBean> list = sqlHelper_simple.queryAmount(matter); 	//��ѯ����
		int amount = list.get(0).getAmount();  //��ȡ�������
		
			//System.out.println(amount);
		boolean result = sqlHelper_simple.savaInOutStore(simple);	//���ñ��涩������
		if (outRadioButton.isSelected()) {
			if (inoutcount > amount) {	//�����������ڿ�����������治��
				JOptionPane.showMessageDialog(this , "��治�㣡����"); 
				return;
			}
		}
		if (result) {
			JOptionPane.showMessageDialog(this , "�������ӳɹ���");
		}
	}
}
