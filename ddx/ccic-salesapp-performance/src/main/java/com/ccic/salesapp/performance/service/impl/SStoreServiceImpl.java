package com.ccic.salesapp.performance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.performance.domain.mapper.SStoreDao;
import com.ccic.salesapp.performance.domain.vo.dto.SStore;
import com.ccic.salesapp.performance.service.SStoreService;

@Service
public class SStoreServiceImpl implements SStoreService {


	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SStoreDao sStoreDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public SStore get(BigDecimal id){
		return sStoreDao.get(id);
	}
	
	@Override
	public List<SStore> list(Map<String, Object> map)
	{

		return sStoreDao.list(map);

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
		return sStoreDao.count(map);
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
	public int save(SStore sStore){ return sStoreDao.save(sStore); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(SStore sStore){
		return sStoreDao.update(sStore);
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
	public int remove(BigDecimal id){
		return sStoreDao.remove(id);
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
	public int batchRemove(BigDecimal[] ids){
		return sStoreDao.batchRemove(ids);
	}
	/*============通用batchRemove结束===========*/
	
}
