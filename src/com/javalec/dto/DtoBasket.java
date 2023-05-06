package com.javalec.dto;

public class DtoBasket {

	String pid;
	String pbrand;
	String pname;
	int pprice;
	int bqty;
	
	
	public DtoBasket() {
		// TODO Auto-generated constructor stub
	}


	public DtoBasket(String pid, String pbrand, String pname, int ppricne, int bqty) {
		super();
		this.pid = pid;
		this.pbrand = pbrand;
		this.pname = pname;
		this.pprice = ppricne;
		this.bqty = bqty;
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


	public int getPpricne() {
		return pprice;
	}


	public void setPpricne(int ppricne) {
		this.pprice = ppricne;
	}


	public int getBqty() {
		return bqty;
	}


	public void setBqty(int bqty) {
		this.bqty = bqty;
	}







}
