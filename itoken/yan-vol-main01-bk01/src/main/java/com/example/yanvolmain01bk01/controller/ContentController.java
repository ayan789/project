package com.example.yanvolmain01bk01.controller;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.yanvolmain01bk01.domain.Content;
import com.example.yanvolmain01bk01.service.ContentService;
import com.example.yanvolmain01bk01.common.utils.PageUtils;
import com.example.yanvolmain01bk01.common.utils.Query;
import com.example.yanvolmain01bk01.common.utils.R;

/**
 * <P></P>
 * 
 * @version 1.0
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-22 10:46:59
 */

@RestController
@RequestMapping("/yanvolmain01bk01/content")
public class ContentController {

	private static Logger logger = Logger.getLogger(ContentController.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private ContentService contentService;

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping()
	String Content(){
	    return "yanvolmain01bk01/content/content";
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
		List<Content> contentList = contentService.list(query);
		int total = contentService.count(query);
		PageUtils pageUtils = new PageUtils(contentList, total);
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
	    return "yanvolmain01bk01/content/add";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		Content content = contentService.get(id);
		model.addAttribute("content", content);
	    return "yanvolmain01bk01/content/edit";
	}

	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping("/save")
	public R save( Content content){
		if(contentService.save(content)>0){
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
	public R update( Content content){
		contentService.update(content);
		return R.ok();
	}

	/**
     * <P>删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/remove")
	public R remove( Long id){
		if(contentService.remove(id)>0){
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
	public R remove(@RequestParam("ids[]") Long[] ids){
		contentService.batchRemove(ids);
		return R.ok();
	}
	
}
