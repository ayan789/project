package com.ccic.salesapp.noncar.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.TreeDO;
import com.ccic.salesapp.noncar.dto.TreeDO2;
import com.ccic.salesapp.noncar.dto.request.planelement.RateDO;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree2;
 

@Service
public interface PlanFormService {
	Tree<RateDO> getTree(HashMap<String,Object> map);
	
	Tree<TreeDO> getTreeList();
	
	Tree2<TreeDO2> OccupationTreeList();
}
