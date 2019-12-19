package com.example.yanvolmain01bk01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.yanvolmain01bk01.domain.Item;
import com.example.yanvolmain01bk01.service.ItemService;
import com.example.yanvolmain01bk01.common.utils.PageUtils;
import com.example.yanvolmain01bk01.common.utils.Query;
import com.example.yanvolmain01bk01.common.utils.R;

/**
 * <P></P>
 * 商品表
 * @version 1.0
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-21 16:30:11
 */

@RestController
@RequestMapping("/yanvolmain01bk01/item")
public class ItemController {

	private static Logger logger = Logger.getLogger(ItemController.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private ItemService itemService;

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping()
	String Item(){
	    return "yanvolmain01bk01/item/item";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/list")
	public R list(@RequestParam("offset") Integer offset , @RequestParam("limit") Integer limit ){
		Map<String, Object> params = new HashMap<>();
		params.put("offset",offset);
		params.put("limit",limit);
		logger.info("入参:{}");
		//查询列表数据
        Query query = new Query(params);
		List<Item> itemList = itemService.list(query);
		int total = itemService.count(query);
		PageUtils pageUtils = new PageUtils(itemList, total);
		logger.info("出参:{}");
		return R.ok().data(pageUtils);
	}

	@GetMapping("/list2")
	public R list(Query query){
		logger.info("入参:{}");
		//查询列表数据
		List<Item> itemList = itemService.list(query);
		int total = itemService.count(query);
		PageUtils pageUtils = new PageUtils(itemList, total);
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
	    return "yanvolmain01bk01/item/add";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		Item item = itemService.get(id);
		model.addAttribute("item", item);
	    return "yanvolmain01bk01/item/edit";
	}

	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping("/save")
	public R save( Item item){
		if(itemService.save(item)>0){
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
	public R update( Item item){
		itemService.update(item);
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
		if(itemService.remove(id)>0){
		return R.ok().data("OK");
		}
		return R.error().data("ERROR");
	}

	/**
     * <P>批量删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		itemService.batchRemove(ids);
		return R.ok();
	}
	
}
