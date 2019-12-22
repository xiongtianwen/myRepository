package com.example.demo.decorator;

public interface IThirdLogin extends ILogin {

    ResultMsg loginForQQ(String qqId);

    ResultMsg loginForWechat(String wechatId);
}
