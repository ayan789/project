package com.example.tlyanepos;

import com.application.SInsure;
import com.neo.DocumentDefine;
import com.neo.DocumentMgr;
import lerrain.tool.script.InsureFactors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class IndexController {

    @Autowired
    MyFutureTask myFutureTask;

    @Autowired
    UserDao userDao;

    @Autowired
    private ContentCategoryService contentCategoryService;

    @Autowired
    private TprogressService tprogressService;


    @GetMapping("/home")
    public String index () {
        return "up tl-yan-epos";
    }

    @GetMapping("/userList")
    public String userList () {
         List<User> list = userDao.getByMap(new HashMap<>());
         System.out.println(list.get(0).getName());
         return "OK";
    }

    @GetMapping("/getDemo")
    public String getDemo (String a,String b) {
        System.out.println("get");
        System.out.println("a"+a);
        System.out.println("b"+b);
        return "OK";
    }

    @PostMapping("/postDemo")
    public String postDemo (String a,String b) {
        System.out.println("post");
        System.out.println("a"+a);
        System.out.println("b"+b);
        return "OK";
    }

    @PutMapping("/putDemo")
    public String putDemo (String a,String b) {
        System.out.println("put");
        System.out.println("a"+a);
        System.out.println("b"+b);
        return "OK";
    }

    @DeleteMapping("/deleteDemo")
    public String deleteDemo (String a,String b) {
        System.out.println("delete");
        System.out.println("a"+a);
        System.out.println("b"+b);
        return "OK";
    }

    @GetMapping("/loadDocument")
    public List<DocumentDefine> loadDocument () {
        DocumentMgr mgr = new DocumentMgr();
        InsureFactors insureFactors = new InsureFactors();
        insureFactors.set("payFreqCode","ol");
        List<DocumentDefine> lists = mgr.getDocumentList(insureFactors);
        System.out.println(lists.toString());
        return lists;
    }

    @GetMapping("/loadProgress")
    public Map loadProgress () {
        ProgressMgr mgr = new ProgressMgr();
        Map map = mgr.getProgressList();
        System.out.println(map);
        return map;
    }

    @GetMapping("/get/data")
    public SInsure getUserData(Long userId) {
        System.out.println("SInsureController的线程:" + Thread.currentThread());
        long begin = System.currentTimeMillis();
        SInsure sInsure = myFutureTask.getUserAggregatedResult(userId);
        long end = System.currentTimeMillis();
        System.out.println("===============总耗时:" + (end - begin) /1000.0000+ "秒");
        return sInsure;
    }


    @GetMapping("/list")
    public List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }

    @RequestMapping("/list2")
    @ResponseBody
    public Map<String,Object> getContentCatList() {
        TreeNode root = contentCategoryService.getCategoryList2((long)0);
        Map<String,Object> map=new HashMap<>();
        map.put("data", root.getChildren());
        return map;
    }

    @RequestMapping("/list3")
    @ResponseBody
    public Map<String,Object>  getList() {
        TprogressTreeNode root = tprogressService.getTree("00");
        Map<String,Object> map=new HashMap<>();
        map.put("stepModels", root.getChildren());
        return map;
    }


}
