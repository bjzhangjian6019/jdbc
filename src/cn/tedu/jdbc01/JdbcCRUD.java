package cn.tedu.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import cn.tedu.util.JdbcUtil;

/**
 * Jdbc实现增删改查
 * C：create创建
 * R：retrieve 读取
 * U: update 修改
 * D：delete 删除
 * 
 * @author zhangjian
 *
 */
public class JdbcCRUD {

	/**增加一条数据：zhangsan,5000*/
	@Test
	public void add() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接
			conn = DriverManager.getConnection("jdbc:mysql:///jt_db?characterEncoding=utf-8", "root", "root");
			//3.获取传输器
			stat = conn.createStatement();
			//4.执行SQL
			int rows = stat.executeUpdate("insert into account " + 
			"values(null,'zhangsan',5000)");
			//5.处理数据
			System.out.println("影响的行数：" + rows);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//6.释放资源
			JdbcUtil.close(conn, stat, rs);
		}
	}
	
	
	/**查询：查询account表中id为2的记录*/
	@Test
	public void query() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			//注册驱动并获取连接
			conn = JdbcUtil.getConn();
			//获取传输器
			stat = conn.createStatement();
			//执行sql语句，返回执行的结果
			String sql = "select * from " + "account where id = 2";
			rs = stat.executeQuery(sql);
			//处理结果
			if (rs.next()) {//true
				System.out.println(
						rs.getInt("id")+" , " +
						rs.getString("name")+" , "+
						rs.getDouble("money")
					);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stat, rs);
		}
	}
	
	/**修改account表中name为 "zhangsan" 的记录，将金额改为15000*/
	@Test
	public void update() {
		
	}
	
	/**删除：删除account表中name为 "zhangsan" 的记录*/
	@Test
	public void delete() {
		
		
	}
}
