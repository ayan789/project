package com.example.mybatis.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.mybatis.domain.SalesResponsibility;
import com.example.mybatis.service.SalesResponsibilityService;

/**
 * <P></P>
 * 
 * @version 1.0
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 15:45:42
 */

@RestController
@RequestMapping("/mybatis/salesResponsibility")
public class SalesResponsibilityController {

	private static Logger logger = Logger.getLogger(SalesResponsibilityController.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SalesResponsibilityService salesResponsibilityService;

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping()
	String SalesResponsibility(){
	    return "mybatis/salesResponsibility/salesResponsibility";
	}

	@GetMapping("/get")
	public R get() {
		return R.ok().data(salesResponsibilityService.get("vvvvff"));
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
		List<SalesResponsibility> salesResponsibilityList = salesResponsibilityService.list(query);
		System.out.println(JSONObject.toJSONString(salesResponsibilityList));
		int total = salesResponsibilityService.count(query);
		PageUtils pageUtils = new PageUtils(salesResponsibilityList, total);
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
	    return "mybatis/salesResponsibility/add";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		SalesResponsibility salesResponsibility = salesResponsibilityService.get(id);
		model.addAttribute("salesResponsibility", salesResponsibility);
	    return "mybatis/salesResponsibility/edit";
	}

	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping("/save")
	public R save( SalesResponsibility salesResponsibility){
		if(salesResponsibilityService.save(salesResponsibility)>0){
			return R.ok().data(null);
		}
		return R.error().data(null);
	}

	/**
     * <P>修改</P>
     * @param
     * @return
     * @throws Exception
     */
	@RequestMapping("/update")
	public R update( SalesResponsibility salesResponsibility){
		salesResponsibilityService.update(salesResponsibility);
		return R.ok().data(null);
	}

	/**
     * <P>删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/remove")
	public R remove( String id){
		if(salesResponsibilityService.remove(id)>0){
		return R.ok().data(null);
		}
		return R.error().data(null);
	}

	/**
     * <P>批量删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		salesResponsibilityService.batchRemove(ids);
		return R.ok().data(null);
	}
	
}
