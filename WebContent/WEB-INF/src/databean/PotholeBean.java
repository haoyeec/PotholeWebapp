package databean;

public class PotholeBean {
	private int potholeId;
	private String imagePath;
	private String description;
	private String depth;
	private int hateCount;
	private String longitude;
	private String latitude;
	
	public void setPotholeId(int id){potholeId = id;}
	public void setImagePath(String s){imagePath = s.trim();}
	public void setDescription (String s) {description = s.trim();}
	public void setDepth (String s) {depth = s.trim();}
	public void setHateCount (int h) {hateCount = h;}
	public void setLongitude(String l){longitude = l.trim();}
	public void setLatitude (String l) {latitude = l.trim();}
	
	public int getPotholeId(){return potholeId;}
	public String getImagePath(){return imagePath;}
	public String getDescription(){return description;}
	public String getDepth(){return depth;}
	public int getHateCount() {return hateCount;}
	public String getLongitude(){return longitude;}
	public String getLatitude() {return latitude;}
	
	public PotholeBean() {}
	public PotholeBean(int i) {potholeId = i;}
	
	
}
