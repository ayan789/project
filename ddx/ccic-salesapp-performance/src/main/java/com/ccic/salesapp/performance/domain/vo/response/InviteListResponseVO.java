package com.ccic.salesapp.performance.domain.vo.response;

import java.util.List;

import com.ccic.salesapp.performance.domain.vo.dto.DataVO;
import com.ccic.salesapp.performance.domain.vo.request.PageVO;

public class InviteListResponseVO extends DataVO {

	List<InviteDetailResponseVO> list;
	
	PageVO page;

	public List<InviteDetailResponseVO> getList() {
		return list;
	}

	public void setList(List<InviteDetailResponseVO> list) {
		this.list = list;
	}

	public PageVO getPage() {
		return page;
	}

	public void setPage(PageVO page) {
		this.page = page;
	}

	
}