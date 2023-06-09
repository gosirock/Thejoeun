package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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

	
	
	
	public DaoBasket() {
		// TODO Auto-generated constructor stub
	}


	public ArrayList<DtoBasket> selectLinst(){
		ArrayList<DtoBasket> dtoList = new ArrayList<DtoBasket>();
		
		String whereDefault = "select p.pid, p.pbrand, p.pname, p.pprice, b.qty";
		String whereDefault1 = " from product p, user u, basket b";
		String whereDefault2 = " where u.userid = b.user_userid and p.pid = b.product_pid and u.userid = " + "'"+ ShareVar.loginUserId+"'";
		
		
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
				int bqty = rs.getInt(5);
				
				DtoBasket dtoBasket = new DtoBasket(pid, pbrand, pname, pprice, bqty);
				dtoList.add(dtoBasket);
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}

	
	
		
		
		
		
	}
	

