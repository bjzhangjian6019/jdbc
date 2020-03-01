package cn.tedu.jdbc01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JDBCDemo {

	public static void main(String[] args) throws Exception {
		
		//1.注册数据库的驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取数据库的连接
		String url = "jdbc:mysql://localhost:3306/jt_db?characterEncoding=utf-8";
		String user = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, user, password);
		//3.获取传输器
		Statement st = conn.createStatement();
		//4.执行SQL语句
		String sql = "select * from account";
		ResultSet rs = st.executeQuery(sql);
		//5.处理数据
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			double money = rs.getDouble("money");
			System.out.println(id+", "+name+", "+money);
		}
		//6.释放资源
		rs.close();
		st.close();
		conn.close();
		System.out.println("运行结束！");
	}
	
}






