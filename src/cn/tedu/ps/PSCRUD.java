package cn.tedu.ps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.tedu.util.JdbcUtil;

/**
 * 使用PreparedStatement进行增删改查。
 * @author zhangjian
 *
 */
public class PSCRUD {

	@Test
	public void testFindAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//获取连接
			conn = JdbcUtil.getConn();
			//声明sql，获取传输器
			String sql = "select * from user";
			ps = conn.prepareStatement(sql);
			//执行sql语句返回执行结果
			rs = ps.executeQuery();
			//遍历结果
			while(rs.next()) {
				int id = rs.getInt("id");
				String user = rs.getString("username");
				String password = rs.getString("password");
				System.out.println("id:" + id+",user:" + user + ",password:" + password);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		
	}
	
	/**修改id为2的用户的密码为123456*/
	@Test
	public void testUpdat() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//获取连接
			conn = JdbcUtil.getConn();
			//声明sql，获取传输器的对象
			String sql = "update user set password=? where id = ?";
			ps = conn.prepareStatement(sql);
			//设置参数
			ps.setString(1, "123456");
			ps.setInt(2, 2);
			//执行sql语句，返回执行的结果
			int rows = ps.executeUpdate();
			System.out.println("影响的行数："+rows);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		
	}
	
	
}
