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
	private JTextField ioCounttextField;	//�����������ı���
	private JComboBox pNocomboBox;   //��Ա���������
	private JComboBox mNocomboBox;	//���ϴ���������
	private JRadioButton inRadioButton;   //���ֵ�ѡ��ť
	private JRadioButton outRadioButton;	//���ֵ�ѡ��ť
	private JTextArea remarktextArea;	//��ע���ı���
	private JDatePicker datePicker;		//���������ڿؼ�
	private JTable table;
	private DefaultTableModel model;	//���ģ��
	private JLabel dateLabel;
	private JLabel amountLabel;
	private JButton addButton;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	//���ڸ�ʽת����2018-04-11��
	private JButton delButton;
	
	public multiStoreFrame() {
		setTitle("�����Ͻ�/���ֹ���");
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		setResizable(false);	//�����С���ɱ�
		contentPane = new MyPanel();		//�����
	    setContentPane(contentPane);
	    contentPane.setLayout(null); //�޲���
	    
	    JLabel lblNewLabel = new JLabel("�������ͣ�");
	    lblNewLabel.setBounds(21, 167, 90, 15);
	    contentPane.add(lblNewLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    inRadioButton = new JRadioButton("����");
	    inRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				dateLabel.setText("����ʱ�䣺");
				amountLabel.setText("����������");
	    	}
	    });

	    inRadioButton.setFocusTraversalPolicyProvider(true);
	    inRadioButton.setSelected(true);	//Ĭ��ѡ����ֵ�ѡ��ť
	    buttonGroup.add(inRadioButton);
	    inRadioButton.setBounds(117, 163, 62, 23);
	    contentPane.add(inRadioButton);
	    
	    outRadioButton = new JRadioButton("����");
	    outRadioButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dateLabel.setText("����ʱ�䣺");
				amountLabel.setText("����������");
	    	}
	    });
	    buttonGroup.add(outRadioButton);
	    outRadioButton.setBounds(198, 163, 76, 23);
	    contentPane.add(outRadioButton);

	    dateLabel = new JLabel("����ʱ�䣺");
	    dateLabel.setBounds(21, 28, 101, 15);
	    contentPane.add(dateLabel);
	    datePicker = new JDatePicker();
	    datePicker.setBounds(117, 25, 137, 21);
	    datePicker.setEnabled(false);
	    contentPane.add(datePicker);

	    amountLabel = new JLabel("����������");
	    amountLabel.setBounds(329, 28, 90, 15);
	    contentPane.add(amountLabel);
	    ioCounttextField = new JTextField();
	    ioCounttextField.setBounds(429, 28, 137, 21);
	    contentPane.add(ioCounttextField);
	    
	    JLabel lblNewLabel_2 = new JLabel("������Ա���룺");
	    lblNewLabel_2.setBounds(21, 75, 101, 15);
	    contentPane.add(lblNewLabel_2);
	    
	    //�����ݿ���Ա�� ��ȡ��Ա�����ʾ����Ա�����������
	    List<personBean> list = sqlHelper_person.queryAll();	//��ѯȫ����Ա��Ϣ �����ڼ���
	    String pNo[] = new String[list.size() + 1];	//�������鱣����
	    for(int i = 0; i < list.size(); i++) {	//�������� ��ȡ��Ա���
	    	pNo[i] = list.get(i).getNo();
	    }
	    
	    pNocomboBox = new JComboBox(pNo);    //��Ա����
	    pNocomboBox.setBounds(117, 72, 137, 21);
	    contentPane.add(pNocomboBox);

	    JLabel lblNewLabel_3 = new JLabel("���ϴ��룺");
	    lblNewLabel_3.setBounds(21, 122, 76, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    //�����ݿ����ϱ� ��ȡ��Ա�����ʾ�����ϴ�����������
	    List<MatterBean> list2 = Jdbcexecute_matters.queryall(); //��ѯȫ��������Ϣ �����ڼ���
	    String mNo[] = new String[list2.size() + 1];	//�������鱣����
	    for(int i = 0; i < list2.size(); i++) {		//�������� ��ȡ���ϱ��
	    	mNo[i ] = list2.get(i).getMatterid();
	    }
	    
	    mNocomboBox = new JComboBox(mNo);   //���ϴ���
	    mNocomboBox.setBounds(117, 119, 137, 21);
	    contentPane.add(mNocomboBox);
	    
	    JLabel lblNewLabel_5 = new JLabel("��ע��");
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
	    table.setRowHeight(20);	//����и�
	    model = (DefaultTableModel) table.getModel();	
	    
	    //���ñ�ͷ
	    model.setColumnIdentifiers(new Object[] { "������", "����", "��Ա����", "��ע", "���ϴ���", "����", "״̬"});
	    scrollPane.setViewportView(table);   //��ʾ��  
	    //ʹ������ݾ�����ʾ
	    DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
	    r.setHorizontalAlignment(JLabel.CENTER);   
	    table.setDefaultRenderer(Object.class, r);
	    
	    JLabel lblNewLabel_6 = new JLabel("Ԥ����");
	    lblNewLabel_6.setBounds(21, 192, 54, 15);
	    contentPane.add(lblNewLabel_6);
	    
	    addButton = new JButton("��  ��");
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
	    
	    JButton saveButton = new JButton("��  ��");
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
	    
	    JButton returnButton = new JButton("��  ��");
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
	    
	    delButton = new JButton("ɾ  ��");
	    delButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int selectRow = table.getSelectedRow();	//��ȡѡ�����
	    		if (selectRow < 0) {
	    			JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����У�");
	    			return;
	    		}
	    		model.removeRow(selectRow);
	    	}
	    });
	    delButton.setBounds(117, 327, 93, 23);
	    contentPane.add(delButton);
	    
	}
	

	//�Զ����ɶ�����
	protected String makeOrderId() {
		multiBean multi = new multiBean();
		Date date = new Date();
		String orderid = null;
		List<multiBean> list = sqlHelper_multi.queryMaxOrderID(multi);
		String oID = list.get(0).getOrderID();	//��ȡ���ݿ���󶩵���
		
		String id4 = oID.substring(oID.length()-4, oID.length());	//��ȡ�ַ�����4λ
		int id4int = Integer.parseInt(id4);	//תΪ����
		int lastIntID = id4int +1;	//�� 1
		String StringID = String.valueOf(lastIntID);	//תΪString��
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
	
	
	
	//������� �����
	protected void do_addButton() throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");;	//���ڸ�ʽת����2018-04-11��
		Date date = datePicker.getSelectedDate();
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

		//��ÿ�δӴ����������ֵ����������
		String[] rowDate = {orderID, sdf1.format(date), pno, remarks, mno, String.valueOf(inoutcount), state};
		model.addRow(rowDate);  //�������е�������ӵ����ģ����
		
	}
	
	//���浽���ݿ���
	protected void do_saveButton() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;	//���ڸ�ʽת����2018-04-11��
		Boolean result = null;
		int count = 0;
		for(int i = 0; i < model.getRowCount(); i ++) {//������ȡ���ģ����ÿһ�е�����
			multiBean multi = new multiBean();
			String orderID =  (String) model.getValueAt(i, 0);
			Date date =  sdf.parse((String) model.getValueAt(i, 1));
			String pno = (String) model.getValueAt(i, 2);
			String remarks = (String) model.getValueAt(i, 3);
			String mno = (String) model.getValueAt(i, 4);
			count = Integer.valueOf((String) model.getValueAt(i, 5)) ;
			String state = (String) model.getValueAt(i, 6);
			
			//System.out.println(orderID + " "  + date + " " + pno + " " + remarks + " " + mno + " " + count + " " + state);
			
			multi.setOrderID(orderID);	//�ٰ����ݱ��浽ʵ����
			multi.setInoutDate(date);
			multi.setNo(pno);
			multi.setRemarks(remarks);
			multi.setMatterid(mno);
			multi.setInoutCount(count);
			multi.setState(state);
			
			result = sqlHelper_multi.addMulti(multi);	//������Ӷ����϶����洢����
		}
		model.setRowCount(0);
		MatterBean matter = new MatterBean();
		List<MatterBean> list = sqlHelper_simple.queryAmount(matter); 	//��ѯ����
		int amount[] = new int[list.size() + 1];	
		int i;
		for( i = 0; i < list.size(); i++) {		//��ȡ�������
			amount[0] = list.get(i).getAmount();
			//System.out.println(amount[0]);
 		}
		if (outRadioButton.isSelected()) {
			if (count > amount[0]) {	//�����������ڿ�����������治��
				JOptionPane.showMessageDialog(this , "��治�㣡����"); 
				return;
			}
		}
		if (result) {
			JOptionPane.showMessageDialog(this , "�������ӳɹ���");
		}
	}
}















