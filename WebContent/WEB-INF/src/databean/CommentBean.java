package databean;

public class CommentBean {
	private int commentId;
	private int potholeId;
	private int userId;
	private String comment;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public CommentBean(){}
	public CommentBean(int i){commentId = i;}

}
