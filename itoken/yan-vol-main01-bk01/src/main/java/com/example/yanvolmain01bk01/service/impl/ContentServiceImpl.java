package com.example.yanvolmain01bk01.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.example.yanvolmain01bk01.dao.ContentDao;
import com.example.yanvolmain01bk01.domain.Content;
import com.example.yanvolmain01bk01.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	private static Logger logger = Logger.getLogger(ContentServiceImpl.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private ContentDao contentDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public Content get(Long id){
		return contentDao.get(id);
	}
	
	@Override
	public List<Content> list(Map<String, Object> map)
	{
		logger.info("入参:{}");
		return contentDao.list(map);

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
		return contentDao.count(map);
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
	public int save(Content content){ return contentDao.save(content); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(Content content){
		return contentDao.update(content);
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
		return contentDao.remove(id);
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
		return contentDao.batchRemove(ids);
	}
	/*============通用batchRemove结束===========*/
	
}
