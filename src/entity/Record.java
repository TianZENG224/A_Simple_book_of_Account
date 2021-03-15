package entity;

import java.util.Date;

public class Record {
	private int spend;
	private int id;
	private int cid;
	private String comment;
	private Date date;
	
	public void setSpend(int spend) {
		this.spend = spend;
	}
	public int getSpend() {
		return spend;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCid() {
		return cid;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
}
