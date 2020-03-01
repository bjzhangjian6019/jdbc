package cn.tedu.util;
/**
 * 工具类的使用：单独封装出来，方便使用。
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** JDBC工具类 */
public class JdbcUtil {
	/**
	 * 获取数据库连接对象并返回
	 * @return Connection对象
	 * @throws Exception 
	 */
	public static Connection getConn() throws Exception{
		//1.注册驱动
		Class.forName( "com.mysql.jdbc.Driver" );
		//2.获取连接
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql:///jt_db?characterEncoding=utf-8", 
				"root", 
				"root");
		return conn;
	}
	
	/**
	 * 释放JDBC程序中的资源
	 * @param conn 连接对象
	 * @param stat 传输器对象
	 * @param rs 结果集对象
	 */
	public static void close(Connection conn, 
			Statement stat, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				stat = null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
}









