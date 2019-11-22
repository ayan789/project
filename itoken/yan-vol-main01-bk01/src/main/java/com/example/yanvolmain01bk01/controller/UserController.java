package com.example.yanvolmain01bk01.controller;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.yanvolmain01bk01.domain.User;
import com.example.yanvolmain01bk01.service.UserService;
import com.example.yanvolmain01bk01.common.utils.PageUtils;
import com.example.yanvolmain01bk01.common.utils.Query;
import com.example.yanvolmain01bk01.common.utils.R;

/**
 * <P></P>
 * 
 * @version 1.0
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-21 16:11:23
 */

@RestController
@RequestMapping("/yanvolmain01bk01/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private UserService userService;

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping()
	String User(){
	    return "yanvolmain01bk01/user/user";
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
		List<User> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
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
	    return "yanvolmain01bk01/user/add";
	}

	/**
     * <P></P>
     * @param
     * @return
     * @throws Exception
     */
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		User user = userService.get(id);
		model.addAttribute("user", user);
	    return "yanvolmain01bk01/user/edit";
	}

	/**
     * <P>保存</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping("/save")
	public R save( User user){
		if(userService.save(user)>0){
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
	public R update( User user){
		userService.update(user);
		return R.ok();
	}

	/**
     * <P>删除</P>
     * @param
     * @return
     * @throws Exception
     */
	@PostMapping( "/remove")
	public R remove( Integer id){
		if(userService.remove(id)>0){
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
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
}
