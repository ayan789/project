package com.ccic.salesapp.noncar.dto;

public class ImgBatchUploadResponseVo {
	
		private String imgType;
		private String imgTypeName;
		private String uploadNode;
		private String validFlag;
		private String uploadFile;
		private String fileOrgName;
		private String fileSize;
		private String shootModel;
		private String shootTime;
		private String gpsTag;
		private String showIdx;
		private String remark;
		private String maxWidth;
		private String extendFields;
		private String imgId;
		private String appendPath;
		private String fileName;
		private String bussNo;
		
		
		public String getBussNo() {
			return bussNo;
		}
		public void setBussNo(String bussNo) {
			this.bussNo = bussNo;
		}
		private String successDatas;
		public String getImgType() {
			return imgType;
		}
		public void setImgType(String imgType) {
			this.imgType = imgType;
		}
		public String getImgTypeName() {
			return imgTypeName;
		}
		public void setImgTypeName(String imgTypeName) {
			this.imgTypeName = imgTypeName;
		}
		public String getUploadNode() {
			return uploadNode;
		}
		public void setUploadNode(String uploadNode) {
			this.uploadNode = uploadNode;
		}
		public String getValidFlag() {
			return validFlag;
		}
		public void setValidFlag(String validFlag) {
			this.validFlag = validFlag;
		}
		public String getUploadFile() {
			return uploadFile;
		}
		public void setUploadFile(String uploadFile) {
			this.uploadFile = uploadFile;
		}
		public String getFileOrgName() {
			return fileOrgName;
		}
		public void setFileOrgName(String fileOrgName) {
			this.fileOrgName = fileOrgName;
		}
		public String getFileSize() {
			return fileSize;
		}
		public void setFileSize(String fileSize) {
			this.fileSize = fileSize;
		}
		public String getShootModel() {
			return shootModel;
		}
		public void setShootModel(String shootModel) {
			this.shootModel = shootModel;
		}
		public String getShootTime() {
			return shootTime;
		}
		public void setShootTime(String shootTime) {
			this.shootTime = shootTime;
		}
		public String getGpsTag() {
			return gpsTag;
		}
		public void setGpsTag(String gpsTag) {
			this.gpsTag = gpsTag;
		}
		public String getShowIdx() {
			return showIdx;
		}
		public void setShowIdx(String showIdx) {
			this.showIdx = showIdx;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getMaxWidth() {
			return maxWidth;
		}
		public void setMaxWidth(String maxWidth) {
			this.maxWidth = maxWidth;
		}
		public String getExtendFields() {
			return extendFields;
		}
		public void setExtendFields(String extendFields) {
			this.extendFields = extendFields;
		}
		public String getImgId() {
			return imgId;
		}
		public void setImgId(String imgId) {
			this.imgId = imgId;
		}
		public String getAppendPath() {
			return appendPath;
		}
		public void setAppendPath(String appendPath) {
			this.appendPath = appendPath;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		@Override
		public String toString() {
			return "ImgBatchUploadResponseVo [imgType=" + imgType + ", imgTypeName=" + imgTypeName + ", uploadNode="
					+ uploadNode + ", validFlag=" + validFlag + ", uploadFile=" + uploadFile + ", fileOrgName="
					+ fileOrgName + ", fileSize=" + fileSize + ", shootModel=" + shootModel + ", shootTime=" + shootTime
					+ ", gpsTag=" + gpsTag + ", showIdx=" + showIdx + ", remark=" + remark + ", maxWidth=" + maxWidth
					+ ", extendFields=" + extendFields + ", imgId=" + imgId + ", appendPath=" + appendPath
					+ ", fileName=" + fileName + "]";
		}
		public ImgBatchUploadResponseVo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ImgBatchUploadResponseVo(String imgType, String imgTypeName, String uploadNode, String validFlag,
				String uploadFile, String fileOrgName, String fileSize, String shootModel, String shootTime,
				String gpsTag, String showIdx, String remark, String maxWidth, String extendFields, String imgId,
				String appendPath, String fileName) {
			super();
			this.imgType = imgType;
			this.imgTypeName = imgTypeName;
			this.uploadNode = uploadNode;
			this.validFlag = validFlag;
			this.uploadFile = uploadFile;
			this.fileOrgName = fileOrgName;
			this.fileSize = fileSize;
			this.shootModel = shootModel;
			this.shootTime = shootTime;
			this.gpsTag = gpsTag;
			this.showIdx = showIdx;
			this.remark = remark;
			this.maxWidth = maxWidth;
			this.extendFields = extendFields;
			this.imgId = imgId;
			this.appendPath = appendPath;
			this.fileName = fileName;
		}
		
}
