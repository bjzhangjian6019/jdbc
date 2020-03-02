package cn.tedu.homework2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import cn.tedu.util.JdbcUtil;


/**
 * 学生信息管理系统
 * 运行程序,根据选项,可以对学生信息进行增删改查操作。
 */
public class StudentManager {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			//1.提示选择操作选项
			System.out.print("a：查询学生信息\t");
			System.out.print("b：添加学生信息\t");
			System.out.print("c：修改学生信息\t");
			System.out.println("d：删除学生信息");
			System.out.println("请输入操作，abcd任选一项：");
			String opt = sc.nextLine();
			//2.根据选项执行不同操作
			if( "a".equalsIgnoreCase( opt ) ){
				findAll(); //查询学生信息
			}else if( "b".equalsIgnoreCase( opt ) ){
				addStu(); //添加学生信息
			}else if( "c".equalsIgnoreCase( opt ) ){
				updateStu(); //修改学生信息
			}else if( "d".equalsIgnoreCase( opt ) ){
				deleteStu(); //删除学生信息
			}else{
				System.out.println("输入错误，请重新输入！");
			}
		}
	}
	
	/** a. 查询所有学生信息 */
	private static void findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			String sql = "select * from stu";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String stuid = rs.getString("stuid");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String addr = rs.getString("addr");
				double score = rs.getDouble("score");
				System.out.println( stuid+"，"+name+"，"+gender
						+"，"+addr+"，"+score );
			}
			System.out.println("------------------------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** b. 添加学生信息 */
	private static void addStu() {
		System.out.println("请输入要添加的学生编号：");
		String stuid = sc.nextLine();
		System.out.println("请输入姓名：");
		String name = sc.nextLine();
		System.out.println("请输入性别：");
		String gender = sc.nextLine();
		System.out.println("请输入地址：");
		String addr = sc.nextLine();
		System.out.println("请输入成绩：");
		double score = Double.parseDouble(sc.nextLine());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			String sql = "insert into stu values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stuid);
			ps.setString(2, name);
			ps.setString(3, gender);
			ps.setString(4, addr);
			ps.setDouble(5, score);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("添加成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** c. 根据id修改学生信息 */
	private static void updateStu() {
		System.out.println("请输入要修改的学生编号：");
		String stuid = sc.nextLine();
		System.out.println("请输入要修改的姓名：");
		String name = sc.nextLine();
		System.out.println("请输入要修改的性别：");
		String gender = sc.nextLine();
		System.out.println("请输入要修改的所在地址：");
		String addr = sc.nextLine();
		System.out.println("请输入要修改的成绩：");
		double score = Double.parseDouble(sc.nextLine());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			String sql = "update stu set name=?,gender=?"
					+ ",addr=?,score=? where stuid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, addr);
			ps.setDouble(4, score);
			ps.setString(5, stuid);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("修改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** d. 根据id删除学生信息 */
	private static void deleteStu() {
		System.out.println("请输入要删除的学生编号：");
		String stuid = sc.nextLine();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			String sql = "delete from stu where stuid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stuid);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




