package com.seeyon.apps.develop.nhcm.util;

import com.seeyon.ctp.common.AppContext;

import javax.sql.DataSource;
import java.sql.*;


public class DBUtil_OA {
	private static ThreadLocal<Connection> connectionHolder=new ThreadLocal<Connection>();
		//private static Connection conn;
		public static Connection getConnection(){
			Connection conn=connectionHolder.get();
			try {
				if(conn == null || conn.isClosed()){
				
					//A8连接池
					//Context ctx = new InitialContext();
					DataSource dataSource = (DataSource) AppContext.getBean("dataSource");
					conn=dataSource.getConnection();
					connectionHolder.set(conn);
					
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return conn;
		}
		
		public static void closeConnection(){
			Connection conn=connectionHolder.get();
			if(conn !=null){
				try {
					conn.close();
					//从ThreadLocal中清除Connection
					connectionHolder.remove();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close(Connection conn) {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close(PreparedStatement pstmt) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close(Statement pstmt) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close(ResultSet rs ) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//开启事务
		public static void beginTransaction(Connection conn) {
			try {
				if (conn != null) {
					if (conn.getAutoCommit()) {
						conn.setAutoCommit(false); //手动提交
					}
				}
			}catch(SQLException e) {}
		}
		
		//提交事务
		public static void commitTransaction(Connection conn) {
			try {
				if (conn != null) {
					if (!conn.getAutoCommit()) {
						conn.commit();
					}
				}
			}catch(SQLException e) {}
		}
		
		//回滚事务
		public static void rollbackTransaction(Connection conn) {
			try {
				if (conn != null) {
					if (!conn.getAutoCommit()) {
						conn.rollback();
					}
				}
			}catch(SQLException e) {}
		}
		
		//恢复conn的状态
		public static void resetConnection(Connection conn) {
			try {
				if (conn != null) {
					if (conn.getAutoCommit()) {
						conn.setAutoCommit(false);
					}else {
						conn.setAutoCommit(true);
					}
				}
			}catch(SQLException e) {}
		}
		
		public static void main(String[] src){
			System.out.println(DBUtil_OA.getConnection());
			//String sDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
//			String sDriverName = "com.mysql.jdbc.Driver";//SQL数据库引擎
//			try {
//				Class.forName(sDriverName);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//			
//			String yy="2012.00";
//			System.out.println(yy.substring(0,yy.indexOf(".")));
//				
//			String de="insert into excel_manage2_data(m_account,m_begintime,m_endtime,m_depart,m_item,m_column01,m_column02) values ('广东人民广播电台','20120201','20120229','珠江经济频道','-1.00工商广告','300.00','600.99')";
//			System.out.println(de.replace(".00", ""));
			
//			Connection conn=DBUtil.getConnection();
//			String spname="{call pr_param_zp_project(?,?,?)}";
//			try {
//				CallableStatement cstmt=conn.prepareCall(spname);
//				cstmt.setString(1, "NX5200002");
//				cstmt.setString(2, "1");
//				cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
//				cstmt.executeUpdate();
//				String str=cstmt.getString(3);
//				System.out.println(str);
//				cstmt.close();
//				DBUtil.closeConnection();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			Map<String, String> map1=new HashMap<String, String>();
//			map1.put("1", "11");
//			map1.put("2", "222");
//			map1.put("1","222");
////			System.out.println(map1.get("1"));
//			
//			Map<Integer, Integer> map2=new HashMap<Integer, Integer>();
//			map2.put(1, 1);
//			map2.put(2, 2);
//			map2.put(1, 11);
//			String aa=map2.get(11).toString();
//			System.out.println(aa);
			
		} 
	}