package kr.or.connect.naverreservation.dto;

public class DisplayInfoImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer displayInfoId;
	private Integer displayInfoImageId;
	private Integer fileInfoId;
	private String fileName;
	private String modifyDate;
	private String saveFileName;
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public Integer getDisplayInfoImageId() {
		return displayInfoImageId;
	}
	public void setDisplayInfoImageId(Integer displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}
	public Integer getFileInfoId() {
		return fileInfoId;
	}
	public void setFileInfoId(Integer fileInfoId) {
		this.fileInfoId = fileInfoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	@Override
	public String toString() {
		return "DisplayInfoImage [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag="
				+ deleteFlag + ", displayInfoId=" + displayInfoId + ", displayInfoImageId=" + displayInfoImageId
				+ ", fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", modifyDate=" + modifyDate
				+ ", saveFileName=" + saveFileName + "]";
	}
	
}
