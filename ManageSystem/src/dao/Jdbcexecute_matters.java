package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import bean.MatterBean;

public class Jdbcexecute_matters  implements sqlConfig{
    protected static String Addition="insert into wg215_matter values(?,?,?,?,?,?)";
    protected static String Delete="delete from wg215_matter where matterid=?";
    protected static String Update="update wg215_matter set matterid=?,mattername=?,spec=?,measurement=?,amount=?,other=? where matterid=?";
    protected static String Query="select * from wg215_matter where 1=1 ";
    protected static String Queryforid="select * from wg215_matter where matterid=?";
    
    // 获得数据库的连接
    private static Connection getConnection()  {
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
    
    
    public static int addition(MatterBean matter) {
    	
    	Connection con=getConnection();
    	PreparedStatement ps=null;
    	try {
			ps=con.prepareStatement(Addition);
			ps.setString(1, matter.getMatterid());
			ps.setString(2, matter.getMattername());
			ps.setString(3, matter.getSpec());
			ps.setString(4, matter.getMeasurement());
			ps.setInt(5, matter.getAmount());
			ps.setString(6, matter.getOther());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
    	return -1;
    }
    
    
    public static int delete(MatterBean matter) {
    	Connection con=getConnection();
    	PreparedStatement ps=null;
    	try {
			ps=con.prepareStatement(Delete);
			ps.setString(1, matter.getMatterid());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
    	return -1;
    }
    
    public static java.util.List<MatterBean> query(MatterBean matter){
    	Connection con=getConnection();
    	java.util.List<MatterBean> results=new ArrayList<MatterBean>();
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	StringBuilder query=new StringBuilder("select * from wg215_matter where 1=1 ");
    	if(!matter.getMatterid().isEmpty())
    		query.append(" and matterid like'%"+matter.getMatterid()+"%' ");
    	if(!matter.getMattername().isEmpty())
    		query.append(" and mattername like'%"+matter.getMattername()+"%' ");
    	if(!matter.getSpec().isEmpty())
    		query.append(" and spec like'%"+matter.getSpec()+"%' ");
    	try {
			ps=con.prepareStatement(query.toString());
			rs=ps.executeQuery();
			while(rs.next()) {
				MatterBean mat=new MatterBean();
				mat.setMatterid(rs.getString("matterid"));
				mat.setMattername(rs.getString("mattername"));
				mat.setSpec(rs.getString("spec"));
				mat.setMeasurement(rs.getString("measurement"));
				mat.setOther(rs.getString("other"));
				results.add(mat);
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
    	return null;
    }

  
    public static MatterBean queryone(MatterBean matterBean) {
    	Connection con=getConnection();
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	MatterBean matter=new MatterBean();
    	try {
			ps=con.prepareStatement(Queryforid);
			ps.setString(1, matterBean.getMatterid());
			rs=ps.executeQuery();
			while(rs.next()) {
				matter.setMatterid(rs.getString("matterid"));
				matter.setMattername(rs.getString("mattername"));
				matter.setSpec(rs.getString("spec"));
				matter.setMeasurement(rs.getString("measurement"));
				matter.setAmount(rs.getInt("amount"));
				matter.setOther(rs.getString("other"));	
			}
			return matter;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
    	return null;
    }

    
    public static java.util.List<MatterBean> queryall(){
    	String sql="select * from wg215_matter;";
    	java.util.List<MatterBean> results=new ArrayList<MatterBean>();
    	Connection con=getConnection();
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				MatterBean matter=new MatterBean();
				matter.setMatterid(rs.getString("matterid"));
				matter.setMattername(rs.getString("mattername"));
				matter.setSpec(rs.getString("spec"));
				matter.setMeasurement(rs.getString("measurement"));
				matter.setAmount(rs.getInt("amount"));
				matter.setOther(rs.getString("other"));
				results.add(matter);
			}
			return results;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
    	return null;
    }
    
    public static int updateforquery(MatterBean matter,MatterBean matter1) {
    	Connection con=getConnection();
    	PreparedStatement ps=null;
    	try {
			ps=con.prepareStatement(Update);
			ps.setString(1, matter.getMatterid());
			ps.setString(2, matter.getMattername());
			ps.setString(3, matter.getSpec());
			ps.setString(4, matter.getMeasurement());
			ps.setInt(5, matter.getAmount());
			ps.setString(6, matter.getOther());
			ps.setString(7, matter1.getMatterid());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    	return -1;
    }
}
