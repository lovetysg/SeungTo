package src.com.javalec.ex.dto;

import java.sql.Timestamp;

public class BreDto {

	int rId;
	String bId;
	String rContent;
	Timestamp rDate;
	
	public BreDto( String bId, String rContent,Timestamp rDate ) {
		// TODO Auto-generated constructor stub
		this.bId = bId;
		this.rContent = rContent;
		this.rDate = rDate;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public Timestamp getrDate() {
		return rDate;
	}

	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
	
}
