package com.example.mybatis.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.example.mybatis.dao.SalesResponsibilityAuditDao;
import com.example.mybatis.domain.SalesResponsibilityAudit;
import com.example.mybatis.service.SalesResponsibilityAuditService;

@Service
public class SalesResponsibilityAuditServiceImpl implements SalesResponsibilityAuditService {

	private static Logger logger = Logger.getLogger(SalesResponsibilityAuditServiceImpl.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SalesResponsibilityAuditDao salesResponsibilityAuditDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public SalesResponsibilityAudit get(String id){
		return salesResponsibilityAuditDao.get(id);
	}
	
	@Override
	public List<SalesResponsibilityAudit> list(Map<String, Object> map)
	{
		logger.info("入参:{}");
		return salesResponsibilityAuditDao.list(map);

	}
	/*============通用get结束===========*/

	/*============通用count开始===========*/
	/**
     * <P>数量</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int count(Map<String, Object> map){
		return salesResponsibilityAuditDao.count(map);
	}
	/*============通用count结束===========*/

	/*============通用save开始===========*/
	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int save(SalesResponsibilityAudit salesResponsibilityAudit){ return salesResponsibilityAuditDao.save(salesResponsibilityAudit); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(SalesResponsibilityAudit salesResponsibilityAudit){
		return salesResponsibilityAuditDao.update(salesResponsibilityAudit);
	}
	/*============通用update结束===========*/

	/*============通用remove开始===========*/
	/**
     * <P>删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int remove(String id){
		return salesResponsibilityAuditDao.remove(id);
	}
	/*============通用remove结束===========*/

	/*============通用batchRemove开始===========*/
	/**
     * <P>批量删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int batchRemove(String[] ids){
		return salesResponsibilityAuditDao.batchRemove(ids);
	}
	/*============通用batchRemove结束===========*/
	
}
