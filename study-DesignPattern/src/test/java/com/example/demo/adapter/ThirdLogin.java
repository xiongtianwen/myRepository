package com.example.demo.adapter;

public class ThirdLogin extends Login {

    ResultMsg loginForQQ(String qqId){
        ResultMsg resultMsg = super.register(qqId,null);
        if(200 == resultMsg.getCode()){
            return super.login(qqId,null);
        }else{
            resultMsg.setFail("QQ登录失败！",new User(qqId,null,null));
            return resultMsg;
        }

    }

    ResultMsg loginForWechat(String wechatId){
        ResultMsg resultMsg = super.register(wechatId,null);
        if(200 == resultMsg.getCode()){
            return super.login(wechatId,null);
        }else{
            resultMsg.setFail("微信登录失败！",new User(wechatId,null,null));
            return resultMsg;
        }
    }
}
