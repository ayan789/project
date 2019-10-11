package com.example.tlyannoif;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Object AccountSave(Account account) {
        String result = "";
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(account,GroupOne.class);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
            result = errors;
        }
        else if(account.getStrs().get(0).equals("ABC")){
            result = "不能存在ABC";
        }
        else{
            System.out.println("执行saveAccount");
            result = "执行saveAccount";
        }
        return result;
    }
}
