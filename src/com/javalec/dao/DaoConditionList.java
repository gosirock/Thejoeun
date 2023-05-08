package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.Dto;
import com.javalec.util.ShareVar;

public class DaoConditionList {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	
	
	String conname;
	String condata;
	
	public DaoConditionList() {
		// TODO Auto-generated constructor stub
	}

	public DaoConditionList(String conname, String condata) {
		super();
		this.conname = conname;
		this.condata = condata;
	}
	
	public ArrayList<Dto> conditionList(){
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		
		String whereDefault = "select pid, pbrand, pname, pprice, pstock from product";
		String whereDefault1 = " where " + conname + " like '%" + condata + "%'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1);
			
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