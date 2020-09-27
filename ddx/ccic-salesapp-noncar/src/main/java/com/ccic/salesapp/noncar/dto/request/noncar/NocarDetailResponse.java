package com.ccic.salesapp.noncar.dto.request.noncar;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

public class NocarDetailResponse extends ResponseBody {
		private NocarDetailResponseBody   policyElementString;

		public NocarDetailResponseBody getPolicyElementString() {
			return policyElementString;
		}

		public void setPolicyElementString(NocarDetailResponseBody policyElementString) {
			this.policyElementString = policyElementString;
		}
		
}
