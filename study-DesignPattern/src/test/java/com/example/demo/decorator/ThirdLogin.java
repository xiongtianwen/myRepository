package com.example.demo.decorator;

public class ThirdLogin implements IThirdLogin {

    private ILogin iLogin;

    public ThirdLogin(ILogin iLogin){
        this.iLogin = iLogin;
    }

    @Override
    public ResultMsg register(String userName, String pwd) {
        return iLogin.register(userName,pwd);
    }

    @Override
    public ResultMsg login(String userName, String pwd) {
        return iLogin.login(userName,pwd);
    }

    @Override
    public ResultMsg loginForQQ(String qqId){
        ResultMsg resultMsg = this.register(qqId,null);
        if(200 == resultMsg.getCode()){
            return this.login(qqId,null);
        }else{
            resultMsg.setFail("QQ登录失败！",new User(qqId,null,null));
            return resultMsg;
        }

    }

    @Override
    public ResultMsg loginForWechat(String wechatId){
        ResultMsg resultMsg = this.register(wechatId,null);
        if(200 == resultMsg.getCode()){
            return this.login(wechatId,null);
        }else{
            resultMsg.setFail("微信登录失败！",new User(wechatId,null,null));
            return resultMsg;
        }
    }

}
