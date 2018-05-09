package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import bean.MatterBean;
import bean.SimpleBean;
import bean.personBean;

public class sqlHelper_simple implements sqlConfig {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
    // 获得数据库的连接
    private static  Connection getConnection()  {
        Connection conn = null;
        try {
            Class.forName(DRIVER);// 加载数据库驱动
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 获得数据库连接
            System.out.println("成功！！！！");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("d");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ddd");
        }
        return null;
    }
    //关闭数据库连接
    public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
    	try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    //调用进 、出仓存储过程
    public static boolean savaInOutStore(SimpleBean simple) {
    	String sql = "{call wg215_simplePro(?, ?, ?, ?, ?, ?, ?, ?) }";
    	conn = getConnection();
    	CallableStatement cs = null;
    	try {
			cs = conn.prepareCall(sql);     //执行存储过程
			cs.setString(1, simple.getOrderID());         //
			cs.setDate(2, new java.sql.Date(simple.getInoutDate().getTime()));   //日期转换
			cs.setString(3, simple.getNo());
			cs.setString(4, simple.getRemarks());
			cs.setString(5, simple.getMatterid());
			cs.setInt(6, simple.getInoutCount());
			cs.setString(7, simple.getState());
			cs.setString(8, "");
    		cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    closeAll(rs, ps, conn);
		    try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
    }
    
    //查询进出仓  人员编号  物料代码  备注  起止时间
    public static List<SimpleBean> querySimple (SimpleBean simple) throws ParseException {
		conn = getConnection();
		List<SimpleBean> results = new ArrayList<SimpleBean>();    //集合存储查询到数据
    	StringBuilder sql = new StringBuilder("select * from wg215_simple where 1=1" );
    	//按人员编号
		if (!simple.getNo().isEmpty()) {
			sql.append(" and no = " + simple.getNo());
		}
		
		//按物料代码
		if (!simple.getMatterid().isEmpty()) {
			sql.append(" and matterid = " + simple.getMatterid());
		}
		
		//按备注--模糊查询
		if (!simple.getRemarks().isEmpty()) {
			sql.append(" and remarks like '%" + simple.getRemarks() + "%'");
		}
		
		//按起止日期
 		if (simple.getStartd() != null && simple.getEndd() != null) {
			sql.append(" and inoutDate between '" + new Date(simple.getStartd().getTime()) + "' and '" + new Date(simple.getEndd().getTime()) + "'");
		}
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()) {
				SimpleBean sim = new SimpleBean();
				sim.setOrderID(rs.getString(1));
				sim.setInoutDate(rs.getDate(2));
				sim.setNo(rs.getString(3));
				sim.setRemarks(rs.getString(4));
				sim.setMatterid(rs.getString(5));
				sim.setInoutCount(rs.getInt(6));
				sim.setState(rs.getString(7));
				results.add(sim);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, ps, conn);
		}
		return results;   	
    }
    //判断库存不足     按物料代码 查询查库存数量amount
    public static List<MatterBean> queryAmount(MatterBean matter) {
		List<MatterBean> results = new ArrayList<MatterBean>();    //集合存储查询到的数据
		String sql = "select amount from wg215_matter where matterid = " +  matter.getMatterid();
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MatterBean matter1 = new MatterBean();
				matter1.setAmount(rs.getInt("amount"));
				results.add(matter1);
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {// 释放资源
            closeAll(rs, ps, conn);
        }
		return null;
    	
    }
    
    //查询全部进出仓信息
    public static List<SimpleBean> querySimpleAll() {
		String sql = "select * from wg215_simple;";
		List<SimpleBean> results = new ArrayList<>();
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SimpleBean sim = new SimpleBean();
				sim.setOrderID(rs.getString(1));
				sim.setInoutDate(rs.getDate(2));
				sim.setNo(rs.getString(3));
				sim.setRemarks(rs.getString(4));
				sim.setMatterid(rs.getString(5));
				sim.setInoutCount(rs.getInt(6));
				sim.setState(rs.getString(7));
				results.add(sim);
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    closeAll(rs, ps, conn);
		}
    	return null;
	}
    
      //查询数据库max的订单号
    public static List<SimpleBean> queryMaxOrderID(SimpleBean simple ) {
		List<SimpleBean> results = new ArrayList<SimpleBean>();
		String sql = "select max(orderID) from wg215_simple ";
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SimpleBean sim = new SimpleBean();
				sim.setOrderID(rs.getString(1));
				results.add(sim);
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {// 释放资源
            closeAll(rs, ps, conn);
        }
    	return null;
    }
    
    
    //查询进仓 按时间 的总数量
    public static List<SimpleBean> queryDate(String sd, String ed) {
    	List<SimpleBean> results = new ArrayList<SimpleBean>();
    	String state = "进仓";
		String sql = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
				+ "between  '2018-04-01' and '2018-04-25'  and state =  '"+ state + "' GROUP BY matterid ";
		
    	String sql2 = null;
    		sql2 = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
    				+ "between  '" + sd + "' and '" + ed + "' and state = '" + state +"' GROUP BY matterid ";
		
//    	String sql1 = "SELECT a.matterid,inSum,outSum from "
//    			+ "(SELECT matterid,sum(inoutCount) as inSum from wg215_simple "
//    			+ "where state='进仓' and inoutDate between '2018-04-01' and '2018-04-25' "
//    			+ "GROUP BY matterid) as a full join "
//    			+ "(select matterid, sum(inoutCount) as outSum from wg215_simple "
//    			+ "where state='出仓' and inoutDate between '2018-04-01' and '2018-04-25' "
//    			+ "GROUP BY matterid) as b on a.matterid=b.matterid";
		
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()) {
				SimpleBean sim = new SimpleBean();
				sim.setMatterid(rs.getString("matterid"));
				sim.setInoutCount(rs.getInt("inoutCount"));
				sim.setState(state);
				results.add(sim);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {// 释放资源
            closeAll(rs, ps, conn);
        }
    	return results;
	}
    
    //查询进仓 按时间 的总数量
    public static List<SimpleBean> queryDate1(String sd, String ed) {
    	List<SimpleBean> results = new ArrayList<SimpleBean>();
    	String state = "出仓";
		String sql = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
				+ "between  '2018-04-01' and '2018-04-25'  and state = '" + state+"' GROUP BY matterid ";
		
    	String sql2 = null;
    		sql2 = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
    				+ "between  '" + sd + "' and '" + ed + "' and state = '" + state +"' GROUP BY matterid ";
    	
    	
    	
//    	String sql1 = "SELECT a.matterid,inSum,outSum from "
//    			+ "(SELECT matterid,sum(inoutCount) as inSum from wg215_simple "
//    			+ "where state='进仓' and inoutDate between '2018-04-01' and '2018-04-25' "
//    			+ "GROUP BY matterid) as a full join "
//    			+ "(select matterid, sum(inoutCount) as outSum from wg215_simple "
//    			+ "where state='出仓' and inoutDate between '2018-04-01' and '2018-04-25' "
//    			+ "GROUP BY matterid) as b on a.matterid=b.matterid";
		
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()) {
				SimpleBean sim = new SimpleBean();
				sim.setMatterid(rs.getString("matterid"));
				sim.setInoutCount(rs.getInt("inoutCount"));
				sim.setState(state);
				results.add(sim);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {// 释放资源
            closeAll(rs, ps, conn);
        }
    	return results;
	}
    
  
}



















