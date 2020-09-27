package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.Applicant;

@Mapper
public interface ApplicantDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_APPLICANT
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(BigDecimal id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_APPLICANT
	 * @mbggenerated
	 */
	void insert(Applicant record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_APPLICANT
	 * @mbggenerated
	 */
	void insertSelective(Applicant record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_APPLICANT
	 * @mbggenerated
	 */
	Applicant selectByPrimaryKey(BigDecimal id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_APPLICANT
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Applicant record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table B2BBUSI.T_APPLICANT
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Applicant record) throws SQLException;
}