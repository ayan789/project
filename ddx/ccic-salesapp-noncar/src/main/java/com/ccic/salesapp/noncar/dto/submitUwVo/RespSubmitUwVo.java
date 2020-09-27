package com.ccic.salesapp.noncar.dto.submitUwVo;

import java.util.List;

public class RespSubmitUwVo {

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
			private String orderId;
			public String getOrderId() {
				return orderId;
			}
			public void setOrderId(String orderId) {
				this.orderId = orderId;
			}
			private String isIssueAfterPay;
			public String getIsIssueAfterPay() {
				return isIssueAfterPay;
			}
			public void setIsIssueAfterPay(String isIssueAfterPay) {
				this.isIssueAfterPay = isIssueAfterPay;
			}
			private ChannelOpInfoDTO channelOpInfoDTO;
			public ChannelOpInfoDTO getChannelOpInfoDTO() {
				return channelOpInfoDTO;
			}
			public void setChannelOpInfoDTO(ChannelOpInfoDTO channelOpInfoDTO) {
				this.channelOpInfoDTO = channelOpInfoDTO;
			}
			public List<SubmitProposalInfoList> getSubmitProposalInfoList() {
				return submitProposalInfoList;
			}
			public void setSubmitProposalInfoList(List<SubmitProposalInfoList> submitProposalInfoList) {
				this.submitProposalInfoList = submitProposalInfoList;
			}
			private List<SubmitProposalInfoList> submitProposalInfoList;
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
