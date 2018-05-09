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
		setTitle("��Ա��Ϣ����");
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);	
		setResizable(false);
		
	    JLabel NoLabel = new JLabel("��Ա���룺");
	    NoLabel.setBounds(39, 171, 74, 15);
	    contentPane.add(NoLabel);
	    textNo = new JTextField();
	    textNo.setBounds(114, 168, 133, 21);   
	    contentPane.add(textNo);  
	    
	    JLabel NameLabel = new JLabel("��    ����");
	    NameLabel.setBounds(339, 171, 74, 15);
	    contentPane.add(NameLabel);
	    textName = new JTextField();
	    textName.setBounds(412, 168, 126, 21);   
	    contentPane.add(textName);   
	   
	    JLabel SexLabel = new JLabel("��    ��");
	    SexLabel.setBounds(39, 204, 74, 15);
	    contentPane.add(SexLabel);
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    manradioButton = new JRadioButton("��");
	    manradioButton.setFocusTraversalKeysEnabled(true);
	    buttonGroup.add(manradioButton);
	    manradioButton.setSelected(true);
	    manradioButton.setBounds(114, 200, 57, 23);
	    contentPane.add(manradioButton);
	    
	    womanradioButton = new JRadioButton("Ů");
	    womanradioButton.setFocusTraversalKeysEnabled(true);
	    buttonGroup.add(womanradioButton);
	    womanradioButton.setBounds(194, 200, 74, 23);
	    contentPane.add(womanradioButton);   
	    
	    JLabel BirthLabel = new JLabel("�������ڣ�");
	    BirthLabel.setBounds(339, 204, 74, 15);
	    contentPane.add(BirthLabel);
	    
		datePicker = new DatePicker();
		datePicker.setBounds(412, 198, 126, 21);
		contentPane.add(datePicker);
	    
	    JLabel IDCardLabel = new JLabel("���֤�ţ�");
	    IDCardLabel.setBounds(39, 242, 74, 15);
	    contentPane.add(IDCardLabel);
	    textIDCard = new JTextField();
	    textIDCard.setBounds(114, 239, 133, 21);
	    contentPane.add(textIDCard);	
	    
	    JLabel PlaceLabel = new JLabel("��    �᣺");
	    PlaceLabel.setBounds(339, 242, 74, 15);
	    contentPane.add(PlaceLabel);
	    textPlace = new JTextField();
	    textPlace.setBounds(412, 239, 126, 21);
	    contentPane.add(textPlace);	  
	    
	    JLabel AddressLabel = new JLabel("��ͥסַ��");
	    AddressLabel.setBounds(39, 276, 74, 15);
	    contentPane.add(AddressLabel);
	    textAddress = new JTextField();
	    textAddress.setBounds(114, 273, 133, 21);
	    contentPane.add(textAddress); 
	    
	    JLabel TelphoneLabel = new JLabel("��ϵ�绰��");
	    TelphoneLabel.setBounds(339, 279, 74, 15);
	    contentPane.add(TelphoneLabel);
	    textTelphone = new JTextField();
	    textTelphone.setBounds(412, 273, 126, 21);   
	    contentPane.add(textTelphone);

		showButton = new JButton("��  ʾ");
		showButton.setBounds(39, 318, 83, 34);
		contentPane.add(showButton);
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_showButton(e);
			}
		});

		returnButton = new JButton("��  ��");
		returnButton.setBounds(488, 318, 83, 34);
		contentPane.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	    updateButton = new JButton("��  ��");
	    updateButton.setBounds(272, 318, 74, 34);
	    contentPane.add(updateButton);
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_updateButton(e);
				
			}
		});
	    
	    queryButton = new JButton("��  ѯ");
	    queryButton.setBounds(390, 20, 93, 23);
	    contentPane.add(queryButton);
	    queryButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_queryButton(e);
	    	}
	    });
	    
		JButton delButton = new JButton("ɾ  ��");
		delButton.setBounds(151, 318, 83, 34);
	    contentPane.add(delButton);
		delButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				do_deleteButton(e);
			}
		});
		
	    saveButton = new JButton("��  ��");
	    saveButton.setBounds(385, 318, 74, 34);
	    contentPane.add(saveButton);
	    saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_saveButton(e);
				
			}
		});
	    
	    
	    
	    JLabel lblNewLabel_1 = new JLabel("������Ҫ��ѯ�������Ϣ��");
	    lblNewLabel_1.setBounds(55, 24, 192, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    text = new JTextField();
	    text.setBounds(219, 21, 107, 21);
	    contentPane.add(text);
	    text.setColumns(10);
	    


		JScrollPane scrollPane = new JScrollPane();// ������������
		scrollPane.setBounds(10, 61, 574, 91);
		contentPane.add(scrollPane); // Ӧ�ù�������
		
		personInfotable = new JTable();
		personInfotable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personInfotable.setRowHeight(20);   // ���ñ���߶�
		JTableHeader header = personInfotable.getTableHeader();// ��ñ�ͷ����
		header.setPreferredSize(new Dimension(header.getWidth(), 30)); // ���ñ�ͷ�߶�
			
		model = (DefaultTableModel) personInfotable.getModel();// ��ñ��ģ��
		model.setRowCount(0);// ��ձ���е�����
		model.setColumnIdentifiers(new Object[] {"���", "����", "�Ա�", "��������", "���֤��", "����", "��ͥסַ", "��ϵ�绰"});

		personInfotable.setModel(model);
		
		scrollPane.setViewportView(personInfotable);
		

	}
	
	protected void do_showButton(ActionEvent e) {  //��ʾĳһ����Ϣ���ı�����
		int selectRow = personInfotable.getSelectedRow();
		if (selectRow < 0) {
			JOptionPane.showMessageDialog(this, "��ѡ����Ҫ��ʾ���У�", "", JOptionPane.WARNING_MESSAGE);
            return;
		}
		else {
			final personBean person = new personBean();
			person.setNo((String) personInfotable.getValueAt(selectRow, 0));    //�ӱ���л�ȡֵ
			person.setName((String) personInfotable.getValueAt(selectRow, 1));
			person.setSex((String) personInfotable.getValueAt(selectRow, 2));
			person.setBirth((Date) personInfotable.getValueAt(selectRow, 3));
			person.setIDCard((String) personInfotable.getValueAt(selectRow, 4));
			person.setPlace((String) personInfotable.getValueAt(selectRow, 5));
			person.setAddress((String) personInfotable.getValueAt(selectRow, 6)); 
			person.setTelphone((String) personInfotable.getValueAt(selectRow, 7));
			// ��ֵ��䵽�ؼ�
	    	textNo.setText(person.getNo());
	    	textName.setText(person.getName());
	    	textAddress.setText(person.getAddress());
	    	
	    	textIDCard.setText(person.getIDCard());
	    	textPlace.setText(person.getPlace());
	    	textTelphone.setText(person.getTelphone());
		}
	}
		
		 protected void do_updateButton(ActionEvent e) {    //�޸İ�ť
			 String No = textNo.getText().trim();
		     String Name = textName.getText().trim();
			 String Sex = null;
			 if (manradioButton.isSelected()) {
				 Sex = "��";
			 }
			 else {
				 Sex = "Ů";
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
				 JOptionPane.showMessageDialog(this, "��Ա��Ϣ�޸ĳɹ���");
			 }
			 else {
		            JOptionPane.showMessageDialog(this, "������Ϣ�޸�ʧ�ܣ�����");
		     }
			 
			
		 }
		 
		 //ģ����ѯ
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
				for (personBean Person : results) {   // �����ݼ��ص����ģ����
					 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), 
							 Person.getBirth(), Person.getIDCard(), 
							 Person.getPlace(), Person.getAddress(), Person.getTelphone() });				
				}			
			personInfotable.setModel(model);
			}
			else {
				 JOptionPane.showMessageDialog(this, "�޷��������Ľ����");
			}			
		 }
		 
		//ɾ����Ϣ
		 protected void do_deleteButton (ActionEvent e) {  
			 int selectRow = personInfotable.getSelectedRow();
				if (selectRow < 0) {
					JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�޸ĵ��У�", "", JOptionPane.WARNING_MESSAGE);
		            return;
				}
				else {
					final personBean person = new personBean();
					person.setNo((String) personInfotable.getValueAt(selectRow, 0));
					sqlHelper_person.delete(person);
					DefaultTableModel model = (DefaultTableModel) personInfotable.getModel();// ��ñ��ģ��
			        model.setRowCount(0);// ��ձ���е�����
			        model.setColumnIdentifiers(new Object[] { "���", "����", "�Ա�", "��������", "���֤��", "����", "��ͥסַ", "��ϵ�绰" });
			        List<personBean> results = sqlHelper_person.queryAll();// ɾ��ĳһ����Ϣ�������ݿ��б���ȫ������
			        for (personBean Person : results) {   // �����ݼ��ص����ģ����
						 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
								 Person.getPlace(), Person.getAddress(), Person.getTelphone() });	
					}
					personInfotable.setModel(model);		        
				}
		 }
		 
			protected void do_saveButton(ActionEvent e) {     //���水ť�¼� �����Ϣ
				String No = textNo.getText().trim();
				String Name = textName.getText().trim();
				String Sex = null;
				if (manradioButton.isSelected()) {
					Sex = "��";
				}
				else {
					Sex = "Ů";
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
					JOptionPane.showMessageDialog(this, "�����Ϣ��д������", "", JOptionPane.WARNING_MESSAGE);
			   }
				else {
					int result = sqlHelper_person.save(person);
					if(result > 0) {
						JOptionPane.showMessageDialog(this , "��Ϣ���ӳɹ���"); 
					}
				}


			}

	public static void main(String[] args) { 
		PersonMSystem pms = new PersonMSystem();
		pms.setVisible(true);
	}
}
