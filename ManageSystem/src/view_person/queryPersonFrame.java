package view_person;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bean.personBean;
import dao.sqlHelper_person;
import util.MyPanel;

public class queryPersonFrame extends JFrame {
	private JPanel contentPane;
	private JTable personinfotable;
	private JTextField text;
	private JButton queryButton;
    private JButton returnButton;
	private DefaultTableModel model;
	private JLabel lblNewLabel;

	public queryPersonFrame() {
		setTitle("查询人员信息");
		contentPane = new MyPanel();
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		setResizable(false);
    
	    JLabel NameLabel = new JLabel("要查询的信息：");
	    NameLabel.setBounds(29, 313, 118, 15);
	    contentPane.add(NameLabel);
	    text = new JTextField();
	    text.setBounds(157, 310, 112, 21);
	    contentPane.add(text);

	    queryButton = new JButton("查  询");
	    queryButton.setBounds(313, 306, 85, 29);
	    contentPane.add(queryButton);
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
	    
		returnButton = new JButton("返  回");
		returnButton.setBounds(444, 306, 83, 29);
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
	    
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 47, 541, 223);
		contentPane.add(scrollPane);
	    getContentPane().add(scrollPane);
	    personinfotable = new JTable();
		personinfotable.setRowHeight(20);
		
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
	    r.setHorizontalAlignment(JLabel.CENTER);   
	    personinfotable.setDefaultRenderer(Object.class, r);
		
		JTableHeader header = personinfotable.getTableHeader();
		header.setPreferredSize(new Dimension(header.getWidth(), 30));
		
		model = (DefaultTableModel) personinfotable.getModel();
		model.setColumnIdentifiers(new Object[] {"编号", "姓名", "性别", "出生日期", "身份证号", "籍贯", "家庭住址", "联系电话"});
		scrollPane.setViewportView(personinfotable);
		
		lblNewLabel = new JLabel("查询到的信息如下：");
		lblNewLabel.setBounds(29, 22, 176, 15);
		contentPane.add(lblNewLabel);
	}
	
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
				 model.addRow(new Object[] { Person.getNo(), Person.getName(), Person.getSex(), Person.getBirth(), Person.getIDCard(), 
						 Person.getPlace(), Person.getAddress(), Person.getTelphone() });				
			}			
		personinfotable.setModel(model);
		}
		else {
			 JOptionPane.showMessageDialog(this, "无符合条件的结果！");
		}			
	 }
}
