package main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import bean.authorityBean;
import bean.personBean;
import dao.sqlHelper_person;
import util.MyPanel;
import view_chart.MaterielReport;
import view_matter.AdditionMattersframe;
import view_matter.DeleteMattersframe;
import view_matter.ModifyMattersframe;
import view_matter.QueryMattersframe;
import view_multi.multiStoreFrame;
import view_person.addPersonframe;
import view_person.authorityFrame;
import view_person.deletePersonFrame;
import view_person.queryPersonFrame;
import view_person.updatePersonFrame;
import view_simple.querySimpleFrame;
import view_simple.simpleStoreFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class mainFrame extends JFrame  {
	private static final long serialVersionUID = 7460318756113382912L;
	 JMenuItem addP;
	 JMenuItem updateP;
	 JMenuItem delP;
	 JMenuItem queryP;
	 JMenuItem addM, updateM, delM, queryM;
	 JMenuItem simple, qSimple, multi;
	 JMenuItem authority;
	 String no;
	 private JMenuItem mntmNewMenuItem;
	 private JMenuItem mntmNewMenuItem_1;
	 private JMenuItem mntmNewMenuItem_2;
	 JPanel panel;
	
//	public static void main(String[] args) {
//		mainFrame frame = new mainFrame();
//		frame.setVisible(true);
//	}
	
	public mainFrame(String no) {
		
		setTitle("管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		setResizable(false);
		panel = new MyPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("人员档案");
		menuBar.add(menu);
		
		addP = new JMenuItem("增加人员");
		menu.add(addP);
		addP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPersonframe addf = new addPersonframe();
				addf.setVisible(true);
			}
		});
		

		updateP = new JMenuItem("修改人员");
		menu.add(updateP);
		updateP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePersonFrame updateqFrame = new updatePersonFrame();
				updateqFrame.setVisible(true);
			}
		});
		
		
		delP = new JMenuItem("删除人员");
		menu.add(delP);
		delP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePersonFrame delF = new deletePersonFrame();
				delF.setVisible(true);	
			}
		});
		
		queryP = new JMenuItem("查询人员");
		menu.add(queryP);
		queryP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryPersonFrame queryF = new queryPersonFrame();
				queryF.setVisible(true);
			}
		});
		
		JMenu mnNewMenu = new JMenu("物料档案");
		menuBar.add(mnNewMenu);
		
		addM = new JMenuItem("增加物品");
		addM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdditionMattersframe additionMattersframe = new AdditionMattersframe();
				additionMattersframe.setVisible(true);
			}
		});
		mnNewMenu.add(addM);
		
		
		delM = new JMenuItem("删除物品");
		delM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteMattersframe deleteMattersframe = new DeleteMattersframe();
				deleteMattersframe.setVisible(true);
			}
		});
		mnNewMenu.add(delM);
		
		updateM = new JMenuItem("修改物品");
		updateM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyMattersframe modifyMattersframe = new ModifyMattersframe();
				modifyMattersframe.setVisible(true);
			}
		});
		mnNewMenu.add(updateM);
		
		queryM = new JMenuItem("查找物品");
		queryM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryMattersframe queryMattersframe = new QueryMattersframe();
				queryMattersframe.setVisible(true);
			}
		});
		mnNewMenu.add(queryM);
		
		JMenu mnNewMenu_1 = new JMenu("简单物料进出仓");
		menuBar.add(mnNewMenu_1);
		
		simple = new JMenuItem("简单物料进出仓");
		mnNewMenu_1.add(simple);
		simple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simpleStoreFrame sInStore = new simpleStoreFrame();
				sInStore.setVisible(true);
			}
		});
		
		qSimple = new JMenuItem("进出仓查询");
		qSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				querySimpleFrame qSimple = new querySimpleFrame();
				qSimple.setVisible(true);
			}
		});
		mnNewMenu_1.add(qSimple);
		
		JMenu mnNewMenu_2 = new JMenu("多物料进出仓");
		menuBar.add(mnNewMenu_2);
		
		multi = new JMenuItem("多物料进出仓");
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				multiStoreFrame multi = new multiStoreFrame();
				multi.setVisible(true);
			}
		});
		mnNewMenu_2.add(multi);
		
		JMenu mnNewMenu_3 = new JMenu("用户权限");
		menuBar.add(mnNewMenu_3);
		
		
		authority = new JMenuItem("用户权限设置");
		authority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authorityFrame authority = new authorityFrame();
				authority.setVisible(true);
			}
		});
		mnNewMenu_3.add(authority);
		
		
		JMenu mnNewMenu_4 = new JMenu("统计与报表打印");
		menuBar.add(mnNewMenu_4);
		
		mntmNewMenuItem = new JMenuItem("物料统计");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaterielReport mr = new MaterielReport();
				mr.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("进出仓单");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("仓库账本");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_5 = new JMenu("退出系统");
		mnNewMenu_5.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				System.exit(0);
			}
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
		});
		menuBar.add(mnNewMenu_5);
		getContentPane().setLayout(null);
		
//		JLabel lblNewLabel = new JLabel();
//		lblNewLabel.setBounds(0, 0, 612, 351);
//		getContentPane().add(lblNewLabel);
//		
//		ImageIcon image = new ImageIcon("you.jpg");
//		lblNewLabel.setIcon(image);
		
		
		setAuthorit(no);
		
	}
	
	/**
	 * 通过人员表的权限字段查询
	 */
	public void setAuthorit(String no) {
		//System.out.println("type: " + no);
		personBean person = new personBean();
		List<personBean> list = sqlHelper_person.queryAuthority(no);
		
		String ptype = null;
		for(int i=0; i< list.size(); i++) {
			ptype = list.get(i).getAuthority();
		}
		char[] ch = new char[ptype.length()];	
		ch = ptype.toCharArray();
		
		JMenuItem[] jm = new JMenuItem[] {addP, updateP, delP, queryP, addM, delM, updateM, queryM, simple, qSimple, multi, authority};
		for(int i = 0; i<jm.length; i++ ) {
			if ('0' ==ch[i]) {
				System.out.println("0" + " ");
				jm[i].setEnabled(false);
			}
			if ('1' ==ch[i]) {
				System.out.println("1");
				jm[i].setEnabled(true);
			}
		}
	}
}
