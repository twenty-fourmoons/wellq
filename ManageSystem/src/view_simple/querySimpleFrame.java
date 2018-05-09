package view_simple;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bean.MatterBean;
import bean.SimpleBean;
import bean.personBean;
import dao.Jdbcexecute_matters;
import dao.sqlHelper_person;
import dao.sqlHelper_simple;
import util.MyPanel;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import com.eltima.components.ui.DatePicker;
import javax.swing.JSeparator;

public class querySimpleFrame extends JFrame {
	private static final long serialVersionUID = -6534472628851696637L;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTable simpletable;		//�����ֱ��
	private JComboBox pNocomboBox;	//��Ա���������
	private JComboBox mNocomboBox;	//���ϴ���������
	private JTextArea remarktextArea;	//��ע���ı���
	private DefaultTableModel model;	//���ģ��
	private DatePicker EdatePicker;	//��ʼʱ��
	private DatePicker SdatePicker;	//����ʱ��
	private JSeparator separator;
	
	public querySimpleFrame() {
		setTitle("�����Ͻ�/���ֲ�ѯ");
		setSize(600, 400);	//�����С
		setLocationRelativeTo(null);// ������ʾ
		setResizable(false);	//�����С���ɸı�
		JPanel contentPane = new MyPanel();	//�����
		contentPane.setFocusTraversalPolicyProvider(true);
	    setContentPane(contentPane);
	    contentPane.setLayout(null);	//�޲���
	    
	    JLabel lblNewLabel = new JLabel("��ֹ���ڣ�");
	    lblNewLabel.setBounds(32, 36, 66, 15);
	    contentPane.add(lblNewLabel);
	    	    
	    JLabel lblNewLabel_1 = new JLabel("��");
	    lblNewLabel_1.setBounds(196, 36, 24, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    lblNewLabel_2 = new JLabel("���ϴ��룺");
	    lblNewLabel_2.setBounds(209, 86, 66, 15);
	    contentPane.add(lblNewLabel_2);
	    
	    lblNewLabel_3 = new JLabel("��Ա���룺");
	    lblNewLabel_3.setBounds(32, 86, 66, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    lblNewLabel_4 = new JLabel("��ע��");
	    lblNewLabel_4.setBounds(330, 36, 54, 15);
	    contentPane.add(lblNewLabel_4);
	    
	    //�����ݿ����ϱ� ��ȡ��Ա�����ʾ�����ϴ�����������
	    List<MatterBean> list2 = Jdbcexecute_matters.queryall();	//��ѯȫ��������Ϣ �����ڼ���
	    String mNo[] = new String[list2.size() + 1];		//�������鱣����
	    for(int i = 0; i < list2.size(); i++) {		//�������� ��ȡ���ϱ��
	    	MatterBean mat = list2.get(i);	
	    	mNo[i + 1] = mat.getMatterid();
	    }
	    mNocomboBox = new JComboBox(mNo);
	    mNocomboBox.setBounds(277, 83, 66, 21);
	    contentPane.add(mNocomboBox);
	    
	  //�����ݿ���Ա�� ��ȡ��Ա�����ʾ����Ա�����������
	    List<personBean> list = sqlHelper_person.queryAll();		//��ѯȫ����Ա��Ϣ �����ڼ���
	    String pNo[] = new String[list.size() + 1];			//�������鱣����
	    for(int i = 0; i < list.size(); i++) {		//�������� ��ȡ��Ա���
	    	personBean per= list.get(i);
	    	pNo[i + 1] = per.getNo();
	    }
	    pNocomboBox = new JComboBox(pNo);
	    pNocomboBox.setBounds(108, 83, 66, 21);
	    contentPane.add(pNocomboBox);
	    
	    remarktextArea = new JTextArea();
	    remarktextArea.setBounds(394, 32, 161, 76);
	    contentPane.add(remarktextArea);
	    
	    JScrollPane scrollPane = new JScrollPane();		//�������
	    scrollPane.setBounds(32, 138, 526, 129);
	    contentPane.add(scrollPane);
	    
	    simpletable = new JTable();
	    scrollPane.add(simpletable);
	    simpletable.setRowHeight(20);	//����и�
	    
	    model = (DefaultTableModel) simpletable.getModel();	
	    //���ñ�ͷ
	    model.setColumnIdentifiers(new Object[] { "������", "����", "��Ա����", "��ע", "���ϴ���", "����������", "״̬"});
	    scrollPane.setViewportView(simpletable);   //��ʾ��  
	    
	    JButton btnNewButton_1 = new JButton("��  ��");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    btnNewButton_1.addKeyListener(new KeyListener() {
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
	    btnNewButton_1.setBounds(414, 300, 93, 34);
	    contentPane.add(btnNewButton_1);
	    
	    JButton btnNewButton = new JButton("��ʾȫ��");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_showallButton();
	    	}
	    });
	    btnNewButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_showallButton();
				}
			}
		});
	    btnNewButton.setBounds(68, 300, 93, 34);
	    contentPane.add(btnNewButton);
	    
	    JButton queryButton = new JButton("��  ѯ");
	    queryButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					do_queryButton();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
	    	}
	    });
	    queryButton.addKeyListener(new KeyListener() {
	    	public void keyTyped(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					try {
						do_queryButton();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	    queryButton.setBounds(237, 300, 93, 34);
	    contentPane.add(queryButton);

	    SdatePicker = new DatePicker();
	    SdatePicker.getInnerTextField().setFocusTraversalPolicyProvider(true);
	    SdatePicker.setBounds(87, 33, 99, 21);
	    contentPane.add(SdatePicker);
	    
	    EdatePicker = new DatePicker();
	    EdatePicker.setBounds(220, 33, 99, 21);
	    contentPane.add(EdatePicker);
	    
	    separator = new JSeparator();
	    separator.setBounds(0, 284, 594, 2);
	    contentPane.add(separator);
	    
	}
	
	protected void do_queryButton() throws ParseException {
		String pno, mno;
		if (pNocomboBox.getSelectedItem() == null) {
			pno = "";
		}else {
			pno = pNocomboBox.getSelectedItem().toString();	//��ȡѡ����������ֵ
		}
		
		if (mNocomboBox.getSelectedItem() == null) {
			mno = "";
		}else {
			mno = mNocomboBox.getSelectedItem().toString();	//��ȡѡ����������ֵ
		}
		String remarks = remarktextArea.getText().trim();	//��ȡ��ע���ı����ֵ
		Date start = (Date) SdatePicker.getValue();	//��ȡ���ڿؼ���ֵ    java.util.Date���� ��Wed Apr 11 00:00:00 CST 2018��	
		Date end = (Date) EdatePicker.getValue();

	//System.out.println(end);
		SimpleBean simple = new SimpleBean();
		simple.setNo(pno);
		simple.setMatterid(mno);
		simple.setRemarks(remarks);
		simple.setStartd(start);
		simple.setEndd(end);
	//System.out.println(pno);
		List<SimpleBean> result = sqlHelper_simple.querySimple(simple);	//���ò�ѯ�����ֱ� ����
		if (result.size() > 0) {	//result������������
			model.setRowCount(0);	//��ձ��е�����
			for (SimpleBean sim : result) {	//��������
				//���õ���������ӵ�����
				model.addRow(new Object[] { sim.getOrderID(), sim.getInoutDate(), sim.getNo(), sim.getRemarks(),
						sim.getMatterid(), sim.getInoutCount(), sim.getState()});
			}
			simpletable.setModel(model);
		}
		else {
			 JOptionPane.showMessageDialog(this, "�޷��������Ľ����");
		}	
	}
	
	//��ʾȫ����������Ϣ
	protected void do_showallButton() {
			model =(DefaultTableModel) simpletable.getModel();
			model.setRowCount(0);	
			List<SimpleBean> results = sqlHelper_simple.querySimpleAll();
			for (SimpleBean simple : results) {
				model.addRow(new Object[] { simple.getOrderID(), simple.getInoutDate(), simple.getNo(), 
						simple.getRemarks(), simple.getMatterid(), simple.getInoutCount(), simple.getState()});
			}
			simpletable.setModel(model);
	}
}







