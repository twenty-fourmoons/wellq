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
    // ������ݿ������
    private static  Connection getConnection()  {
        Connection conn = null;
        try {
            Class.forName(DRIVER);// �������ݿ�����
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ������ݿ�����
            System.out.println("�ɹ���������");
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
    //�ر����ݿ�����
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
    
    //���ý� �����ִ洢����
    public static boolean savaInOutStore(SimpleBean simple) {
    	String sql = "{call wg215_simplePro(?, ?, ?, ?, ?, ?, ?, ?) }";
    	conn = getConnection();
    	CallableStatement cs = null;
    	try {
			cs = conn.prepareCall(sql);     //ִ�д洢����
			cs.setString(1, simple.getOrderID());         //
			cs.setDate(2, new java.sql.Date(simple.getInoutDate().getTime()));   //����ת��
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
    
    //��ѯ������  ��Ա���  ���ϴ���  ��ע  ��ֹʱ��
    public static List<SimpleBean> querySimple (SimpleBean simple) throws ParseException {
		conn = getConnection();
		List<SimpleBean> results = new ArrayList<SimpleBean>();    //���ϴ洢��ѯ������
    	StringBuilder sql = new StringBuilder("select * from wg215_simple where 1=1" );
    	//����Ա���
		if (!simple.getNo().isEmpty()) {
			sql.append(" and no = " + simple.getNo());
		}
		
		//�����ϴ���
		if (!simple.getMatterid().isEmpty()) {
			sql.append(" and matterid = " + simple.getMatterid());
		}
		
		//����ע--ģ����ѯ
		if (!simple.getRemarks().isEmpty()) {
			sql.append(" and remarks like '%" + simple.getRemarks() + "%'");
		}
		
		//����ֹ����
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
    //�жϿ�治��     �����ϴ��� ��ѯ��������amount
    public static List<MatterBean> queryAmount(MatterBean matter) {
		List<MatterBean> results = new ArrayList<MatterBean>();    //���ϴ洢��ѯ��������
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
		}finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
		return null;
    	
    }
    
    //��ѯȫ����������Ϣ
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
    
      //��ѯ���ݿ�max�Ķ�����
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
		}finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
    	return null;
    }
    
    
    //��ѯ���� ��ʱ�� ��������
    public static List<SimpleBean> queryDate(String sd, String ed) {
    	List<SimpleBean> results = new ArrayList<SimpleBean>();
    	String state = "����";
		String sql = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
				+ "between  '2018-04-01' and '2018-04-25'  and state =  '"+ state + "' GROUP BY matterid ";
		
    	String sql2 = null;
    		sql2 = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
    				+ "between  '" + sd + "' and '" + ed + "' and state = '" + state +"' GROUP BY matterid ";
		
//    	String sql1 = "SELECT a.matterid,inSum,outSum from "
//    			+ "(SELECT matterid,sum(inoutCount) as inSum from wg215_simple "
//    			+ "where state='����' and inoutDate between '2018-04-01' and '2018-04-25' "
//    			+ "GROUP BY matterid) as a full join "
//    			+ "(select matterid, sum(inoutCount) as outSum from wg215_simple "
//    			+ "where state='����' and inoutDate between '2018-04-01' and '2018-04-25' "
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
		}finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
    	return results;
	}
    
    //��ѯ���� ��ʱ�� ��������
    public static List<SimpleBean> queryDate1(String sd, String ed) {
    	List<SimpleBean> results = new ArrayList<SimpleBean>();
    	String state = "����";
		String sql = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
				+ "between  '2018-04-01' and '2018-04-25'  and state = '" + state+"' GROUP BY matterid ";
		
    	String sql2 = null;
    		sql2 = "select matterid, inoutCount = SUM(inoutCount) from wg215_simple where inoutDate "
    				+ "between  '" + sd + "' and '" + ed + "' and state = '" + state +"' GROUP BY matterid ";
    	
    	
    	
//    	String sql1 = "SELECT a.matterid,inSum,outSum from "
//    			+ "(SELECT matterid,sum(inoutCount) as inSum from wg215_simple "
//    			+ "where state='����' and inoutDate between '2018-04-01' and '2018-04-25' "
//    			+ "GROUP BY matterid) as a full join "
//    			+ "(select matterid, sum(inoutCount) as outSum from wg215_simple "
//    			+ "where state='����' and inoutDate between '2018-04-01' and '2018-04-25' "
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
		}finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
    	return results;
	}
    
  
}



















