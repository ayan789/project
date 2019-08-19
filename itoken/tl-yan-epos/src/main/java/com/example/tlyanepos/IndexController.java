package com.example.tlyanepos;

import com.neo.DocumentDefine;
import com.neo.DocumentMgr;
import lerrain.tool.script.InsureFactors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class IndexController {

    @Autowired
    UserDao userDao;

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


}
