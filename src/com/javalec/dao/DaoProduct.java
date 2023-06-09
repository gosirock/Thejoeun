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
import com.javalec.dto.DtoBasket;
import com.javalec.util.ShareVar;

public class DaoProduct {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	
	
	
	
	String pid;
	String pbrand;
	String pname;
	int pprice;
	String pimagename;
	FileInputStream file;
	String qty;
	
	
	public DaoProduct() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	// 테이블 클릭
	
		public DaoProduct(String pid) {
		super();
		this.pid = pid;
	}





		public DaoProduct(String pid, String qty) {
		super();
		this.pid = pid;
		this.qty = qty;
	}






		// 테이블 클릭
		public Dto tableClick() {
			Dto dto = null;
			String query = "select pid, pbrand, pname, pprice, pimagename, pimage from product where pid = '" + pid + "'";
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query);

			while(rs.next()) {
				String wkpid = rs.getString(1);
				String wkpbrand = rs.getString(2);
				String wkpname = rs.getString(3);
				int wkpprice = rs.getInt(4);
				String wkpimagename = rs.getString(5);
				
				File file = new File("./" + wkpimagename);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(6);
				
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
					
				}
				dto = new Dto(wkpid, wkpbrand, wkpname, wkpprice, wkpimagename);
			}
			conn_mysql.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
		}

		// 장바구니 담기
		public boolean basketAction() {
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				String query0 = "select pstock >= " + qty + " from product where pid = '" + pid + "'";
				ResultSet rs = stmt_mysql.executeQuery(query0);
				int rss = 0;
				while(rs.next()) {
					rss = rs.getInt(1);	
				}
				
				
				if(rss == 1) {
					String query = "insert into basket(product_pid, user_userid, qty, insertdate)";
					String query1 = " values (?, ?, ?, now())";
					String query2 = " on duplicate key update qty = qty+?, insertdate = now()";
					
					ps = conn_mysql.prepareStatement(query + query1 + query2);
					ps.setString(1, pid.trim());
					ps.setString(2, ShareVar.loginUserId);
					ps.setInt(3, Integer.parseInt(qty));
					ps.setInt(4, Integer.parseInt(qty));
					
					ps.executeUpdate();
					conn_mysql.close();	
					
					return true;
				}else {
					return false;
					
					
				}
				
				

			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		// 장바구니 비우기
		public void basketEmptyAction() {
			
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				String query = "delete from basket";
				
				ps = conn_mysql.prepareStatement(query);
				ps.executeUpdate();
				conn_mysql.close();

		}catch(Exception e) {
			e.printStackTrace();

		}
		}

		public void selectDelete() {
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				String query = "delete from basket where product_pid = '" + pid + "'";
				
				ps = conn_mysql.prepareStatement(query);
				ps.executeUpdate();
				conn_mysql.close();
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		}
		
		
		public void buyAction() {
			ArrayList<DtoBasket> dtobasket = new ArrayList<DtoBasket>();
			
			String whereDefault = "select product_pid, qty from basket";
			PreparedStatement ps = null;
			
			try {
				int listCount = 0;
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				ResultSet rs = stmt_mysql.executeQuery(whereDefault);
				
				while(rs.next()) {
					String pid = rs.getString(1);
					int tqy = rs.getInt(2);
					
				DtoBasket dto = new DtoBasket(tqy, pid);
				dtobasket.add(dto);
				listCount = dtobasket.size();
				
				}
				for(int i = 0; i < listCount; i++) {
					String query0 = "update product set pstock = pstock -" + dtobasket.get(i).getBqty() + " where pid = '" + dtobasket.get(i).getBpid() + "'";  
				
					ps = conn_mysql.prepareStatement(query0);
					ps.executeUpdate();
				}				
				
				
				String query = "insert into purchase select * from basket";
				String query1 = "delete from basket where user_userid = '" + ShareVar.loginUserId + "'";
				
				
				ps = conn_mysql.prepareStatement(query);
				ps = conn_mysql.prepareStatement(query1);
				ps.executeUpdate();
				conn_mysql.close();

		}catch(Exception e) {
			e.printStackTrace();

		}

		}
		
		
}
