package com.example.demo.decorator;

import java.util.UUID;

@Deprecated
public class Login implements ILogin {
    @Override
    public ResultMsg register(String userName, String pwd) {
        System.out.println("1、检查是否注册过");
        User u =  new User(UUID.randomUUID().toString().replaceAll("-",""),userName,pwd);
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setSuccess("注册成功",u);
        return resultMsg;
    }

    @Override
    public ResultMsg login(String userName, String pwd) {
        System.out.println("检验用户名和密码是否正确");
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setSuccess("登录成功",new User("",userName,pwd));
        return resultMsg;
    }
}
