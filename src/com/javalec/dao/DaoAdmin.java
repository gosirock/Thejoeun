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



import javax.swing.JOptionPane;

import com.javalec.dto.DtoAdmin;
import com.javalec.util.ShareVar;

public class DaoAdmin {
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
		
		
		
		String conname; // 검색에서 사용할 변수
		String condata;
		
		// File
		FileInputStream file;
		

		
		public DaoAdmin() {
			// TODO Auto-generated constructor stub
		}


		public DaoAdmin(String pid) {
			super();
			this.pid = pid;
		}
		


		public String getPid() {
			return pid;
		}


		public void setPid(String pid) {
			this.pid = pid;
		}


		public DaoAdmin(String pid, String pbrand, String pname, int pstock, int pprice, FileInputStream file) {  //insertAction 해주기 생성자
			
			super();
			this.pid = pid;
			this.pbrand = pbrand;
			this.pname = pname;
			this.pstock = pstock;
			this.pprice = pprice;
			this.file = file;
		}
		
		
		
		
		
		
		
		
		
		
		// ----------------f
		
		
		public DtoAdmin tableClick() {
			DtoAdmin dto = null;
			
			String whereDefault = "select pid, pbrand, pname, pstock, pprice, pimagename, pimage from product";    // select from 은 이렇게하기
			String whereDefault1 = " where pid = '" + pid + "'";
			try {  // java가 db에 접근했다.
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1);
				
				while(rs.next()) { // rs.next는 다음이있으면 1 . 없으면 0;
					String wkPid = rs.getString(1);
					String wkPbrand = rs.getString(2);
					String wkPname = rs.getString(3);
					int wkPstock = rs.getInt(4);
					int wkPprice = rs.getInt(5);
					String wkPimagename = rs.getString(6);
					
					// File 불러오기
					File file = new File("./" + wkPimagename);
					FileOutputStream output = new FileOutputStream(file);   // fileoutputstream은 file만드는 클래스
					InputStream input = rs.getBinaryStream(7);  // db에서 image를 가져오는 것
					byte[] buffer = new byte[1024];  // 1024는 한번에 불러오는 파일의 크기 버퍼가 바이트배열로 만들어지는데 그림의 일부분(정해준 크기)만큼씩 블록으로 생성하여 배열로 들어옴
					while(input.read(buffer)>0) {
						output.write(buffer);
					}
					
					
					// 위에 6개를 한번에 넣기  -> Dto 에서 1개의 데이터 생성자를 만들어놓음
					dto = new DtoAdmin(wkPid, wkPbrand, wkPname, wkPstock, wkPprice, wkPimagename);
					
					
				}
				
				conn_mysql.close();
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			return dto;
		}
		
		
		
		
		
		
		
		
		public ArrayList<DtoAdmin> selectList(){
			ArrayList<DtoAdmin> dtoList = new ArrayList<DtoAdmin>(); 
				String whereDefault = "select pid, pbrand, pname, pstock, pprice from product";    // select from 은 이렇게하기
				try {  // java가 db에 접근했다.
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
					Statement stmt_mysql = conn_mysql.createStatement();

					ResultSet rs = stmt_mysql.executeQuery(whereDefault);
					
					while(rs.next()) { // rs.next는 다음이있으면 1 . 없으면 0;
						String wkID = rs.getString(1);
						String wkBrand = rs.getString(2);
						String wkName = rs.getString(3);
						int wkStock = rs.getInt(4);
						int wkPrice = rs.getInt(5);
						
						// 위에 5개를 한번에 넣기  -> Dto 에서 4개의 데이터 생성자를 만들어놓음
						DtoAdmin dto = new DtoAdmin(wkID, wkBrand, wkName, wkStock, wkPrice);
						dtoList.add(dto);
						
					}
					
					conn_mysql.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				return dtoList;
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		public boolean insertAction() {   
			PreparedStatement ps = null ;
			try {  // java가 db에 접근했다.
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				String query = "insert into product (pid,pbrand,pname,pstock,pprice)";
				String query1 = " values (?,?,?,?,?)";
				
				ps = conn_mysql.prepareStatement(query + query1);
				ps.setString(1, pid.trim());
				ps.setString(2, pbrand.trim());
				ps.setString(3, pname.trim());
				ps.setInt(4, pstock);
				ps.setInt(5, pprice);
				// File 추가
				//ps.setBinaryStream(6, pimage);
				
				
				ps.executeUpdate();
				conn_mysql.close();
				
				JOptionPane.showMessageDialog(null, pname + "님의 정보가 입력 되었습니다.");
				
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
			
		}

		
		
		
		
		
		
		
		
		
		
		
		
		public boolean updateAction() {
			PreparedStatement ps = null ;
			try {  // java가 db에 접근했다.
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				String query = "update product set  pbrand = ?, pname = ?, pstock = ?, pprice = ?";
				String query1 = " where pid = ?";
				
				ps = conn_mysql.prepareStatement(query + query1);
				ps.setString(1, pbrand.trim());   // 물음표 2번
				ps.setString(2, pname.trim());
				ps.setInt(3, pstock);
				ps.setInt(4, pprice);
				ps.setString(5, pid);   // 물음표 6번
				
				
				
				ps.executeUpdate();
				conn_mysql.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public boolean deleteAction() {
			PreparedStatement ps = null ;
			try {  // java가 db에 접근했다.
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				String query = "delete from shoesshop.product where pid = ?";
				
				ps = conn_mysql.prepareStatement(query);
				ps.setString(1,pid);
				
				
				
				ps.executeUpdate();
				conn_mysql.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}

}