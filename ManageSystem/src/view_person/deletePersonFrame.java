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
		setTitle("ɾ����Ա��Ϣ");
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		contentPane = new MyPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 551, 162);
		contentPane.add(scrollPane);
		
		personInfotable = new JTable();
		personInfotable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personInfotable.setRowHeight(20);   // ���ñ���߶�
		
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
	    r.setHorizontalAlignment(JLabel.CENTER);   
	    personInfotable.setDefaultRenderer(Object.class, r);
		
		JTableHeader header = personInfotable.getTableHeader();// ��ñ�ͷ����
		header.setPreferredSize(new Dimension(header.getWidth(), 30)); // ���ñ�ͷ�߶�
		
		model = (DefaultTableModel) personInfotable.getModel();// ��ñ��ģ��
		model.setRowCount(0);// ��ձ���е�����
		model.setColumnIdentifiers(new Object[] {"���", "����", "�Ա�", "��������", "���֤��", "����", "��ͥסַ", "��ϵ�绰"});
//		List<personBean> results = sqlHelper_person.queryAll();// ������ݿ��б���ȫ������
//		for (personBean Person : results) {   // �����ݼ��ص����ģ����
//			 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
//					 Person.getPlace(), Person.getAddress(), Person.getTelphone() });	
//		}
		
		personInfotable.setModel(model);
		scrollPane.setViewportView(personInfotable);	//��ʾ��
		
		JButton delButton = new JButton("ɾ��");
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
				
		JButton returnButton = new JButton("����");
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
		
	    JButton btnNewButton = new JButton("��  ѯ");
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
		
	    JLabel lblNewLabel = new JLabel("��ѯ������Ϣ��");
	    lblNewLabel.setBounds(20, 81, 102, 21);
	    contentPane.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("������Ҫ��ѯ�������Ϣ��");
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
	
	//ɾ����Ϣ
	 protected void do_deleteButton () {  
		 int selectRow = personInfotable.getSelectedRow();	//��ȡѡ�����
		if (selectRow < 0) {
			JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����У�");
			return;
		}
		else {
			int result = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��Ҫɾ��");
			if (result == JOptionPane.OK_OPTION) {
				final personBean person = new personBean();
				person.setNo((String) personInfotable.getValueAt(selectRow, 0));
				sqlHelper_person.delete(person);
				
				DefaultTableModel model = (DefaultTableModel) personInfotable.getModel();// ��ñ��ģ��
		        model.setRowCount(0);// ��ձ���е�����
		        List<personBean> results = sqlHelper_person.queryAll();// ɾ��ĳһ����Ϣ�������ݿ��б���ȫ������
		        for (personBean Person : results) {   // �����ݼ��ص����ģ����
					 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
							 Person.getPlace(), Person.getAddress(), Person.getTelphone() });	
			   }
				personInfotable.setModel(model);	
		    }
	    }
	 }
	 //ģ����ѯ
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
			for (personBean Person : results) {   // �����ݼ��ص������
				 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
						 Person.getPlace(), Person.getAddress(), Person.getTelphone() });				
			}			
		personInfotable.setModel(model);
		}
		else {
			 JOptionPane.showMessageDialog(this, "�޷��������Ľ����");
		}			
	 }
}
