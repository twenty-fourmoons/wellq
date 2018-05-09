package view_chart;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import bean.MatterBean;
import bean.SimpleBean;
import dao.sqlHelper_simple;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sunking.swing.JDatePicker;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.eltima.components.ui.DatePicker;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

 

public class MaterielReport extends JFrame{
	private JPanel contentPane;
	private  JFreeChart chart;
	private JPanel datepanel, chartPanel;
	private  DatePicker start;
	private  DatePicker end;
	private  CategoryPlot plot;
	CategoryDataset dataset;
	private String sd;
	private  String ed;
	
	public  MaterielReport() {
		contentPane = new JPanel();		//主面板
	    setContentPane(contentPane);
	    setSize(600, 400);	//窗体大小
		setLocationRelativeTo(null);// 居中显示
		contentPane.setLayout(new BorderLayout()); //无布局
	    
	    datepanel = new JPanel(null);
	    datepanel.setPreferredSize(new Dimension(600, 80));
	    contentPane.add(datepanel, BorderLayout.SOUTH);
	    
//	    calpanel = new JPanel(null);
//	    calpanel.setPreferredSize(new Dimension(100, 180));
//	    contentPane.add(calpanel, BorderLayout.EAST);
	    
	    JLabel lblNewLabel = new JLabel("到");
	    lblNewLabel.setBounds(154, 30, 54, 15);
	    datepanel.add(lblNewLabel);
	    
	    JButton calButton = new JButton("统  计");
	    calButton.setBounds(322, 28, 93, 23);
	    datepanel.add(calButton);
	    
	    start = new DatePicker();
	    start.setBounds(34, 30, 99, 21);
	    datepanel.add(start);
	    
	    end = new DatePicker();
	    end.setBounds(188, 30, 99, 21);
	    datepanel.add(end);
	    
	    JSeparator separator = new JSeparator();
	    separator.setBounds(0, 10, 584, 2);
	    datepanel.add(separator);
	    calButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chart = createChart(getDateSet(sd, ed));
				chartPanel = new ChartPanel(chart);
				contentPane.add(chartPanel, BorderLayout.CENTER);
			}
		});
	}
	
	
	//用数据集创建一个柱状图
	public  JFreeChart createChart(CategoryDataset dataset) {
	    chart = ChartFactory.createBarChart3D(
	    		"物料柱状图",	// 图表标题  
	    		"物料代码",// 目录轴的显示标签--横轴  
	    		"数量",// 数值轴的显示标签--纵轴  
	    		dataset,// 数据集  
	    		PlotOrientation.VERTICAL, // 图表方向：水平、
	    		true,// 是否显示图例(对于简单的柱状图必须  
	    		false,// 是否生成工具  
	    		true// 是否生成URL链接  
	    );
	    plot = chart.getCategoryPlot();
	    //在柱状图上显示数值
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setBaseItemLabelsVisible(true);
	    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
	    
	    //轴字体
	    CategoryAxis domaimAxis = plot.getDomainAxis();
	    domaimAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));// 轴数值
	    domaimAxis.setLabelFont(new Font("宋体", Font.BOLD, 14)); // 轴标题
	    //Y轴字体
	    ValueAxis rangeAxis = plot.getRangeAxis();
	    rangeAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));
	    rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
	    
	    chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
	    chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));
		return chart;
	}
	
	
	
	//获取柱状图 数据集
	private  CategoryDataset getDateSet(String sd, String ed) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = (Date) start.getValue();
		Date eDate = (Date) end.getValue();
		sd = sdf.format(sDate);
		ed = sdf.format(eDate);
//		sd = "2018-04-19";
//		ed = "2018-04-20";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<SimpleBean> list = sqlHelper_simple.queryDate(sd, ed);
		List<SimpleBean> list1 = sqlHelper_simple.queryDate1(sd, ed);
		List<SimpleBean> list2  = new ArrayList<SimpleBean>();
		list2.addAll(list);
		list2.addAll(list1);
		System.out.println(list2.size());
		if (list2.size() == 0 || list2 == null) {
			JOptionPane.showMessageDialog(this, "无相关记录！！");
		}
		else {
			for (SimpleBean sim : list2) {
				dataset.setValue(sim.getInoutCount(), sim.getState(), sim.getMatterid());
			}
		}
		
//		System.out.println(list2.get(0).getInoutCount());
//		System.out.println(list2.get(0).getMatterid());
//		System.out.println(list2.get(0).getState());
//		
//		System.out.println(list2.get(1).getInoutCount());
//		System.out.println(list2.get(1).getMatterid());
//		System.out.println(list2.get(1).getState());
//		
//		System.out.println(list2.get(2).getInoutCount());
//		System.out.println(list2.get(2).getMatterid());
//		System.out.println(list2.get(2).getState());
		return dataset;
	}
	
	
//	public static void main(String[] args) {
//		MaterielReport mr = new MaterielReport();
//		mr.setVisible(true);
//		
//	}
}











