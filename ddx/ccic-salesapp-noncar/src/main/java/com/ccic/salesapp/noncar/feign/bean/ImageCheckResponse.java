package com.ccic.salesapp.noncar.feign.bean;

import java.util.ArrayList;

public class ImageCheckResponse {
	
	private ResponseHead responseHead;
	private ResponseBody responseBody;

	public static class ResponseHead {
		private String seqNo;
		private String status;
		private String esbCode;
		private String esbMessage;
		private String appCode;
		private String appMessage;

		public String getSeqNo() {
			return seqNo;
		}

		public void setSeqNo(String seqNo) {
			this.seqNo = seqNo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getEsbCode() {
			return esbCode;
		}

		public void setEsbCode(String esbCode) {
			this.esbCode = esbCode;
		}

		public String getEsbMessage() {
			return esbMessage;
		}

		public void setEsbMessage(String esbMessage) {
			this.esbMessage = esbMessage;
		}

		public String getAppCode() {
			return appCode;
		}

		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}

		public String getAppMessage() {
			return appMessage;
		}

		public void setAppMessage(String appMessage) {
			this.appMessage = appMessage;
		}
	}

	public static class ResponseBody {
		private String size;
		private ArrayList<ImageNodes> imageNodes;

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public ArrayList<ImageNodes> getImageNodes() {
			return imageNodes;
		}

		public void setImageNodes(ArrayList<ImageNodes> imageNodes) {
			this.imageNodes = imageNodes;
		}
	}

	public static class ImageNodes {
		private String nodeId;
		private String nodeName;
		private String imageCount;

		public String getNodeId() {
			return nodeId;
		}

		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		public String getNodeName() {
			return nodeName;
		}

		public void setNodeName(String nodeName) {
			this.nodeName = nodeName;
		}

		public String getImageCount() {
			return imageCount;
		}

		public void setImageCount(String imageCount) {
			this.imageCount = imageCount;
		}
	}

	public ResponseHead getResponseHead() {
		return responseHead;
	}

	public void setResponseHead(ResponseHead responseHead) {
		this.responseHead = responseHead;
	}

	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}

}
