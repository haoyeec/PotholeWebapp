package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PersonBean {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String zipCode;
	
	public void setUserId(int i) {userId = i;}
	public void setUserName(String s) {userName = s.trim();}
	public void setPassword(String s) {password = s;}
	public void setClearPassword(String s) {password = hash(s.trim());}
	public void setEmail (String s) {email = s.trim();}
	public void setZipCode (String i) {zipCode = i;}
	
	public int getUserId(){return userId;}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	public String getEmail() {return email;}
	public String getZipCode(){return zipCode;}
	
	public PersonBean(){}
	public PersonBean(int i){userId = i;}
	
	public boolean checkPassword(String p){
		return password.equals(hash(p));
	}
	
	public String hash(String p){
		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}
		
		md.update(userName.getBytes());
		md.update(p.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
		
	}
}
