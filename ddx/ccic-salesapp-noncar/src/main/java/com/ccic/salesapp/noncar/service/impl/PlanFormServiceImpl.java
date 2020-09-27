package com.ccic.salesapp.noncar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccic.salesapp.noncar.dto.TreeDO;
import com.ccic.salesapp.noncar.dto.TreeDO2;
import com.ccic.salesapp.noncar.dto.request.planelement.BuildTree;
import com.ccic.salesapp.noncar.dto.request.planelement.BuildTree2;
import com.ccic.salesapp.noncar.dto.request.planelement.Insurance;
import com.ccic.salesapp.noncar.dto.request.planelement.RateDO;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree2;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanFormMapper;
import com.ccic.salesapp.noncar.service.PlanFormService;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class PlanFormServiceImpl implements PlanFormService {
	
	@Autowired
	TPrdPlanFormMapper tPrdPlanFormMapper;

	@Override
	public Tree<RateDO> getTree(HashMap<String,Object> map) {
		List<Tree<RateDO>> trees = new ArrayList<Tree<RateDO>>();
		List<Insurance> insurances = tPrdPlanFormMapper.list2(map);
		for (Insurance insurance : insurances) {
			
			Tree<RateDO> tree = new Tree<RateDO>();
			tree.setId(insurance.getId());
			if(insurance.getPid()!=0)
			tree.setParentId(insurance.getPid());
			tree.setText(insurance.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<RateDO> t = BuildTree.build(trees);
		return t;
	}
	
	@Override
	public Tree<TreeDO> getTreeList() {
		List<Tree<TreeDO>> trees = new ArrayList<Tree<TreeDO>>();
		List<TreeDO> treeDOs = tPrdPlanFormMapper.listTreeDO();
		for (TreeDO treeDO : treeDOs) {
			Tree<TreeDO> tree = new Tree<TreeDO>();
			tree.setId(treeDO.getId());
			tree.setParentId(treeDO.getPid());
			tree.setText(treeDO.getName());
			trees.add(tree);
		}
		Tree<TreeDO> t = BuildTree.build(trees);
		return t;
	}
	
	@Override
	public Tree2<TreeDO2> OccupationTreeList() {
		List<Tree2<TreeDO2>> trees = new ArrayList<Tree2<TreeDO2>>();
		List<TreeDO2> treeDOs = tPrdPlanFormMapper.listTreeDO2();
		for (TreeDO2 treeDO : treeDOs) {
			Tree2<TreeDO2> tree = new Tree2<TreeDO2>();
			tree.setId(treeDO.getId());
			tree.setParentId(treeDO.getPid());
			tree.setText(treeDO.getName());
			tree.setGrage(treeDO.getGrage());
			trees.add(tree);
		}
		Tree2<TreeDO2> t = BuildTree2.build(trees);
		return t;
	}


}
