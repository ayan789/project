package com.example.yanvolmain01bk01.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.example.yanvolmain01bk01.dao.MapurlDao;
import com.example.yanvolmain01bk01.domain.Mapurl;
import com.example.yanvolmain01bk01.service.MapurlService;

@Service
public class MapurlServiceImpl implements MapurlService {

	private static Logger logger = Logger.getLogger(MapurlServiceImpl.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private MapurlDao mapurlDao;

	/*============通用get开始===========*/
	/**
     * <P>获取</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public Mapurl get(String mapurlid){
		return mapurlDao.get(mapurlid);
	}
	
	@Override
	public List<Mapurl> list(Map<String, Object> map)
	{
		logger.info("入参:{}");
		return mapurlDao.list(map);

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
		return mapurlDao.count(map);
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
	public int save(Mapurl mapurl){ return mapurlDao.save(mapurl); }
	/*============通用save结束===========*/

	/*============通用update开始===========*/
	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public int update(Mapurl mapurl){
		return mapurlDao.update(mapurl);
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
	public int remove(String mapurlid){
		return mapurlDao.remove(mapurlid);
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
	public int batchRemove(String[] mapurlids){
		return mapurlDao.batchRemove(mapurlids);
	}
	/*============通用batchRemove结束===========*/
	
}
