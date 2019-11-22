package com.example.yanvolmain01bk01.controller;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.yanvolmain01bk01.domain.Mapurl;
import com.example.yanvolmain01bk01.service.MapurlService;
import com.example.yanvolmain01bk01.common.utils.PageUtils;
import com.example.yanvolmain01bk01.common.utils.Query;
import com.example.yanvolmain01bk01.common.utils.R;

/**
 * <P></P>
 * 
 * @version 1.0
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-22 10:55:18
 */

@RestController
@RequestMapping("/yanvolmain01bk01/mapurl")
public class MapurlController {

	private static Logger logger = Logger.getLogger(MapurlController.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private MapurlService mapurlService;

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping()
	String Mapurl(){
	    return "yanvolmain01bk01/mapurl/mapurl";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		logger.info("入参:{}");
		//查询列表数据
        Query query = new Query(params);
		List<Mapurl> mapurlList = mapurlService.list(query);
		int total = mapurlService.count(query);
		PageUtils pageUtils = new PageUtils(mapurlList, total);
		logger.info("出参:{}");
		return R.ok().data(pageUtils);
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/add")
	String add(){
	    return "yanvolmain01bk01/mapurl/add";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/edit/{mapurlid}")
	String edit(@PathVariable("mapurlid") String mapurlid,Model model){
		Mapurl mapurl = mapurlService.get(mapurlid);
		model.addAttribute("mapurl", mapurl);
	    return "yanvolmain01bk01/mapurl/edit";
	}

	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping("/save")
	public R save( Mapurl mapurl){
		if(mapurlService.save(mapurl)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@RequestMapping("/update")
	public R update( Mapurl mapurl){
		mapurlService.update(mapurl);
		return R.ok();
	}

	/**
     * <P>删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/remove")
	public R remove( String mapurlid){
		if(mapurlService.remove(mapurlid)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
     * <P>批量删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/batchRemove")
	public R remove(@RequestParam("ids[]") String[] mapurlids){
		mapurlService.batchRemove(mapurlids);
		return R.ok();
	}
	
}
