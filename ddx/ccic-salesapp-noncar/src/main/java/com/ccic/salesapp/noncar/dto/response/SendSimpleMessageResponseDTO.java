package com.ccic.salesapp.noncar.dto.response;

public class SendSimpleMessageResponseDTO {

	private ResponseHead responseHead;
	private ResponseBody responseBody;

	public static class ResponseHead {
		private String seqNo;
		private int status;
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

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
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
		private String messageId;

		public String getMessageId() {
			return messageId;
		}

		public void setMessageId(String messageId) {
			this.messageId = messageId;
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
