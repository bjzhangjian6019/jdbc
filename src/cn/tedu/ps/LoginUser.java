package cn.tedu.ps;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import cn.tedu.util.JdbcUtil;

/**
 * 模拟用户登录,连接数据库进行验证
 * @author zhangjian
 *
 */
public class LoginUser {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//1.提示用户登录
		System.out.println("请进行登录：");
		//2.提示输入用户名
		System.out.println("请输入用户名：");
		String username = sc.nextLine();
		//3.提示输入密码
		System.out.println("请输入密码：");
		String password = sc.nextLine();
		//4.根据用户名和密码查询数据库
		login(username, password);
		sc.close();
	}
	public static void login(String username,String password) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.注册驱动，获取数据库的连接
			conn = JdbcUtil.getConn();
			//2.获取传输器
			stat = conn.createStatement();
			//3.执行SQL语句
			String sql = "select * from user where username='"+
			username + "' and password = '" + password + "'";
			rs = stat.executeQuery(sql);
			//4.处理数据
			if (rs.next()) {
				System.out.println("登录成功。");
			}else {
				System.out.println("登录失败。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//5.释放资源
			JdbcUtil.close(conn, stat, rs);
		}
				
	}
}
