package com.ccic.salesapp.performance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.ccic.salesapp.performance.domain.mapper.PrpdcompanyDao;
import com.ccic.salesapp.performance.domain.vo.dto.Prpdcompany;
import com.ccic.salesapp.performance.service.PrpdcompanyService;

@Service
public class PrpdcompanyServiceImpl implements PrpdcompanyService {

	
	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private PrpdcompanyDao prpdcompanyDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public Prpdcompany get(String comcode){
		return prpdcompanyDao.get(comcode);
	}
	
	@Override
	public List<Prpdcompany> list(Map<String, Object> map)
	{
		return prpdcompanyDao.list(map);

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
		return prpdcompanyDao.count(map);
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
	public int save(Prpdcompany prpdcompany){ return prpdcompanyDao.save(prpdcompany); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(Prpdcompany prpdcompany){
		return prpdcompanyDao.update(prpdcompany);
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
	public int remove(String comcode){
		return prpdcompanyDao.remove(comcode);
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
	public int batchRemove(String[] comcodes){
		return prpdcompanyDao.batchRemove(comcodes);
	}
	/*============通用batchRemove结束===========*/
	
}
