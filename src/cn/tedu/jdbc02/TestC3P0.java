package cn.tedu.jdbc02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 使用C3P0连接池
 * @author zhangjian
 *
 */

public class TestC3P0 {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		//创建一个连接池
		ComboPooledDataSource pool = new ComboPooledDataSource();
		try {
			//0.配置连接数据库的基本信息
			//pool.setDriverClass("com.mysql.jdbc.Driver");
			//pool.setJdbcUrl("jdbc:mysql:///jt_db?characterEncoding=utf-8");
			//pool.setUser("root");
			//pool.setPassword("root");
			//从连接池中获取一个连接池对象
			conn = pool.getConnection();
			//2.获取传输器
			stat = conn.createStatement();
			
			//3.执行SQL并返回执行结果
			rs = stat.executeQuery(
				"select * from account where id=1"
			);
			//4.处理结果
			if(rs.next()){
				String name = rs.getString("name");
				double money = rs.getDouble("money");
				System.out.println(name+":"+money);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* 如果通过连接池获取连接，最后需要将
			 *	连接还回连接池中 */
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					rs = null;
				}
			}
			if(stat!=null){
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					stat = null;
				}
			}
			if(conn!=null){
				try {
					/* 如果当前的conn是自己创建的
					 * 连接对象，调用close方法是
					 * 关闭连接。
					 * 如果当前的conn是从连接池中
					 * 获取的连接对象，调用close方
					 * 法是将连接还回池中！
					 */
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					conn = null;
				}
			}
		}
		
	
	}
	
}
