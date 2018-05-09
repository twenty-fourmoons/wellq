package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import bean.authorityBean;
import bean.personBean;
import main.loginFrame;
import util.MD5Util;

public class sqlHelper_person implements sqlConfig {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
     // ������ݿ������
    private static Connection getConnection()  {
        
        
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
    
    
    //������Ա��Ϣ�����ݿ�
    public static  int save(personBean person) {
    	String sql = "insert into wg215_person (no, name, sex, birthdate, idcard, place, address, telphone, passwd, authority) "
    			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    	conn = getConnection();
    	String passwd = MD5Util.md5(person.getNo());
    	String auth = "000100010100";	//Ĭ��Ȩ�� ֻ�ܲ�ѯ
    	try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, person.getNo());
			ps.setString(2, person.getName());
			ps.setString(3, person.getSex());
			ps.setDate(4, new Date(person.getBirth().getTime()) );
			ps.setString(5, person.getIDCard());
			ps.setString(6, person.getPlace());
			ps.setString(7, person.getAddress());
			ps.setString(8, person.getTelphone());
			ps.setString(9, passwd);
			ps.setString(10, auth);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            closeAll(rs, ps, conn);
        }
        return -1;
    }
    
    //�޸���Ա����ĳ�����ݵ�ֵ
    public static int update(personBean person) {
    	String sql = "update wg215_person set name = ?, sex = ?,  birthdate = ?, idcard = ?, "
    			+ "place = ?, address = ?, telphone = ?  where  no = ?;";
    	conn = getConnection();
    	try {
			ps = conn.prepareStatement(sql);			
			ps.setString(1, person.getName());
			ps.setString(2, person.getSex());
			ps.setDate(3, new Date(person.getBirth().getTime()) );
			ps.setString(4, person.getIDCard());
			ps.setString(5, person.getPlace());
			ps.setString(6, person.getAddress());
			ps.setString(7, person.getTelphone());
			ps.setString(8, person.getNo());
			return ps.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            closeAll(rs, ps, conn);
        }
        return -1;
    }
    
 // ��ѯ��Ա��Ϣ��ȫ����¼��ת�浽List��
    public static List<personBean> queryAll() {
    	String sql = "select * from wg215_person;";
    	List<personBean> results = new ArrayList<personBean>() ;
    	conn = getConnection();
    	try {
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			personBean person = new personBean();
    			person.setNo(rs.getString(1));
    			person.setName(rs.getString(2));
    			person.setSex(rs.getString(3));
    			person.setBirth(rs.getDate(4));
    			person.setIDCard(rs.getString(5));
    			person.setPlace(rs.getString(6));
    			person.setAddress(rs.getString(7));
    			person.setTelphone(rs.getString(8));
    			results.add(person);
    		}			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {// �ͷ���Դ
        	closeAll(rs, ps, conn);
        }
    	return results;
    	
    }
    
    //ɾ����Ա
    public static int delete(personBean person) {
    	String sql = "delete from wg215_person where no = " + person.getNo();
        conn = getConnection();
        try {
        	ps = conn.prepareStatement(sql);;
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
        return -1;
    	
    }
    
    // ����ģ����������ѯ�������������List��
    public static List<personBean> query(personBean person) {
    	String Name = person.getName();
    	String No = person.getNo();
    	Date Birthdate = (Date) person.getBirth();
    	String IDCard = person.getIDCard();
    	String Place =person.getPlace();
    	String Address = person.getAddress();
    	String Telphone = person.getTelphone();
    	List<personBean> results = new ArrayList<personBean>();
    	String sql = " select * from wg215_person where name like '%" + Name +  "%' "
    						+ "or no like '%" + No + "%' "
    						+ "or  birthdate like '%" + Birthdate +  "%' "
    						+ "or idcard like '%" + IDCard +  "%' "
    						+ "or place like '%" + Place +  "%' "
    						+ "or address like '%" + Address +  "%' "
    						+ "or telphone like '%" + Telphone +  "%'";    	
    	conn = getConnection();
    	try {
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		if (rs != null ) {
    			while (rs.next()) {
    				personBean per = new personBean();
    				per.setNo(rs.getString("no"));
        			per.setName(rs.getString("name"));
        			per.setSex(rs.getString("sex"));
        			per.setBirth(rs.getDate("birthdate"));
        			per.setIDCard(rs.getString("idcard"));
        			per.setPlace(rs.getString("place"));
        			per.setAddress(rs.getString("address"));
        			per.setTelphone(rs.getString("telphone"));
        			results.add(per);
    			}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
        return results;
    	
    }
    
    //��ѯ���ݿ�max����Ա���
    public static List<personBean> queryMaxNo(personBean person) {
		List<personBean> results = new ArrayList<personBean>();
		String sql = "select max(no) from wg215_person";
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				personBean per = new personBean();
				per.setNo(rs.getString(1));
				results.add(per);
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {// �ͷ���Դ
            closeAll(rs, ps, conn);
        }
    	return null;
    	
    }
    
    //��¼ͨ��no ��ѯ����
    public static List<personBean> queryPaswd(String no) {
    	personBean person = new personBean();
    	List<personBean> results = new ArrayList<personBean>() ;
    	conn = getConnection();
    	try {
			String sql = "select passwd from wg215_person where no = " + no;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				person.setPasswd(rs.getString("passwd"));
	            results.add(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, conn);
		}
		return results;
    }
    
    //�����û��Ƿ����
    public static boolean findUser(String no) {
		String sql = "select * from wg215_person where no= " + no;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    
   
    
     /*
      *  ͨ��no ��ѯ��Ա���Ȩ��
      */
    public static List<personBean> queryAuthority(String no) {
    	personBean per = new personBean();
    	List<personBean> results = new ArrayList<personBean>() ;
    	conn = getConnection();
    	try {
			String sql = "select authority from wg215_person where no = " + no;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				per.setAuthority((rs.getString("authority")));
	            results.add(per);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, conn);
		}
		return results;
	}
    
    
  //�޸�ĳ��Ա��Ȩ��
    public static int updateAuthority(personBean person) {
		String sql = "update wg215_person set authority = ? where no = ?";
		System.out.println(person.getNo());
		System.out.println(person.getAuthority());
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, person.getAuthority());
			ps.setString(2, person.getNo());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, conn);
		}
    	return -1;
    	
    }
    
    
    
//    //��ѯĳ��Ա�� Ȩ�� �����
//    public static List<authorityBean> queryAuthorityAll(String no) {
//    	conn = getConnection();
//    	List<authorityBean> results = new ArrayList<authorityBean>();
//    	String sql = "select * from wg215_authority where no = " + no;
//    	try {
//        	ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//		    	authorityBean auth = new authorityBean();
//				auth.setPadd(rs.getString(2));
//				auth.setPdelete(rs.getString(3));
//				auth.setPupdate(rs.getString(4));
//				auth.setPquery(rs.getString(5));
//				auth.setMadd(rs.getString(6));
//				auth.setMdelete(rs.getString(7));
//				auth.setMupdate(rs.getString(8));
//				auth.setMquery(rs.getString(9));
//				auth.setSimple(rs.getString(10));
//				auth.setQsimple(rs.getString(11));
//				auth.setMulti(rs.getString(12));
//				auth.setAuthority(rs.getString(13));
//				results.add(auth);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			closeAll(rs, ps, conn);
//		}
//		return results;
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
