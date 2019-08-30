package com.example.tlyanepos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tlyanepos.TbContentCategoryExample.Criteria;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        //根据parentid查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TreeNode getCategoryList2(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample example2 = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo((long) 0);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        TreeNode root = new TreeNode(list.get(0).getId(),list.get(0).getParentId(),list.get(0).getName(),list.get(0).getStatus());

        System.out.println("--------------------------------------------");
        System.out.println("id:"+root.getId()+"pid:"+root.getPid()+"State:"+root.getStatus()+"Text:"+root.getName());
        System.out.println("--------------------------------------------");
        List<TbContentCategory> listall = contentCategoryMapper.selectByExample(example2);
        System.out.println("--------------------------------------------");
        System.out.println("总条数："+listall.size());
        System.out.println("--------------------------------------------");

        for(int i=1;i<listall.size();i++)
        {
            TreeNode node=new TreeNode(listall.get(i).getId(),listall.get(i).getParentId(),listall.get(i).getName(),listall.get(i).getStatus());

            root.add(node);
        }
        System.out.println(root);
//		Map map=new HashMap();
//		map.put("data", root.getChildren());
//		System.out.println("hahaha");
        return root;
    }

}

