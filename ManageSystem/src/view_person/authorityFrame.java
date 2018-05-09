
package view_person;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bean.authorityBean;
import bean.personBean;
import dao.sqlHelper_person;
import util.MyPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class authorityFrame extends JFrame {
	JCheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12;
	JButton queryButton, updateButton;

	private JPanel contentPane;
	private JPanel jPanel;
	private JTextField textField;
	private JSeparator separator_2;
	
	public authorityFrame()  {
		setTitle("Ȩ�޹���");
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		setResizable(false);	//�����С���ɱ�
		contentPane = new MyPanel();	//�����
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    JPanel jPanel = new JPanel();
	    jPanel.setLayout(null);
	    jPanel.setBounds(21, 85, 551, 214);
	    contentPane.add(jPanel);

	    JLabel lblNewLabel = new JLabel("��Ա��Ϣ��");
	    lblNewLabel.setBounds(35, 70, 83, 15);
	    contentPane.add(lblNewLabel);
	    
	    cb1 = new JCheckBox("�����Ա");
	    cb1.setBounds(6, 9, 103, 23);
	    jPanel.add(cb1);
	    
	    cb2 = new JCheckBox("ɾ����Ա");
	    cb2.setBounds(6, 49, 103, 23);
	    jPanel.add(cb2);
	    
	    cb3 = new JCheckBox("�޸���Ա");
	    cb3.setBounds(6, 88, 103, 23);
	    jPanel.add(cb3);
	    
	    cb4 = new JCheckBox("��ѯ��Ա");
	    cb4.setBounds(6, 130, 103, 23);
	    jPanel.add(cb4);
	    
	    JLabel label = new JLabel("������Ϣ��");
	    label.setBounds(160, 70, 83, 15);
	    contentPane.add(label);
	    
	    cb5 = new JCheckBox("�������");
	    cb5.setBounds(131, 9, 103, 23);
	    jPanel.add(cb5);
	    
	    cb6 = new JCheckBox("ɾ������");
	    cb6.setBounds(131, 49, 103, 23);
	    jPanel.add(cb6);
	    
	    cb7 = new JCheckBox("�޸�����");
	    cb7.setBounds(131, 88, 103, 23);
	    jPanel.add(cb7);
	    
	    cb8 = new JCheckBox("��ѯ����");
	    cb8.setBounds(131, 130, 103, 23);
	    jPanel.add(cb8);
	    
	    cb9 = new JCheckBox("�����Ͻ�����");
	    cb9.setBounds(271, 9, 136, 23);
	    jPanel.add(cb9);
	    
	    cb10 = new JCheckBox("��ѯ��");
	    cb10.setBounds(271, 49, 103, 23);
	    jPanel.add(cb10);
	    
	    
	    JLabel label_1 = new JLabel("���Ͻ����֣�");
	    label_1.setBounds(296, 70, 94, 15);
	    contentPane.add(label_1);
	    
	    cb11 = new JCheckBox("�����Ͻ�����");
	    cb11.setBounds(271, 88, 103, 23);
	    jPanel.add(cb11);
	    
	    cb12 = new JCheckBox("Ȩ�޹���");
	    cb12.setBounds(6, 165, 103, 23);
	    jPanel.add(cb12);
	  
	    
	    JSeparator separator = new JSeparator();
	    separator.setBounds(10, 299, 574, 2);
	    contentPane.add(separator);
	    
	    JSeparator separator_1 = new JSeparator();
	    separator_1.setBounds(175, 201, -63, -30);
	    contentPane.add(separator_1);
	    
	    JLabel lblNewLabel_1 = new JLabel("��  �ţ�");
	    lblNewLabel_1.setBounds(35, 30, 54, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    textField = new JTextField();
	    textField.setBounds(99, 27, 108, 21);
	    contentPane.add(textField);
	    
	    queryButton = new JButton("��ѯȨ��");
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
	    queryButton.setBounds(253, 26, 93, 23);
	    contentPane.add(queryButton);
	    
	    updateButton = new JButton("�޸�Ȩ��");
	    updateButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_updateButton();
	    	}
	    });
	    updateButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_updateButton();
				}
			}
		});
	    updateButton.setBounds(85, 322, 93, 23);
	    contentPane.add(updateButton);
	    
	    JButton btnNewButton_2 = new JButton("��  ��");
	    btnNewButton_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    btnNewButton_2.addKeyListener(new KeyListener() {
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
	    btnNewButton_2.setBounds(347, 322, 93, 23);
	    contentPane.add(btnNewButton_2);
	    
	    separator_2 = new JSeparator();
	    separator_2.setBounds(10, 60, 574, 15);
	    contentPane.add(separator_2);
	    
	   
	}
	
	//��ѯȨ�ް�ť
	protected void do_queryButton() {
		String pNo = textField.getText();
		JCheckBox[] cb = new JCheckBox[] {cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12};
		if (sqlHelper_person.findUser(pNo)) {
			List<personBean> list = sqlHelper_person.queryAuthority(pNo);
			String ptype = null;
			for(int i=0; i< list.size(); i++) {
				ptype = list.get(i).getAuthority();
			}
			char[] ch = new char[cb.length];
			ch = ptype.toCharArray();
			
			for(int i=0; i<cb.length; i++) {
				if ('1' ==ch[i]) {
					cb[i].setSelected(true);
				}
				if ('0' ==ch[i]) {
					cb[i].setSelected(false);
				}
			}
		} 
		else {
			JOptionPane.showMessageDialog(this, "�û������ڣ���");
		}
		
	}
	
	
	
	//�޸�Ȩ�ް�ť
	protected void do_updateButton() {
		JCheckBox[] cb = new JCheckBox[] {cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12};
		String pNo = textField.getText().trim();
		char[] c = new char[cb.length] ;	//��ѡ��״ֵ̬01
		for(int i=0; i<cb.length; i++) {
			if (cb[i].isSelected()) {
				c[i] ='1';
			} 
			if (!cb[i].isSelected()) {
				c[i] = '0';
			}
		}
		String auth = new String(c);	//	Ȩ���ַ���
		personBean person = new personBean();
		person.setAuthority(auth);
		person.setNo(pNo);
		int result = sqlHelper_person.updateAuthority(person);
		if (result >= 0) {
			 JOptionPane.showMessageDialog(this, "Ȩ���޸ĳɹ���");
		 }
		 else {
	            JOptionPane.showMessageDialog(this, "Ȩ���޸�ʧ�ܣ�����");
	     }
	}
}
