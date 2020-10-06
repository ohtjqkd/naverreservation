package kr.or.connect.naverreservation.dto;

import java.util.Arrays;
import java.util.List;

public class Comment {
	private Integer commentId;
	private String description;
	private String comment;
	private List<CommentImage> commentImages;
	private String createDate;
	private String modifyDate;
	private Integer productId;
	private String reservationDate;
	private String reservationEmail;
	private Integer reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private double score;
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", description=" + description + ", comment=" + comment
				+ ", commentImages=" + commentImages + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", productId=" + productId + ", reservationDate=" + reservationDate + ", reservationEmail="
				+ reservationEmail + ", reservationInfoId=" + reservationInfoId + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTelephone + ", score=" + score + "]";
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<CommentImage> getCommentImages() {
		return commentImages;
	}
	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		StringBuffer sb = new StringBuffer();
//		int idx = reservationEmail.indexOf("@");
		int idx = reservationEmail.length();
		for(int i = 0; i < idx; i++) {
			if(i<4) {
				sb.append(reservationEmail.charAt(i));
				continue;
			}
			sb.append('*');
		}
		this.reservationEmail = sb.toString();
	}
	public Integer getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = Double.parseDouble(String.format("%.1f",score));;
	}

}
