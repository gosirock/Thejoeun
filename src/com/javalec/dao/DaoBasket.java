package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.Dto;
import com.javalec.dto.DtoBasket;
import com.javalec.util.ShareVar;

public class DaoBasket {
	

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	
	String conname;
	String condata;
	String userid;
	String pid;
	int qty;
	
	public DaoBasket() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	// 구매시
	public DaoBasket(String userid, String pid, int qty) {
		super();
		this.userid = userid;
		this.pid = pid;
		this.qty = qty;
	}









	public DaoBasket(String conname, String condata) {
		super();
		this.conname = conname;
		this.condata = condata;
	}









	public ArrayList<DtoBasket> selectLinst(){
		ArrayList<DtoBasket> dtoList = new ArrayList<DtoBasket>();
		
		String whereDefault = "select p.pid, p.pbrand, p.pname, p.pprice, b.qty";
		String whereDefault1 = "from product p, user u, basket b";
		String whereDefault2 = "where u.userid = b.user_userid and p.pid = b.product_pid and u.userid = " + ShareVar.loginUserId;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+whereDefault1+whereDefault2);
			
			
			while(rs.next()) {
				String pid = rs.getString(1);
				String pbrand = rs.getString(2);
				String pname = rs.getString(3);
				int pprice = rs.getInt(4);
				int bqty =  rs.getInt(5);
				
				DtoBasket dtoBasket = new DtoBasket(pid, pbrand, pname, pprice, bqty);
				dtoList.add(dtoBasket);
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}
	
//	public ArrayList<DtoBasket> puacheaesList(){
//		ArrayList<DtoBasket> dtoList = new ArrayList<DtoBasket>();
//		
//		String whereDefault = "delete from product p, user u, basket b";
//		String whereDefault1 = "where u.userid = b.user_userid and p.pid = b.product_pid and u.userid = " + ShareVar.loginUserId;
//		
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
//			Statement stmt_mysql = conn_mysql.createStatement();
//			
//			ResultSet rs = stmt_mysql.executeQuery(whereDefault+whereDefault1);
//			
//			
//			while(rs.next()) {
//				String pid = rs.getString(1);
//				String pbrand = rs.getString(2);
//				String pname = rs.getString(3);
//				int pprice = rs.getInt(4);
//				int bqty =  rs.getInt(5);
//				
//				DtoBasket dtoBasket = new DtoBasket(pid, pbrand, pname, pprice, bqty);
//				dtoList.add(dtoBasket);
//			}
//			conn_mysql.close();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return dtoList;
//	}

	
	public ArrayList<Dto> buyLinst(){
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		
//		String whereDefault = "select p.pid, p.pbrand, p.pname, p.pprice, b.qty"; 
//		String whereDefault1 = "from product p, user u, basket b";
//		String whereDefault2 = "where u.userid = b.user_userid and p.pid = b.product_pid and u.userid = " + ShareVar.loginUserId;
		
		String whereDefault = "select * from basket where user_userid = " + ShareVar.loginUserId; 
		
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
				int qty = rs.getInt(5);
				
				Dto dto = new Dto(pid, pbrand, pname, pprice, qty);
				dtoList.add(dto);
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}
	
	
	// 구매시 
			public boolean purchasesAction() {
				PreparedStatement ps = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
					Statement stmt_mysql = conn_mysql.createStatement();
					
					
					String query = "update basket b, user u, product p";
					String query1 = "set b.qty = b.qty-1 and p.pstock = p.pstock-1"; 
					String query2 = "where u.userid = b.user_userid  AND p.pid = b.product_pid and u.userid = " + ShareVar.loginUserId; ;
					
					
					ps = conn_mysql.prepareStatement(query + query1 + query2);
					ps.setString(1, pid.trim());
					ps.setString(2, ShareVar.loginUserId);
					ps.setInt(3, qty);
					
					//Basket basket = new Basket(qty);
					
					ps.executeUpdate();
					conn_mysql.close();
					
				}catch(Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
	
	//public 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
