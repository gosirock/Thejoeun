package com.javalec.dto;

public class Dto {

	
	String uid;
	String upssword;
	
	String pid;
	String pbrand;
	String pname;
	int pprice;
	int pstock;
	String pimagename;
	int bqty;
	
	
	public String getPimagename() {
		return pimagename;
	}

	public void setPimagename(String pimagename) {
		this.pimagename = pimagename;
	}

	public Dto() {
	}

	// 로그인
	public Dto(String uid, String upssword) {
		super();
		this.uid = uid;
		this.upssword = upssword;
	}

	
	
	
	// buy 테이블클릭 이미지 
	public Dto(String pid, String pbrand, String pname, int pprice, String pimagename) {
		super();
		this.pid = pid;
		this.pbrand = pbrand;
		this.pname = pname;
		this.pprice = pprice;
		this.pimagename = pimagename;
	}

	// buy 초기테이블
	public Dto(String pid, String pbrand, String pname, int pprice, int pstock) {
		super();
		this.pid = pid;
		this.pbrand = pbrand;
		this.pname = pname;
		this.pprice = pprice;
		this.pstock = pstock;
	}

	// Method
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpssword() {
		return upssword;
	}

	public void setUpssword(String upssword) {
		this.upssword = upssword;
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

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getPstock() {
		return pstock;
	}

	public void setPstock(int pstock) {
		this.pstock = pstock;
	}

	public int getBqty() {
		return bqty;
	}
	public void setgetBqty(int bqty) {
		this.bqty = bqty;
	}
	
	
	
	
	
}
