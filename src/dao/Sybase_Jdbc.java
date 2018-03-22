package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Sybase_Jdbc {
	
	public Connection getConntion(){
		Connection conn = null;
		
		try {
			//加载驱动
			Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
			//获得数据库连接
			conn = DriverManager.getConnection("jdbc:sybase:Tds:10.221.32.124:4100/ecis?charset=cp936", "ecis", "ecis!23$");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(ResultSet rs,PreparedStatement pre,Connection conn){
			try {
				if(rs!=null)
					rs.close();
				if(pre!=null)
					pre.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
