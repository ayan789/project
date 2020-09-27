package com.ccic.salesapp.performance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.ccic.salesapp.performance.domain.mapper.SSalelawPersondetailDao;
import com.ccic.salesapp.performance.domain.vo.dto.SSalelawPersondetail;
import com.ccic.salesapp.performance.service.SSalelawPersondetailService;

@Service
public class SSalelawPersondetailServiceImpl implements SSalelawPersondetailService {

	
	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SSalelawPersondetailDao sSalelawPersondetailDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public SSalelawPersondetail get(String usercode){
		return sSalelawPersondetailDao.get(usercode);
	}
	
	@Override
	public List<SSalelawPersondetail> list(Map<String, Object> map)
	{
		
		return sSalelawPersondetailDao.list(map);

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
		return sSalelawPersondetailDao.count(map);
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
	public int save(SSalelawPersondetail sSalelawPersondetail){ return sSalelawPersondetailDao.save(sSalelawPersondetail); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(SSalelawPersondetail sSalelawPersondetail){
		return sSalelawPersondetailDao.update(sSalelawPersondetail);
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
	public int remove(String usercode){
		return sSalelawPersondetailDao.remove(usercode);
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
	public int batchRemove(String[] usercodes){
		return sSalelawPersondetailDao.batchRemove(usercodes);
	}
	/*============通用batchRemove结束===========*/
	
}
