package com.javalec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.Dto;
import com.javalec.util.ShareVar;

public class Dao {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	String uid;
	String upassword;
	String uname;
	String uphone;
	String uemail;
	String uaddress;
	
	String pid;
	String pbrand;
	String pname;
	int pprice;
	String pimagename;
	FileInputStream file;
	
	
	
	public Dao() {
		// TODO Auto-generated constructor stub
	}


	public Dao(String uid, String upassword) {
		super();
		this.uid = uid;
		this.upassword = upassword;
	}
	
	// 중복체크
	public Dao(String uid) {
		super();
		this.uid = uid;
	}


	public Dao(String uid, String upassword, String uname, String uphone, String uemail, String uaddress) {
		super();
		this.uid = uid;
		this.upassword = upassword;
		this.uname = uname;
		this.uphone = uphone;
		this.uemail = uemail;
		this.uaddress = uaddress;
	}


	// method
	
	
	public boolean logincheck() {
		boolean check = false;
		String query = "select count(*) from user where userid = '" + uid + "' and upassword = '" + upassword + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query);
			
			rs.next();
			int countNum = rs.getInt(1); 
			
			if (countNum == 1) {
				check = true;
			}
			
			conn_mysql.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			return check;
	}
	
	
	//회원가입
	public boolean joinAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "insert into user (userid, uname, upassword, uphone, uemail, uaddress, uinsertdate)";
			String query1 = " values (?, ?, ?, ?, ?, ?, now())";
			
			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, uid.trim());
			ps.setString(2, uname.trim());
			ps.setString(3, upassword.trim());
			ps.setString(4, uphone.trim());
			ps.setString(5, uemail.trim());
			ps.setString(6, uaddress.trim());
			
			ps.executeUpdate();
			conn_mysql.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// 중복체크
	public boolean dupCheck() {
		boolean check = false;
		String query = "select count(*) from user where userid = '" + uid + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query);
			
			rs.next();
			int countNum = rs.getInt(1); 
			
			if (countNum == 1) {
				check = true;
			}
			
			conn_mysql.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			return check;
		
	}
	
	// buy초기 검색결과 Table에 셋팅하기
	public ArrayList<Dto> selectLinst(){
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		
		String whereDefault = "select pid, pbrand, pname, pprice, pstock from product";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			
			while(rs.next()) {
				String pid = rs.getString(1);
				String pbrand = rs.getString(2);
				String pname = rs.getString(3);
				int pprice = rs.getInt(4);
				int pstock = rs.getInt(5);
				
				Dto dto = new Dto(pid, pbrand, pname, pprice, pstock);
				dtoList.add(dto);
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}
	
	
	
	}
