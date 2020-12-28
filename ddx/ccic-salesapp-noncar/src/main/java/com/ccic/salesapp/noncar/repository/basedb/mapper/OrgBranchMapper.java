package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.OrgBranch;

@Mapper
public interface OrgBranchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgBranch record);

    OrgBranch selectByPrimaryKey(Integer id);
    /**
     * 通过机构代码查找所属分公司级别机构代码
     * @param comCode
     * @return 分公司机构代码
     */
    String selectBranchByComCode(String comCode);

    int updateByPrimaryKey(OrgBranch record);
}