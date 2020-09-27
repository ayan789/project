package com.ccic.salesapp.noncar.repository.basedb.mapper;

 

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.noncar.dto.EnPolicyListQueryEntity;
import com.ccic.salesapp.noncar.dto.PrpcmainRenewal;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.utils.Page;

@Mapper
@Component
public interface PrpcmainRenewalDAO {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_PRPCMAIN_RENEWAL
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(String policyNo) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_PRPCMAIN_RENEWAL
	 * @mbggenerated
	 */
	void insert(PrpcmainRenewal record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_PRPCMAIN_RENEWAL
	 * @mbggenerated
	 */
	void insertSelective(PrpcmainRenewal record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_PRPCMAIN_RENEWAL
	 * @mbggenerated
	 */
	PrpcmainRenewal selectByPrimaryKey(String policyNo) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_PRPCMAIN_RENEWAL
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(PrpcmainRenewal record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_PRPCMAIN_RENEWAL
	 * @mbggenerated
	 */
	int updateByPrimaryKey(PrpcmainRenewal record) throws SQLException;
	
	Page selectByPolicyPerQueryPage(EnPolicyListQueryEntity enPolicyListQueryEntity ,PageVO pageVo);


}