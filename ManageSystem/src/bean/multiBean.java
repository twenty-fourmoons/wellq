package bean;

import java.util.Date;

public class multiBean {
	private String orderID;
	private Date inoutDate;
	private String no;
	private String remarks;
	private String matterid;
	private int inoutCount;
	private String state;
	
	
	public String getOrderID() {
		return orderID;
	}
	public String setOrderID(String orderID) {
		return this.orderID = orderID;
	}
	public Date getInoutDate() {
		return inoutDate;
	}
	public Date setInoutDate(Date inoutDate) {
		return this.inoutDate = inoutDate;
	}
	public String getNo() {
		return no;
	}
	public String setNo(String no) {
		return this.no = no;
	}
	public String getRemarks() {
		return remarks;
	}
	public String setRemarks(String remarks) {
		return this.remarks = remarks;
	}
	public String getMatterid() {
		return matterid;
	}
	public String setMatterid(String matterid) {
		return this.matterid = matterid;
	}
	public int getInoutCount() {
		return inoutCount;
	}
	public int setInoutCount(int inoutCount) {
		return this.inoutCount = inoutCount;
	}
	public String getState() {
		return state;
	}
	public String setState(String state) {
		return this.state = state;
	}

	
}
