package databean;

public class HateBean {
	private int hateId;
	private int potholeId;
	private int userId;
	public int getHateId() {
		return hateId;
	}
	public void setHateId(int hateId) {
		this.hateId = hateId;
	}
	public int getPotholeId() {
		return potholeId;
	}
	public void setPotholeId(int potholeId) {
		this.potholeId = potholeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int personId) {
		this.userId = personId;
	}
	
	public HateBean(){}
	public HateBean(int i){hateId = i;}
}
