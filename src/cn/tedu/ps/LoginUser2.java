package cn.tedu.ps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


import cn.tedu.util.JdbcUtil;

/**
 * 模拟用户登录,连接数据库进行验证
 * 防止SQL注入攻击：
 * 使用PreparedStatement对象代替原来的Statement
 * @author zhangjian
 *
 */
public class LoginUser2 {

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
//		Statement stat = null;//
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.注册驱动，获取数据库的连接
			conn = JdbcUtil.getConn();
			
			String sql = "select * from user where username = ? and password = ?";
			//2.获取传输器
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);//设置参数1
			ps.setString(2, password);//设置参数2
			rs = ps.executeQuery();//执行查询
			//4.处理数据
			if (rs.next()) {
				System.out.println("登录成功。");
			}else {
				System.out.println("登录失败。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//5.释放资源
			JdbcUtil.close(conn, ps, rs);
		}
				
	}
}
