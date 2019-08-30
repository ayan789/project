package com.example.tlyanepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TprogressServiceImpl implements TprogressService {

    @Autowired
    private TprogressDao tprogressDao;

    @Override
    public TprogressTreeNode getTree(String parentId) {
        //执行查询
        List<Tprogress> list = tprogressDao.getByProgressOne();
        TprogressTreeNode root = new TprogressTreeNode(list.get(0).getProgressId(),list.get(0).getParentId(),list.get(0).getStepKey(),list.get(0).getStatus(),list.get(0).getUrl());
        System.out.println("--------------------------------------------");
        System.out.println("id:"+root.getProgressId()+"pid:"+root.getParentId()+"State:"+root.getStatus()+"Text:"+root.getStepKey());
        System.out.println("--------------------------------------------");
        List<Tprogress> listall = tprogressDao.getByProgressMap();
        System.out.println("--------------------------------------------");
        System.out.println("总条数："+listall.size());
        System.out.println("--------------------------------------------");
        for(int i=1;i<listall.size();i++)
        {
            TprogressTreeNode node=new TprogressTreeNode(listall.get(i).getProgressId(),listall.get(i).getParentId(),listall.get(i).getStepKey(),listall.get(i).getStatus(),listall.get(i).getUrl());

            root.add(node);
        }
        System.out.println(root);
//		Map map=new HashMap();
//		map.put("data", root.getChildren());
//		System.out.println("hahaha");
        return root;
    }
}
