package com.ccic.salesapp.performance.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ccic.salesapp.performance.domain.vo.dto.InviteUserVO;

import java.util.List;

@Mapper
public interface InviteUserMapper {

	List<InviteUserVO> selectByInviteUsercode(@Param("userCode") String userCode,@Param("offset") int offset,@Param("limit") int limit);
	
	int selectByInviteUsercodeNum(String userCode);

	InviteUserVO selectBycardId(String cardid);

	int updateByPrimaryKeySelective(InviteUserVO inviteUserVO);

	void insert(InviteUserVO inviteUserVO);

	InviteUserVO selectByPrimaryKey(InviteUserVO inviteUserVO);
}
