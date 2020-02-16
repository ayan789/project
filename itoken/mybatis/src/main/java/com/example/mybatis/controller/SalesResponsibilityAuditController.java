package com.example.mybatis.controller;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.mybatis.domain.SalesResponsibilityAudit;
import com.example.mybatis.service.SalesResponsibilityAuditService;



/**
 * <P></P>
 * 
 * @version 1.0
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 14:20:32
 */

@RestController
@RequestMapping("/mybatis/salesResponsibilityAudit")
public class SalesResponsibilityAuditController {

	private static Logger logger = Logger.getLogger(SalesResponsibilityAuditController.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SalesResponsibilityAuditService salesResponsibilityAuditService;

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping()
	String SalesResponsibilityAudit(){
	    return "mybatis/salesResponsibilityAudit/salesResponsibilityAudit";
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
		List<SalesResponsibilityAudit> salesResponsibilityAuditList = salesResponsibilityAuditService.list(query);
		int total = salesResponsibilityAuditService.count(query);
		PageUtils pageUtils = new PageUtils(salesResponsibilityAuditList, total);
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
	    return "mybatis/salesResponsibilityAudit/add";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		SalesResponsibilityAudit salesResponsibilityAudit = salesResponsibilityAuditService.get(id);
		model.addAttribute("salesResponsibilityAudit", salesResponsibilityAudit);
	    return "mybatis/salesResponsibilityAudit/edit";
	}

	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping("/save")
	public R save( SalesResponsibilityAudit salesResponsibilityAudit){
		if(salesResponsibilityAuditService.save(salesResponsibilityAudit)>0){
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
	public String update( SalesResponsibilityAudit salesResponsibilityAudit){
		salesResponsibilityAuditService.update(salesResponsibilityAudit);
		return ResultCodeEnum.SUCCESS.getMessage();
	}

	/**
     * <P>删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/remove")
	public String remove( String id){
		if(salesResponsibilityAuditService.remove(id)>0){
		return ResultCodeEnum.SUCCESS.getMessage();
		}
		return ResultCodeEnum.UNKNOWN_REASON.getMessage();
	}

	/**
     * <P>批量删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/batchRemove")
	public String remove(@RequestParam("ids[]") String[] ids){
		salesResponsibilityAuditService.batchRemove(ids);
		return ResultCodeEnum.SUCCESS.getMessage();
	}
	
}
