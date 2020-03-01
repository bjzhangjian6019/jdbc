package cn.tedu.jdbc01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JDBCDemo2 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//1.注册数据库的驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据库的连接
			String url = "jdbc:mysql://localhost:3306/jt_db?characterEncoding=utf-8";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
			//3.获取传输器
			st = conn.createStatement();
			//4.执行SQL语句
			String sql = "select * from account";
			rs = st.executeQuery(sql);
			//5.处理数据
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double money = rs.getDouble("money");
				System.out.println(id+", "+name+", "+money);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//6.释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					st = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
			
		}
		System.out.println("运行结束！");
	}
	
}






