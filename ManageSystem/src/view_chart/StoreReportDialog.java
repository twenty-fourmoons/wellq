package view_chart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

public class StoreReportDialog extends JDialog {
	JPanel ReportPan = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	String pmonth = "";
	JRViewer jrViewer;
	
	public StoreReportDialog (String month) {
		setTitle("½ø³ö²Öµ¥");
		pmonth = month;
		try {
			jbInit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {  
	      Connection conn = null;  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=wlgc215", "sa", "666666");  
	        return conn;  
	}
	
	private void jbInit() throws JRException, ClassNotFoundException, SQLException {
		ReportPan.setLayout(borderLayout1);
		getContentPane().add(ReportPan);
		String reportPath = "G:\\java-eclipse(mars2)\\workspa\\ManageSystem\\Reports\\report2.jasper";
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
		
		Map parameters = new HashMap();
		parameters.put("month", pmonth);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, getConnection());
		
		jrViewer = new JRViewer(jasperPrint);
		
//		ReportPan.setLayout(borderLayout1);
//		ReportPan.setPreferredSize(new Dimension(1800, 600));
//		getContentPane().add(ReportPan, BorderLayout.CENTER);
		ReportPan.add(jrViewer, null);
		
		
		
	}

}
