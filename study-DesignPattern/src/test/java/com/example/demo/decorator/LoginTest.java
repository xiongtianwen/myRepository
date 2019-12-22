package com.example.demo.decorator;

/**
 * 装饰模式测试
 */
public class LoginTest {
    public static void main(String[] args) {
        IThirdLogin login = new ThirdLogin(new Login());
        ResultMsg resultMsg = login.loginForQQ("zhangsan");
        System.out.println(resultMsg.getMsg());
        User u = (User)resultMsg.getData();
        System.out.println(u.getUserName());

    }

}
