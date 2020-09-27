package com.ccic.salesapp.performance.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.performance.domain.mapper.PerformanceEmpAsmtInfoDao;
import com.ccic.salesapp.performance.domain.vo.dto.PerformanceEmpAsmtInfo;
import com.ccic.salesapp.performance.domain.vo.response.CheckRankResponse;
import com.ccic.salesapp.performance.service.PerformanceEmpAsmtInfoService;

@Service
public class PerformanceEmpAsmtInfoServiceImpl implements PerformanceEmpAsmtInfoService {

	private static Logger logger = Logger.getLogger(PerformanceEmpAsmtInfoServiceImpl.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private PerformanceEmpAsmtInfoDao performanceEmpAsmtInfoDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public PerformanceEmpAsmtInfo get(Long id){
		return performanceEmpAsmtInfoDao.get(id);
	}
	
	@Override
	public List<PerformanceEmpAsmtInfo> list(Map<String, Object> map)
	{
		logger.info("入参:{}");
		return performanceEmpAsmtInfoDao.list(map);

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
		return performanceEmpAsmtInfoDao.count(map);
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
	public int save(PerformanceEmpAsmtInfo performanceEmpAsmtInfo){ return performanceEmpAsmtInfoDao.save(performanceEmpAsmtInfo); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(PerformanceEmpAsmtInfo performanceEmpAsmtInfo){
		return performanceEmpAsmtInfoDao.update(performanceEmpAsmtInfo);
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
	public int remove(Long id){
		return performanceEmpAsmtInfoDao.remove(id);
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
	public int batchRemove(Long[] ids){
		return performanceEmpAsmtInfoDao.batchRemove(ids);
	}
	/*============通用batchRemove结束===========*/
	
	
	@Override
	public CheckRankResponse  selectRankCheck(String userCode) {
		return performanceEmpAsmtInfoDao.selectRankCheck(userCode);
	}
}
