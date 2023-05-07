package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.javalec.util.ShareVar;

public class DaoAdminUpdate {
	
	
	// dB 연결하기 static 된 shareVar 로 연결하기
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	String pid;
	String pbrand;
	String pname;
	int pstock;
	int pprice;
	String pimagename;

	public DaoAdminUpdate() {
		// TODO Auto-generated constructor stub
	}

	public DaoAdminUpdate(String pid, String pbrand, String pname, int pstock, int pprice, String pimagename) {
		super();
		this.pid = pid;
		this.pbrand = pbrand;
		this.pname = pname;
		this.pstock = pstock;
		this.pprice = pprice;
		this.pimagename = pimagename;
	}
	
	
	public boolean updateAction() {
		PreparedStatement ps = null ;
		try {  // java가 db에 접근했다.
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "update product set pid = ?, pbrand = ?, pname = ?, pstock = ?, pprice = ?";
			String query1 = " where pid = ?";
			
			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, pid.trim());   // 물음표 1번
			ps.setString(2, pbrand.trim());   // 물음표 2번
			ps.setString(3, pname.trim());
			ps.setInt(4, pstock);
			ps.setInt(5, pprice);
			ps.setString(6, pid);   // 물음표 6번
			
			
			
			ps.executeUpdate();
			conn_mysql.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	
	

}
