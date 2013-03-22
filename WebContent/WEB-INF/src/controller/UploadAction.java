package controller;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import model.Model;
import model.PotholeDAO;

import org.apache.commons.io.FileUtils;
import org.mybeans.dao.DAOException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.PersonBean;
import databean.PotholeBean;

public class UploadAction extends ActionSupport{
	private File upload;//The actual file
    private String uploadContentType; //The content type of the file
    private String uploadFileName; //The uploaded file name
    private String longitude;
    private String latitude;
    private String depth;
    private String desc;
	private boolean uploadButton = false;
	private static PotholeDAO potholeDAO;
	
	public void setDAO(Model model){
		potholeDAO = model.getPotholeDAO();
	}
	
	public void setUploadButton(boolean b) {uploadButton = true;}
	public boolean getUploadButton(){return uploadButton;}

	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String size) {
		this.depth = size;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	private boolean isValidDesc(String s){
		if(s == null || s.equals("") || s.matches(".*[<>\"].*") || s.length() > 50) return false;
		return true;
	}
	
	public void validate(){
		
		
		if(uploadButton){
			if(upload == null) addFieldError("upload", "Upload an image");
			if(!isValidDesc(desc)) addFieldError("desc", "Invalid description");
			if(depth == null || depth.equals("")) addFieldError("depth", "Enter the depth in inches");
			try{
				Double.parseDouble(depth);
			}
			catch(NumberFormatException e){
				addFieldError("depth", "Enter the depth using only numerical digits");
			}
		}

	}

	public String execute() throws Exception{
		if(ActionContext.getContext().getSession().get("currUser") == null){
			addActionError("You need to log in");
			ActionContext.getContext().getSession().put("redirect", "upload");
			return "login";
		}
		if(uploadButton){
			Date d = new Date();
			PersonBean currUser = (PersonBean) ActionContext.getContext().getSession().get("currUser");
			// upload file
			String fullFileName = "c:/tomcat/webapps/FinalProject/photo/" + uploadFileName + "_" + currUser.getUserId() + "_" + d.getTime();
			
			try {
				if(!uploadContentType.split("/")[0].equals("image")){
					addFieldError("upload", "Wrong file type");
					return INPUT;
				}
				File theFile = new File(fullFileName);
				FileUtils.copyFile(upload, theFile);
			} catch (Exception e) {
				addActionError(e.getMessage());
				return INPUT;
			}
			
			// amend database
			PotholeBean potholeBean = new PotholeBean(0);
			potholeBean.setDescription(desc);
			potholeBean.setHateCount(0);
			potholeBean.setImagePath("photo/" + uploadFileName + "_" + currUser.getUserId() + "_" + d.getTime());
			potholeBean.setLatitude(latitude);
			potholeBean.setLongitude(longitude);
			potholeBean.setDepth(depth);
			try{
				potholeDAO.create(potholeBean);
			}
			catch(DAOException e){
				addActionError("Error in database");
				return INPUT;
			}
			
			// reset stuff
			addActionError("Pothole uploaded");
			desc=null;
			depth=null;
		}
		return SUCCESS;
	}
}
