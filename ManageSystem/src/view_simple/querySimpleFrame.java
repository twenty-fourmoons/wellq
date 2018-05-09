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
	private JTable simpletable;		//进出仓表格
	private JComboBox pNocomboBox;	//人员编号下拉框
	private JComboBox mNocomboBox;	//物料代码下拉框
	private JTextArea remarktextArea;	//备注富文本框
	private DefaultTableModel model;	//表格模型
	private DatePicker EdatePicker;	//开始时间
	private DatePicker SdatePicker;	//结束时间
	private JSeparator separator;
	
	public querySimpleFrame() {
		setTitle("简单物料进/出仓查询");
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		setResizable(false);	//窗体大小不可改变
		JPanel contentPane = new MyPanel();	//主面板
		contentPane.setFocusTraversalPolicyProvider(true);
	    setContentPane(contentPane);
	    contentPane.setLayout(null);	//无布局
	    
	    JLabel lblNewLabel = new JLabel("起止日期：");
	    lblNewLabel.setBounds(32, 36, 66, 15);
	    contentPane.add(lblNewLabel);
	    	    
	    JLabel lblNewLabel_1 = new JLabel("至");
	    lblNewLabel_1.setBounds(196, 36, 24, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    lblNewLabel_2 = new JLabel("物料代码：");
	    lblNewLabel_2.setBounds(209, 86, 66, 15);
	    contentPane.add(lblNewLabel_2);
	    
	    lblNewLabel_3 = new JLabel("人员代码：");
	    lblNewLabel_3.setBounds(32, 86, 66, 15);
	    contentPane.add(lblNewLabel_3);
	    
	    lblNewLabel_4 = new JLabel("备注：");
	    lblNewLabel_4.setBounds(330, 36, 54, 15);
	    contentPane.add(lblNewLabel_4);
	    
	    //从数据库物料表 获取人员编号显示在物料代码下拉框中
	    List<MatterBean> list2 = Jdbcexecute_matters.queryall();	//查询全部物料信息 保存在集合
	    String mNo[] = new String[list2.size() + 1];		//定义数组保存编号
	    for(int i = 0; i < list2.size(); i++) {		//遍历集合 获取物料编号
	    	MatterBean mat = list2.get(i);	
	    	mNo[i + 1] = mat.getMatterid();
	    }
	    mNocomboBox = new JComboBox(mNo);
	    mNocomboBox.setBounds(277, 83, 66, 21);
	    contentPane.add(mNocomboBox);
	    
	  //从数据库人员表 获取人员编号显示在人员编号下拉框中
	    List<personBean> list = sqlHelper_person.queryAll();		//查询全部人员信息 保存在集合
	    String pNo[] = new String[list.size() + 1];			//定义数组保存编号
	    for(int i = 0; i < list.size(); i++) {		//遍历集合 获取人员编号
	    	personBean per= list.get(i);
	    	pNo[i + 1] = per.getNo();
	    }
	    pNocomboBox = new JComboBox(pNo);
	    pNocomboBox.setBounds(108, 83, 66, 21);
	    contentPane.add(pNocomboBox);
	    
	    remarktextArea = new JTextArea();
	    remarktextArea.setBounds(394, 32, 161, 76);
	    contentPane.add(remarktextArea);
	    
	    JScrollPane scrollPane = new JScrollPane();		//滚动面板
	    scrollPane.setBounds(32, 138, 526, 129);
	    contentPane.add(scrollPane);
	    
	    simpletable = new JTable();
	    scrollPane.add(simpletable);
	    simpletable.setRowHeight(20);	//表格行高
	    
	    model = (DefaultTableModel) simpletable.getModel();	
	    //设置表头
	    model.setColumnIdentifiers(new Object[] { "订单号", "日期", "人员代码", "备注", "物料代码", "进出仓数量", "状态"});
	    scrollPane.setViewportView(simpletable);   //显示表  
	    
	    JButton btnNewButton_1 = new JButton("返  回");
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
	    
	    JButton btnNewButton = new JButton("显示全部");
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
	    
	    JButton queryButton = new JButton("查  询");
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
			pno = pNocomboBox.getSelectedItem().toString();	//获取选择的下拉框的值
		}
		
		if (mNocomboBox.getSelectedItem() == null) {
			mno = "";
		}else {
			mno = mNocomboBox.getSelectedItem().toString();	//获取选择的下拉框的值
		}
		String remarks = remarktextArea.getText().trim();	//获取备注富文本框的值
		Date start = (Date) SdatePicker.getValue();	//获取日期控件的值    java.util.Date类型 （Wed Apr 11 00:00:00 CST 2018）	
		Date end = (Date) EdatePicker.getValue();

	//System.out.println(end);
		SimpleBean simple = new SimpleBean();
		simple.setNo(pno);
		simple.setMatterid(mno);
		simple.setRemarks(remarks);
		simple.setStartd(start);
		simple.setEndd(end);
	//System.out.println(pno);
		List<SimpleBean> result = sqlHelper_simple.querySimple(simple);	//调用查询进出仓表 方法
		if (result.size() > 0) {	//result集合中有数据
			model.setRowCount(0);	//清空表中的数据
			for (SimpleBean sim : result) {	//遍历集合
				//将得到的数据添加到表中
				model.addRow(new Object[] { sim.getOrderID(), sim.getInoutDate(), sim.getNo(), sim.getRemarks(),
						sim.getMatterid(), sim.getInoutCount(), sim.getState()});
			}
			simpletable.setModel(model);
		}
		else {
			 JOptionPane.showMessageDialog(this, "无符合条件的结果！");
		}	
	}
	
	//显示全部进出仓信息
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







