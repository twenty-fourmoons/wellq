package view_chart;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import dao.sqlConfig;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

public class createPDF {
	Connection conn = null;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Map paramters = new HashMap();
		paramters.put("reportTitle", "我的第一个程序");

		ByteArrayOutputStream outPut=new ByteArrayOutputStream();
		FileOutputStream outputStream = null;
		String outpath = "";
		File file = new File("I:/Users/q/aaa.pdf");
		String filename = "I:/Users/q/report2.jasper";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		try {
			//InputStream inputStream = new FileInputStream(filename);
						
			jasperPrint = JasperFillManager.fillReport(filename, paramters, getConnection());
			//JasperExportManager.exportReportToHtmlFile(jasperPrint, "first.html"); 
			
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);
			pdfExporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED,Boolean.TRUE);  
            //加密  
			pdfExporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,Boolean.TRUE); 
			pdfExporter.exportReport();
			outputStream = new FileOutputStream(file);
			outputStream.write(outPut.toByteArray());
			System.out.println("成功！");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try {
            	outPut.flush();
				outPut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	  public static Connection getConnection() throws ClassNotFoundException, SQLException {  
	        Connection conn = null;  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=wlgc215", "sa", "666666");  
	        return conn;  
	    }

}
