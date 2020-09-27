package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.FormBase;
import com.ccic.salesapp.noncar.dto.Pagination;
import com.ccic.salesapp.noncar.dto.request.VOBase;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
@Mapper
public interface DAOBase {

	public List toList() throws Exception;
	public List toList(FormBase form) throws Exception;
	
	//public List toList(Object param) throws Exception;
	
	public List toList(String statementID,Object param) throws Exception;
	//public List toList( String statementID, Object object) throws Exception;
	public List toList(Pagination pagination, String statementID, Object object) throws Exception;
	public List toList(Pagination pagination,FormBase form) throws Exception;
	//@Deprecated
	//public List toList(Pagination pagination,Object param) throws Exception;
	
	public void doInsert( VOBase vo) throws Exception; //新增,默认新增，在实现类中定义
	//public void doInsert(String statementID, Object obj) throws Exception; //新增
	//@Deprecated
	public void doInsert( String statementID,Object obj) throws Exception; //新增,默认新增，在实现类中定义
	
	public int doDelete(String pk) throws Exception; //主键删除
	public int doDelete(String[] pks) throws Exception; //批量删除
	//public int doDelete(String statementID, String[] pks) throws Exception; //批量删除
	//public int doDelete(String statementID, String pk) throws Exception; //主键删除
	public int doDelete(VOBase vo) throws Exception;
	
	//@Deprecated
	public int doDelete(String statementID,String pk) throws Exception; //主键删除
	//@Deprecated
	public int doDelete(String statementID,String[] pks) throws Exception; //主键删除
	//@Deprecated
	public int doDelete(String statementID,Object param) throws Exception;
	//public int doDelete(String statementID,Object object) throws Exception;
	
	//public Object toUpdate(FormBase form) throws Exception;
	//public Object toUpdate(String statementID, Object obj) throws Exception; //修改
	
	public int doUpdate(VOBase vo) throws Exception; //修改
	//public int doUpdate(String statementID, Object obj) throws Exception; //修改
	//@Deprecated
	public int doUpdate(String statementID,Object param) throws Exception; //修改
	
	//public Object toView(String statementID, Object object) throws Exception; //查询
	public Object toView(FormBase form) throws Exception; //查询
	//@Deprecated
	public Object toView(String statementID,Object param) throws Exception; //查询
	
	//public int getTotalPageCount(String statementID, Object obj) throws Exception; //分页
	public int getTotalCount(FormBase form) throws Exception; //分页
	//public IbatisSqlMapTemplate getIbatisSqlMapTemplate();
	public void startTransaction() throws Exception; //开始事务
	public void commitTransation() throws Exception;  //提交终止事务
	public void endTransaction() throws Exception;  //中止事务
	//public SqlMapClient getSqlMapClient() throws Exception;
	public BigDecimal getSeq(String seqName) throws Exception;
	
}
 
