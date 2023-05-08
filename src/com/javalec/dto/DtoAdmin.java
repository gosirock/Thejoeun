package com.javalec.dto;

public class DtoAdmin {

	String pid;
	String pbrand;
	String pname;
	int pstock;
	int pprice;
	String pimagename;
	
	public DtoAdmin() {
		// TODO Auto-generated constructor stub
	}

	public DtoAdmin(String pid, String pbrand, String pname, int pstock, int pprice) {  // 5개를 테이블에 넣어주기위한 생성
		super();
		this.pid = pid;
		this.pbrand = pbrand;
		this.pname = pname;
		this.pstock = pstock;
		this.pprice = pprice;
	}
	
	

	public DtoAdmin(String pid, String pbrand, String pname, int pstock, int pprice, String pimagename) {
		super();
		this.pid = pid;
		this.pbrand = pbrand;
		this.pname = pname;
		this.pstock = pstock;
		this.pprice = pprice;
		this.pimagename = pimagename;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPbrand() {
		return pbrand;
	}

	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPstock() {
		return pstock;
	}

	public void setPstock(int pstock) {
		this.pstock = pstock;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getPimagename() {
		return pimagename;
	}

	public void setPimagename(String pimagename) {
		this.pimagename = pimagename;
	}
	
	
	
	
}