package com.example.drools;

import com.example.drools.bean.Person;
import com.example.drools.utils.KieSessionUtils;
import io.swagger.annotations.ApiOperation;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class TestController {
    @GetMapping("/index")
    public String ping(){
        try {
            KieSession session  = KieSessionUtils.getAllRules();
            session.insert(new Double("6"));
            session.fireAllRules();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";

    }

    @GetMapping("/xls2drl")
    public String ping2(String path){
        try {
             String gg  =  KieSessionUtils.getDRL(path);
             System.out.println(gg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "created";

    }

    @ApiOperation(value = "验证规则是合法")
    @GetMapping(value = "/aaa")
    public String ruleVerify(String name) {
        Person person = new Person();
        person.setAge(38);
        person.setDesc("hape");
        person.setName("hanhan");
        KieSession session  = null;
        try {
            session = KieSessionUtils.getAllRules();
            session.insert(person);
            session.fireAllRules();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name+"bbb";
    };

    @ResponseBody
    @RequestMapping(value = "/selectContacts", method = RequestMethod.GET)
    @ApiOperation(value = "查询通讯录人员信息", notes = "查询通讯录人员信息")
    public void selectContacts() {
        System.out.println("vv");
    }

    @GetMapping("/xls2drl2")
    public String getRuleTable(String path) throws Exception {
        //把excel翻译成drl文件
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        File file = new File(path); // 例如：C:\\abc.xls
        InputStream is = new FileInputStream(file);
        System.out.println(InputType.XLS);
        String rules =  compiler.compile(is,InputType.XLS);
        System.out.println(rules);
        return rules;
    }


}
