package com.ccic.salesapp.performance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.performance.domain.mapper.SMessageDao;
import com.ccic.salesapp.performance.domain.vo.dto.SMessage;
import com.ccic.salesapp.performance.service.SMessageService;
import com.ccic.salesapp.performance.domain.vo.request.MessageVo;

@Service
public class SMessageServiceImpl implements SMessageService {

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SMessageDao sMessageDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public SMessage get(BigDecimal id){
		return sMessageDao.get(id);
	}
	
	@Override
	public List<SMessage> list(Map<String, Object> map)
	{
		return sMessageDao.list(map);

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
		return sMessageDao.count(map);
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
	public int save(SMessage sMessage){ return sMessageDao.save(sMessage); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(SMessage sMessage){
		return sMessageDao.update(sMessage);
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
		return sMessageDao.remove(id);
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
		return sMessageDao.batchRemove(ids);
	}
	/*============通用batchRemove结束===========*/

	@Override
	public List<Map> getMessageType(MessageVo messageVo) {
		return sMessageDao.getMessageType(messageVo);
	}
	
	@Override
	public List<Map> getMessageList(MessageVo messageVo) {
		return sMessageDao.getMessageList(messageVo);
	}
	
	
}
