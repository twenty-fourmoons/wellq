package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bean.authorityBean;
import bean.personBean;
import bean.userBean;
import dao.sqlHelper_person;
import util.MD5Util;
import util.MyPanel;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;

public class loginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	String no;
	
	public loginFrame() {
		setTitle("登录");
		setBounds(400, 200, 300, 220);// 设置面板位置
		setResizable(false);
		contentPane = new MyPanel();
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("用户名：");
	    lblNewLabel.setBounds(42, 38, 54, 15);
	    contentPane.add(lblNewLabel);
	    
	    textField = new JTextField();
	    textField.setBounds(106, 35, 133, 21);
	    contentPane.add(textField);
	    textField.setColumns(10);
	    
	    JLabel lblNewLabel_1 = new JLabel("密  码：");
	    lblNewLabel_1.setBounds(42, 90, 54, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    passwordField = new JPasswordField();
	    passwordField.setBounds(106, 87, 133, 21);
	    contentPane.add(passwordField);
	    
	    JButton btnNewButton = new JButton("登  录");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		do_login();
	    	}
	    });
	    btnNewButton.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == ke.VK_ENTER) {
					do_login();
				}
			}
		});
	    btnNewButton.setBounds(102, 142, 93, 23);
	    contentPane.add(btnNewButton);
	   
	    
	    
	    
	}


	//点击登录事件
	public void do_login() {
		String no = textField.getText();
		char[] ch = passwordField.getPassword();
		String passwd = String.valueOf(ch);
		String newpasswd = MD5Util.md5(passwd);
		authorityBean au =  new authorityBean();
		personBean person = new personBean();
		person.setNo(no);
		au.setNo(no);
//System.out.println(" MD5:    " + newpasswd);
		List<personBean> list = sqlHelper_person.queryPaswd(no);
		String[] pass = new String[1];	//查到的密码
		
		if (sqlHelper_person.findUser(no)) {
			for( int i = 0; i < list.size(); i++) {		
				pass[0] = list.get(i).getPasswd();
	 		}
			if (newpasswd.equals(pass[0])) {
				JOptionPane.showMessageDialog(null,"登录成功！");
				mainFrame main = new mainFrame(no);
				main.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "密码错误！");
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "用户不存在！");
			return;
		}
		 
	}
	
	
	
	
	
	public static void main(String []args) {
		loginFrame login = new loginFrame();
		login.setVisible(true);
		
 	}
}









