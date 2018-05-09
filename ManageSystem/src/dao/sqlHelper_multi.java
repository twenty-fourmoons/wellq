package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.multiBean;
import bean.MatterBean;
import bean.SimpleBean;

public class sqlHelper_multi implements sqlConfig {
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
    
    ////保存到数据库
    public static boolean addMulti(multiBean multi) {
    	String sql = "{call wg215_multiPro(?, ?, ?, ?, ?, ?, ?) }";
    	conn = getConnection();
    	CallableStatement cs = null;
    	try {
			cs = conn.prepareCall(sql);     //执行存储过程
			cs.setString(1, multi.getOrderID());         //
			cs.setDate(2, new java.sql.Date(multi.getInoutDate().getTime()));   //日期转换
			cs.setString(3, multi.getNo());
			cs.setString(4, multi.getRemarks());
			cs.setString(5, multi.getMatterid());
			cs.setInt(6, multi.getInoutCount());
			cs.setString(7, multi.getState());
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
  //查询数据库max的订单号
    public static List<multiBean> queryMaxOrderID(multiBean multi ) {
		List<multiBean> results = new ArrayList<multiBean>();
		String sql = "select max(orderID) from wg215_multiSame ";
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				multiBean mult = new multiBean();
				mult.setOrderID(rs.getString(1));
				results.add(mult);
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {// 释放资源
            closeAll(rs, ps, conn);
        }
    	return null;
    }
    
    
    

}
