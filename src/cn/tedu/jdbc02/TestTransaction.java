package cn.tedu.jdbc02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.tedu.util.JdbcUtil;

/**
 * 使用C3P0连接池
 * @author zhangjian
 *
 */

public class TestTransaction {
	
	public static void main(String[] args) throws SQLException {
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
			
			//设置取消自动提交事务（改为手动提交）
			conn.setAutoCommit(false);
			
			//2.获取传输器
			stat = conn.createStatement();
			
			//3.执行SQL并返回执行结果
			/* ***** A给B转账100元 ***** */
			//4.A账户减去100元
			String sql = "update acc set money=money-100 where name='A'";
			stat.executeUpdate(sql);
			
			//int i = 1/0; // 让程序抛出异常，中断转账操作
			
			//5.B账户加上100元
			sql = "update acc set money=money+100 where name='B'";
			stat.executeUpdate(sql);
			
			//6.手动提交事务
			conn.commit();
			System.out.println("转账成功！提交事务...");
		} catch (Exception e) {
			e.printStackTrace();
			//一旦其中一个操作出错都将回滚，使两个操作都不成功 
			conn.rollback();    
			System.out.println("执行失败！回滚事务...");
		} finally {
			JdbcUtil.close(conn, stat, rs);
		}
	}
}
