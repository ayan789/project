package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;



@Data
public class PolicyTemple {


	//PolicyLobList
	@JSONField(name="PolicyLobList")
	private List<PolicyLobList> policyLobList;

}